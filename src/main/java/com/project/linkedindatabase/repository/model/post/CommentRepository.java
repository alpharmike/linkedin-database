package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentRepository extends BaseRepository<Comment,Long>  {

    private final LikeCommentService likeCommentService;


    public CommentRepository(LikeCommentService likeCommentService) throws SQLException {
        super(Comment.class);

        this.likeCommentService = likeCommentService;
    }



    @Override
    public void save(Comment object) throws SQLException{
        PreparedStatement savingPs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(profileId, postId, reCommentId, body, createdDate)" +
                " VALUES(?,?,?,?,?)");
        savingPs.setLong(1, object.getProfileId());
        savingPs.setLong(2, object.getPostId());
        if (object.getReCommentId() != null){
            savingPs.setLong(3, object.getReCommentId());}
        else
        {
            savingPs.setNull(3, Types.BIGINT);
        }
        savingPs.setString(4, object.getBody());
        savingPs.setString(5, DateConverter.convertDate(object.getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));

        savingPs.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs =  this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "postId BIGINT NOT NULL," +
                "FOREIGN KEY (postId) REFERENCES " +  BaseEntity.getTableName(Post.class) + "(id),"+
                "reCommentId BIGINT ," +
                "FOREIGN KEY (reCommentId) REFERENCES " +  BaseEntity.getTableName(Comment.class) + "(id),"+
                "body TEXT NOT NULL,"+
                "createdDate NVARCHAR(255) NOT NULL,"+
                "PRIMARY KEY (id)"+
            ");"
        );
        createTablePs.execute();
    }


    @SneakyThrows
    @Override
    public Comment convertSql(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();

            comment.setId(resultSet.getLong("id"));
            comment.setProfileId(resultSet.getLong("profileId"));
            comment.setPostId(resultSet.getLong("postId"));
            comment.setReCommentId(resultSet.getLong("reCommentId"));
            comment.setBody( resultSet.getString("body"));
            comment.setCreatedDate(DateConverter.parse(resultSet.getString("createdDate"), "yyyy-MM-dd HH:mm:ss"));


        return comment;
    }

    public List<Comment> findByPostId(Long postId) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName()+ " where postId = ?");
        ps.setLong(1,postId);

        ResultSet resultSet = ps.executeQuery();
        List<Comment> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;

    }

    public List<CommentJson> findByPostIdJson(Long id) throws SQLException {
        List<Comment> allObject = findByPostId(id);

        List<CommentJson> commentJsonList = new ArrayList<>();

        for (Comment i : allObject)
        {

            CommentJson commentJson = CommentJson.convertToJson(i);
            likeCommentService.setAllLikeComment(commentJson);
            commentJsonList.add(commentJson);
        }

        //create tree
        List<CommentJson> tree = new ArrayList<>();

        //simple explanation
        // if it has reComment -> it has father so we find it and add to father
        // if it has not reComment id -> it is root so we add to tree
        for (CommentJson i :commentJsonList)
        {
            if (i.getReCommentId() == null || i.getReCommentId() == 0)
            {
                tree.add(i);
                continue;
            }

            for (CommentJson j : commentJsonList)
            {
                if (j.getId() == i.getReCommentId())
                {
                    j.getCommentJsonsChild().add(i);
                }
            }
        }

        return tree;

    }
}
