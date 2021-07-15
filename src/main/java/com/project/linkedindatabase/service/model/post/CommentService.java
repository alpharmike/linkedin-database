package com.project.linkedindatabase.service.model.post;

import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface CommentService extends BaseService<Comment,Long> {
    public List<CommentJson> findByPostIdJson(Long id) throws SQLException;
}
