package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.repository.model.BackgroundRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.types.BackgroundTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundServiceMap  implements BackgroundService {

    private final BackgroundRepository backgroundRepository;

    public BackgroundServiceMap(BackgroundTypeService backgroundTypeService) throws SQLException {
        this.backgroundRepository = new BackgroundRepository(backgroundTypeService);
    }

    @Override
    public Background findById(Long aLong) throws SQLException {
        return backgroundRepository.findById(aLong);
    }

    @Override
    public void save(Background object) throws SQLException {
        backgroundRepository.save(object);
    }

    @Override
    public List<Background> findAll() throws SQLException {
        return backgroundRepository.findAll();
    }

    @Override
    public void deleteByObject(Background object) throws SQLException {
        backgroundRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        backgroundRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        backgroundRepository.createTable();
    }

    @Override
    public List<Background> findByProfileId(Long id) throws Exception {
        return backgroundRepository.findByProfileId(id);
    }

    @Override
    public void update(Background background) throws Exception {
        backgroundRepository.update(background);
    }

    @Override
    public void updateWithProfileId(Background object) throws Exception {
        backgroundRepository.updateWithProfileId(object);
    }

    @Override
    public void deleteByIdAndProfileId(Background background) throws Exception {
        backgroundRepository.deleteByIdAndProfileId(background);
    }

    @Override
    public BackgroundJson findByIdAndProfileIdJson(Long profileId, Long id) throws Exception {
        return backgroundRepository.findByIdAndProfileIdJson(profileId,id);
    }

    @Override
    public List<BackgroundJson> findByProfileIdJson(Long profileId) throws Exception {
        return backgroundRepository.findByProfileIdJson(profileId);
    }


}
