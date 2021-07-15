package com.project.linkedindatabase.service.modelMap.skill;

import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.repository.model.skill.EndorsementRepository;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndorsementServiceMap  implements EndorsementService {

    private final EndorsementRepository endorsementRepository;

    public EndorsementServiceMap() throws SQLException {
        this.endorsementRepository = new EndorsementRepository();
    }

    @Override
    public Endorsement findById(Long aLong) throws SQLException {
        return endorsementRepository.findById(aLong);
    }

    @Override
    public void save(Endorsement object) throws SQLException {
        endorsementRepository.save(object);
    }

    @Override
    public List<Endorsement> findAll() throws SQLException {
        return endorsementRepository.findAll();
    }

    @Override
    public void deleteByObject(Endorsement object) throws SQLException {
        endorsementRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        endorsementRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<Endorsement> getAllById(long id) throws SQLException {
        return this.endorsementRepository.getAllById(id);
    }

    @Override
    public ArrayList<Endorsement> getAllByProfileId(long profileId) throws SQLException {
        return this.endorsementRepository.getAllByProfileId(profileId);
    }

    @Override
    public void createTable() throws SQLException {
        endorsementRepository.createTable();
    }

    @Override
    public Endorsement editById(Long id, Long skillId, Long skillLevel, Long relationKnowledge, Long endorserId) throws SQLException {
        return endorsementRepository.editById(id, skillId, skillLevel, relationKnowledge, endorserId);
    }
}
