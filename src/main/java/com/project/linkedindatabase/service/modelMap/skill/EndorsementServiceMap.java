package com.project.linkedindatabase.service.modelMap.skill;

import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.jsonToPojo.EndorsementPoJo;
import com.project.linkedindatabase.repository.model.skill.EndorsementRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;
import com.project.linkedindatabase.service.types.SkillLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EndorsementServiceMap  implements EndorsementService {

    private final EndorsementRepository endorsementRepository;

    public EndorsementServiceMap(SkillLevelService skillLevelService, RelationKnowledgeService relationKnowledgeService,
                                 ProfileService profileService) throws SQLException {
        this.endorsementRepository = new EndorsementRepository(skillLevelService, relationKnowledgeService, profileService);
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
    public List<Endorsement> getAllById(long id) throws SQLException {
        return this.endorsementRepository.getAllById(id);
    }

    @Override
    public List<Endorsement> getAllByProfileId(long profileId) throws SQLException {
        return this.endorsementRepository.getAllByProfileId(profileId);
    }

    @Override
    public List<Endorsement> getAllBySkillId(long skillId) throws SQLException {
        return this.endorsementRepository.getAllBySkillId(skillId);
    }

    @Override
    public List<EndorsementPoJo> getAllBySkillIdJson(long skillId) throws SQLException {
        return this.endorsementRepository.getAllBySkillIdJson(skillId);
    }

    @Override
    public boolean isThereAnotherEndorsement(Endorsement endorsement) throws SQLException {
        return endorsementRepository.isThereAnotherEndorsement(endorsement);
    }

    @Override
    public void deleteAllBySkillId(Long skillId) throws SQLException {
        endorsementRepository.deleteAllBySkillId(skillId);
    }

    @Override
    public void createTable() throws SQLException {
        endorsementRepository.createTable();
    }

    @Override
    public Endorsement editById(Long id, Long skillId, Long skillLevel, Long relationKnowledge, Long endorserId) throws SQLException {
        return endorsementRepository.editById(id, skillId, skillLevel, relationKnowledge, endorserId);
    }

    @Override
    public void updateWithProfileId(Endorsement endorsement) throws SQLException {
        endorsementRepository.updateWithProfileId(endorsement);
    }
}
