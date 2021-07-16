package com.project.linkedindatabase.service.modelMap.accomplishment;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.jsonToPojo.AccomplishmentJson;
import com.project.linkedindatabase.repository.model.accomplishment.AccomplishmentRepository;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentServiceMap implements AccomplishmentService {

    private final AccomplishmentRepository accomplishmentRepository;

    public AccomplishmentServiceMap(AccomplishmentTypeService accomplishmentTypeService) throws SQLException {
        this.accomplishmentRepository = new AccomplishmentRepository(accomplishmentTypeService);
    }

    @Override
    public Accomplishment findById(Long aLong) throws SQLException {
        return accomplishmentRepository.findById(aLong);
    }

    @Override
    public void save(Accomplishment object) throws SQLException {
        accomplishmentRepository.save(object);
    }

    @Override
    public List<Accomplishment> findAll() throws SQLException {
        return accomplishmentRepository.findAll();
    }

    @Override
    public void deleteByObject(Accomplishment object) throws SQLException {
        accomplishmentRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        accomplishmentRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        accomplishmentRepository.createTable();
    }

    @Override
    public List<Accomplishment> findByProfileId(Long id) throws Exception {
        return accomplishmentRepository.findByProfileId( id);
    }

    @Override
    public void update(Accomplishment accomplishment) throws Exception {
        accomplishmentRepository.update(accomplishment);
    }

    @Override
    public void updateWithProfileId(Accomplishment accomplishment) throws Exception {
        accomplishmentRepository.updateWithProfileId(accomplishment);
    }

    @Override
    public void deleteByIdAndProfileId(Accomplishment accomplishment) throws Exception {
        accomplishmentRepository.deleteByIdAndProfileId(accomplishment);
    }

    @Override
    public List<AccomplishmentJson> getAllByProfileIdJson(Long profileId) throws SQLException {
        return accomplishmentRepository.getAllByProfileIdJson(profileId);
    }
}
