package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.LikeJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikePostRepository extends BaseRepository<LikePost,Long>   {

    public LikePostRepository() throws SQLException {
        super(LikePost.class);
    }


    @Override
    public void save(LikePost object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(postId, profileId) VALUES(?, ?)");
        savePs.setLong(1, object.getPostId());
        savePs.setLong(2, object.getProfileId());
        savePs.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "postId BIGINT NOT NULL," +
                "FOREIGN KEY (postId) REFERENCES " +  BaseEntity.getTableName(Post.class) + "(id),"+
                "PRIMARY KEY (id)"+
            ")"
        );

        createTablePs.execute();
    }



    @Override
    public LikePost convertSql(ResultSet resultSet) throws SQLException {
        LikePost likePost = new LikePost();

        likePost.setId(resultSet.getLong("id"));
        likePost.setProfileId(resultSet.getLong("profileId"));
        likePost.setPostId(resultSet.getLong("postId"));

        return likePost;
    }

    public List<LikePost> getLikeByPostId(Long postId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName()+ " where postId = ?");
        ps.setLong(1,postId);

        ResultSet resultSet = ps.executeQuery();
        List<LikePost> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }


}

