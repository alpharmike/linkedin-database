package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LikeCommentRepository  extends BaseRepository<LikeComment,Long>  {

    public LikeCommentRepository() throws SQLException {
        super(LikeComment.class);
    }



    @Override
    public void save(LikeComment object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO ?(commentId, profileId) VALUES(?, ?)");
        savePs.setString(0, this.tableName);
        savePs.setLong(1, object.getCommentId());
        savePs.setLong(2, object.getProfileId());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS ?(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT," +
                "FOREIGN KEY (profileId) REFERENCES profile(id),"+
                "commentId BIGINT," +
                "FOREIGN KEY (commentId) REFERENCES comment(id),"+
                "PRIMARY KEY (id),"+
                ")");
        createTablePs.setString(0, this.tableName);
        createTablePs.execute();
    }



    @Override
    public LikeComment convertSql(ResultSet resultSet) {
        LikeComment likeComment = new LikeComment();
        try {
            resultSet.first();
            likeComment.setId(resultSet.getLong("id"));
            likeComment.setProfileId(resultSet.getLong("profileId"));
            likeComment.setCommentId(resultSet.getLong("commentId"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return likeComment;
    }

}
