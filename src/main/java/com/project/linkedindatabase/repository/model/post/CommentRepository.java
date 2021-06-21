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
public class CommentRepository extends BaseRepository<Comment,Long> implements CommentService {

    public CommentRepository() throws SQLException {
        super(Comment.class);
    }

    @Override
    public Comment findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Comment object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Comment object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Comment convertSql(ResultSet resultSet) {
        return null;
    }
}
