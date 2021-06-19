package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.PostService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PostRepository extends BaseRepository<Post,Long> implements PostService {

    public PostRepository() throws SQLException {
        super(Post.class);
    }

    @Override
    public Post findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Post> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Post object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Post object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Post convertSql(ResultSet resultSet) {
        return null;
    }
}
