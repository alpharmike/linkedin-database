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
public class PostRepository extends BaseRepository<Post,Long>  {

    public PostRepository() throws SQLException {
        super(Post.class);
    }



    @Override
    public void save(Post object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }


    @Override
    public Post convertSql(ResultSet resultSet) {
        return null;
    }
}
