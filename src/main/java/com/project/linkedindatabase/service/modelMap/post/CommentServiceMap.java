package com.project.linkedindatabase.service.modelMap.post;

import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.repository.model.post.CommentRepository;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CommentServiceMap implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceMap(LikeCommentService likeCommentService) throws SQLException {
        this.commentRepository = new CommentRepository(likeCommentService);
    }

    @Override
    public Comment findById(Long aLong) throws SQLException {
        return commentRepository.findById(aLong);
    }

    @Override
    public void save(Comment object) throws SQLException {
        commentRepository.save(object);
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        return commentRepository.findAll();
    }

    @Override
    public void deleteByObject(Comment object) throws SQLException {
        commentRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        commentRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        commentRepository.createTable();
    }

    @Override
    public List<CommentJson> findByPostIdJson(Long id) throws SQLException {
        return commentRepository.findByPostIdJson(id);
    }
}
