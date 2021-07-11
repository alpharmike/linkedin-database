package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.PostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PostRepository extends BaseRepository<Post,Long>  {

    public PostRepository() throws SQLException {
        super(Post.class);
    }



    @Override
    public void save(Post object) throws SQLException {

        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO ?(profileId, sharedId, showPostType, " +
                "text, date, file) VALUES(?, ?, ?, ?, ?, ?)");
        savePs.setString(0, this.tableName);
        savePs.setLong(1, object.getProfileId());
        savePs.setLong(2, object.getSharedId());
        savePs.setLong(3, object.getShowPostType());
        savePs.setString(4, object.getText());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(object.getDate());
        savePs.setString(5, date);
        savePs.setBytes(6, object.getFile());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS ?(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT," +
                "FOREIGN KEY (profileId) REFERENCES profile(id),"+
                "sharedId BIGINT," +
                "FOREIGN KEY (sharedId) REFERENCES post(id),"+
                "showPostType BIGINT," +
                "FOREIGN KEY (showPostType) REFERENCES show_post_type(id),"+
                "text NVARCHAR(2000) NOT NULL,"+
                "date NVARCHAR(255) NOT NULL,"+
                "file MEDIUMBLOB,"+
                "PRIMARY KEY (id),"+
                ")");
        createTablePs.setString(0, this.tableName);
        createTablePs.execute();
    }


    @Override
    public Post convertSql(ResultSet resultSet) {
        Post post = new Post();
        try{
            resultSet.first();
            post.setId(resultSet.getLong("id"));
            post.setProfileId(resultSet.getLong("profileId"));
            post.setSharedId(resultSet.getLong("SharedId"));
            post.setShowPostType(resultSet.getLong("showPostType"));
            post.setText(resultSet.getString("text"));
            post.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resultSet.getString("date")));
            //post.setFile(resultSet.getBytes("file"));

    }catch (SQLException | ParseException s){
            System.out.println(s.getMessage());
        }
        return post;
    }
}
