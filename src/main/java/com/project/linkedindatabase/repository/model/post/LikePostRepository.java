package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LikePostRepository extends BaseRepository<LikePost,Long> implements LikePostService {

    public LikePostRepository() throws SQLException {
        super(LikePost.class);
    }

    @Override
    public LikePost findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<LikePost> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(LikePost object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(LikePost object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public LikePost convertSql(ResultSet resultSet) {
        return null;
    }
}

