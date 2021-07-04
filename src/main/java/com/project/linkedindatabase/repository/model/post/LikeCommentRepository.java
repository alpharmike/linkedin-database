package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LikeCommentRepository  extends BaseRepository<LikeComment,Long>  {

    public LikeCommentRepository() throws SQLException {
        super(LikeComment.class);
    }



    @Override
    public void save(LikeComment object) throws SQLException {
        PreparedStatement savePs =
    }

    @Override
    public void createTable() throws SQLException {

    }



    @Override
    public LikeComment convertSql(ResultSet resultSet) {
        return null;
    }

}
