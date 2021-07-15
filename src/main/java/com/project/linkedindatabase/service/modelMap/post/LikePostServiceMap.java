package com.project.linkedindatabase.service.modelMap.post;

import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.jsonToPojo.LikeJson;
import com.project.linkedindatabase.repository.model.post.LikePostRepository;
import com.project.linkedindatabase.service.model.post.LikePostService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LikePostServiceMap  implements LikePostService {

    private final LikePostRepository likePostRepository;

    public LikePostServiceMap() throws SQLException {
        this.likePostRepository = new LikePostRepository();
    }

    @Override
    public LikePost findById(Long aLong) throws SQLException {
        return likePostRepository.findById(aLong);
    }

    @Override
    public void save(LikePost object) throws SQLException {
        likePostRepository.save(object);
    }

    @Override
    public List<LikePost> findAll() throws SQLException {
        return likePostRepository.findAll();
    }

    @Override
    public void deleteByObject(LikePost object) throws SQLException {
        likePostRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        likePostRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        likePostRepository.createTable();
    }

    @Override
    public List<LikePost> getLikeByPostId(Long id) throws SQLException {
        return likePostRepository.getLikeByPostId(id);
    }

    @Override
    public void deleteByIdAndProfileId(Long id, Long profileId) throws SQLException {
        likePostRepository.deleteByIdAndProfileId( id,  profileId);
    }

    @Override
    public boolean isThereALike(LikePost likePost) throws SQLException {
        return likePostRepository.isThereALike(likePost);
    }
}
