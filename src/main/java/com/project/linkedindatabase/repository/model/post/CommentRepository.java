package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.utils.DateConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.parsers.SAXParser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentRepository extends BaseRepository<Comment,Long>  {



    public CommentRepository() throws SQLException {
        super(Comment.class);

    }



    @Override
    public void save(Comment object) throws SQLException{
        PreparedStatement savingPs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(profileId, postId, reCommentId, body, createdDate)" +
                " VALUES(?,?,?,?,?)");
        savingPs.setLong(1, object.getProfileId());
        savingPs.setLong(2, object.getPostId());
        savingPs.setLong(3, object.getReCommentId());
        savingPs.setString(4, object.getBody());
        savingPs.setString(5, DateConverter.convertDate(object.getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));

        savingPs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs =  this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "postId BIGINT NOT NULL," +
                "FOREIGN KEY (postId) REFERENCES " +  BaseEntity.getTableName(Post.class) + "(id),"+
                "reCommentId BIGINT NOT NULL," +
                "FOREIGN KEY (reCommentId) REFERENCES " +  BaseEntity.getTableName(Comment.class) + "(id),"+
                "body TEXT NOT NULL,"+
                "createdDate NVARCHAR(255) NOT NULL,"+
                "PRIMARY KEY (id)"+
            ");"
        );
        createTablePs.execute();
    }


    @Override
    public Comment convertSql(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        try {
            resultSet.first();
            comment.setId(resultSet.getLong("id"));
            comment.setProfileId(resultSet.getLong("profileId"));
            comment.setPostId(resultSet.getLong("postId"));
            comment.setReCommentId(resultSet.getLong("reCommentId"));
            comment.setBody( resultSet.getString("body"));
            comment.setCreatedDate(DateConverter.parse(resultSet.getString("createdDate"), "yyyy-MM-dd HH:mm:ss"));

        } catch (SQLException  | ParseException s){
            System.out.println(s.getMessage());
        }
        return comment;
    }
}
