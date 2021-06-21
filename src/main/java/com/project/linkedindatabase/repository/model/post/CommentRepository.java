package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.post.CommentService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CommentRepository extends BaseRepository<Comment,Long>  {

    public CommentRepository() throws SQLException {
        super(Comment.class);
    }



    @Override
    public void save(Comment object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }


    @Override
    public Comment convertSql(ResultSet resultSet) {
        return null;
    }
}
