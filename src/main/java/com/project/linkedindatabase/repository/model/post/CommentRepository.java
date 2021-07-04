package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.post.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PreparedStatement savingPs = this.conn.prepareStatement("INSERT INTO comments(text, date, profileId, postId, reCommentId)" +
                " VALUES(?,?,?,?,?)");
        savingPs.setString(0, object.getText());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(object.getDate());
        savingPs.setString(1, date);
        savingPs.setLong(2, object.getProfileId());
        savingPs.setLong(3, object.getPostId());
        savingPs.setLong(4, object.getReCommentId());
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs =  this.conn.prepareStatement("CREATE TABLE Comments(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "text NVARCHAR NOT NULL,"+
                "date NVARCHAR NOT NULL,"+
                "profileId FOREIGN KEY REFERENCES Profiles(profileId)," +
                "postId FOREIGN KEY REFERENCES Posts(postId)," +
                "reCommentId FOREIGN KEY REFERENCES Comments(),"+
                "PRIMARY KEY (id),"+
                ")");
        createTablePs.execute();
    }


    @Override
    public Comment convertSql(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        try{
            resultSet.first();
            comment.setId((long) resultSet.getInt("id"));
            comment.setText( resultSet.getString("text"));
            comment.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(resultSet.getString("date")));
            comment.setProfileId((long)  resultSet.getLong("profileId"));
            comment.setPostId( (long) resultSet.getLong("postId"));
            comment.setReCommentId( (long) resultSet.getLong("reCommentId"));
        }catch (SQLException  | ParseException s){
            System.out.println(s.getMessage());
        }
        return comment;
    }
}
