package com.project.linkedindatabase.service.model.post;

import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.PostJson;
import com.project.linkedindatabase.service.BaseService;

import java.util.List;

public interface PostService extends BaseService<Post,Long> {
    public List<PostJson> findByProfileId(Long profileId) throws Exception;
    public void updateWithProfile(PostJson post) throws Exception;

    public void DeleteByProfileId(Long profileId, Long id) throws Exception;
}
