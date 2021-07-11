package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LikePostRepository extends BaseRepository<LikePost,Long>   {

    public LikePostRepository() throws SQLException {
        super(LikePost.class);
    }


    @Override
    public void save(LikePost object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO "+this.tableName+"(postId, profileId) VALUES(?, ?)");
        savePs.setString(0, this.tableName);
        savePs.setLong(1, object.getPostId());
        savePs.setLong(2, object.getProfileId());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+this.tableName+"(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT," +
                "FOREIGN KEY (profileId) REFERENCES profile(id),"+
                "postId BIGINT," +
                "FOREIGN KEY (postId) REFERENCES post(id),"+
                "PRIMARY KEY (id)"+
                ")");

        createTablePs.execute();
    }



    @Override
    public LikePost convertSql(ResultSet resultSet) {
        LikePost likePost = new LikePost();
        try {
            resultSet.first();
            likePost.setId(resultSet.getLong("id"));
            likePost.setProfileId(resultSet.getLong("profileId"));
            likePost.setPostId(resultSet.getLong("postId"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return likePost;
    }
}

