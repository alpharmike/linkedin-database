package com.project.linkedindatabase.service.model.post;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;

public interface LikeCommentService extends BaseService<LikeComment,Long> {
    public CommentJson setAllLikeComment(CommentJson commentJson) throws SQLException;
}
