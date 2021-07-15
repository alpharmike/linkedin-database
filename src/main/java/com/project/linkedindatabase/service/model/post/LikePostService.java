package com.project.linkedindatabase.service.model.post;

import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.jsonToPojo.LikeJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface LikePostService extends BaseService<LikePost,Long> {
    public List<LikePost> getLikeByPostId(Long id) throws SQLException;

    public void deleteByIdAndProfileId(Long id, Long profileId) throws SQLException;
    public boolean isThereALike(LikePost likePost) throws SQLException;
}
