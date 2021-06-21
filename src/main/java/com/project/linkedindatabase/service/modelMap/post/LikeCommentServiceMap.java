package com.project.linkedindatabase.service.modelMap.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.repository.model.post.LikeCommentRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LikeCommentServiceMap implements LikeCommentService {

    private final LikeCommentRepository likeCommentRepository;

    public LikeCommentServiceMap() throws SQLException {
        this.likeCommentRepository = new LikeCommentRepository();
    }

    @Override
    public LikeComment findById(Long aLong) throws SQLException {
        return likeCommentRepository.findById(aLong);
    }

    @Override
    public void save(LikeComment object) throws SQLException {
        likeCommentRepository.save(object);
    }

    @Override
    public List<LikeComment> findAll() throws SQLException {
        return likeCommentRepository.findAll();
    }

    @Override
    public void deleteByObject(LikeComment object) throws SQLException {
        likeCommentRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        likeCommentRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        likeCommentRepository.createTable();
    }
}
