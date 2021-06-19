package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LikeCommentRepository  extends BaseRepository<LikeComment,Long> implements LikeCommentService {

    public LikeCommentRepository() throws SQLException {
        super(LikeComment.class);
    }

    @Override
    public LikeComment findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<LikeComment> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(LikeComment object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(LikeComment object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public LikeComment convertSql(ResultSet resultSet) {
        return null;
    }

}
