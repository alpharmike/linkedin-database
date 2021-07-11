package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.post.CommentService;
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
        PreparedStatement savingPs = this.conn.prepareStatement("INSERT INTO ?(text, date, profileId, postId, reCommentId)" +
                " VALUES(?,?,?,?,?)");
        savingPs.setString(1, this.tableName);
        savingPs.setString(2, object.getText());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(object.getDate());
        savingPs.setString(3, date);
        savingPs.setLong(4, object.getProfileId());
        savingPs.setLong(5, object.getPostId());
        savingPs.setLong(6, object.getReCommentId());
        savingPs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs =  this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+this.tableName+
                "(id BIGINT NOT NULL AUTO_INCREMENT,"+
                "text NVARCHAR(2000) NOT NULL,"+
                "date VARCHAR(255) NOT NULL,"+
                "profileId BIGINT," +
                "FOREIGN KEY (profileId) REFERENCES profile(id)," +
                "postId BIGINT," +
                "FOREIGN KEY (postId) REFERENCES post(id)," +
                "reCommentId BIGINT," +
                "FOREIGN KEY (reCommentId) REFERENCES comment(id),"+
                "PRIMARY KEY (id),"+
                ");");
        createTablePs.execute();
    }


    @Override
    public Comment convertSql(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        try{
            resultSet.first();
            comment.setId(resultSet.getLong("id"));
            comment.setText( resultSet.getString("text"));
            comment.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resultSet.getString("date")));
            comment.setProfileId(resultSet.getLong("profileId"));
            comment.setPostId(resultSet.getLong("postId"));
            comment.setReCommentId(resultSet.getLong("reCommentId"));
        }catch (SQLException  | ParseException s){
            System.out.println(s.getMessage());
        }
        return comment;
    }
}
