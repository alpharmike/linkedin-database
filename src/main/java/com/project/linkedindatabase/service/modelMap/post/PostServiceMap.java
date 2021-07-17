package com.project.linkedindatabase.service.modelMap.post;

import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.PostJson;
import com.project.linkedindatabase.repository.model.post.PostRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import com.project.linkedindatabase.service.model.post.PostService;
import com.project.linkedindatabase.service.types.ShowPostTypeService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceMap implements PostService {

    private final PostRepository postRepository;

    public PostServiceMap(LikePostService likePostService, ShowPostTypeService showPostTypeService, CommentService commentService, ProfileService profileService) throws SQLException {
        this.postRepository = new PostRepository(likePostService, showPostTypeService, commentService, profileService);
    }

    @Override
    public Post findById(Long aLong) throws SQLException {
        return postRepository.findById(aLong);
    }

    @Override
    public void save(Post object) throws SQLException {
        postRepository.save(object);
    }

    @Override
    public List<Post> findAll() throws SQLException {
        return postRepository.findAll();
    }

    @Override
    public void deleteByObject(Post object) throws SQLException {
        postRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        postRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        postRepository.createTable();
    }

    @Override
    public List<PostJson> findByProfileId(Long profileId) throws Exception {
        return postRepository.findByProfileId(profileId);
    }

    @Override
    public void updateWithProfile(PostJson post) throws Exception {
        postRepository.updateWithProfile(post);
    }

    @Override
    public void DeleteByProfileId(Long profileId, Long id) throws Exception {
        postRepository.DeleteByProfileId(profileId,id);
    }

    @Override
    public List<PostJson> getPostOfConnection(Long profileId) throws SQLException {

        return postRepository.getPostOfConnection(profileId);
    }
    @Override
    public List<PostJson> getPostOfConnectionLike(Long profileId) throws SQLException {
        return postRepository.getPostOfConnectionLike(profileId);
    }
    @Override
    public List<PostJson> getPostOfConnectionComment(Long profileId) throws SQLException {
        return postRepository.getPostOfConnectionComment(profileId);
    }
}
