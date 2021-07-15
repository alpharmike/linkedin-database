package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.jsonToPojo.LikeJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeCommentRepository  extends BaseRepository<LikeComment,Long>  {

    public LikeCommentRepository() throws SQLException {
        super(LikeComment.class);
    }



    @Override
    public void save(LikeComment object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(commentId, profileId) VALUES(?, ?)");
        savePs.setLong(1, object.getCommentId());
        savePs.setLong(2, object.getProfileId());
        savePs.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "commentId BIGINT NOT NULL," +
                "FOREIGN KEY (commentId) REFERENCES " +  BaseEntity.getTableName(Comment.class) + "(id),"+
                "PRIMARY KEY (id)"+
            ")"
        );
        createTablePs.execute();
    }



    @Override
    public LikeComment convertSql(ResultSet resultSet) throws SQLException {
        LikeComment likeComment = new LikeComment();


        likeComment.setId(resultSet.getLong("id"));
        likeComment.setProfileId(resultSet.getLong("profileId"));
        likeComment.setCommentId(resultSet.getLong("commentId"));

        return likeComment;
    }

    public List<LikeComment> getLikeByCommendId(Long commentId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName()+ " where commentId = ?");
        ps.setLong(1,commentId);

        ResultSet resultSet = ps.executeQuery();
        List<LikeComment> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }


    public CommentJson  setAllLikeComment(CommentJson commentJson) throws SQLException {
        List<LikeComment> like = getLikeByCommendId(commentJson.getId());

        for (LikeComment i : like)
        {
            commentJson.getLikeJsons().add(LikeJson.convertToJsonComment(i));
        }

        return commentJson;

    }

    public void deleteByIdAndProfileId(Long id, Long profileId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where id = ? and profileId = ?");

        ps.setLong(1, id);
        ps.setLong(2, profileId);
        ps.execute();
    }

    public boolean isThereALike(LikeComment likeComment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select *  from "+this.getTableName()+" where commentId = ? and profileId = ?");
        ps.setLong(1, likeComment.getCommentId());
        ps.setLong(2, likeComment.getProfileId());
        ResultSet resultSet = ps.executeQuery();
        if (!resultSet.isBeforeFirst() ) {
            return false;
        }else
        {
            return true;
        }
        // return resultSet.isBeforeFirst();
    }
}
