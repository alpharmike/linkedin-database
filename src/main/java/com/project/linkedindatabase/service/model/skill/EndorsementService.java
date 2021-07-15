package com.project.linkedindatabase.service.model.skill;

import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.jsonToPojo.EndorsementPoJo;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EndorsementService extends BaseService<Endorsement,Long> {
    public Endorsement editById(Long id, Long skillId, Long skillLevel, Long relationKnowledge, Long endorserId) throws SQLException;
    public void updateWithProfileId(Endorsement endorsement) throws SQLException;
    public void deleteById(Long id) throws SQLException;
    public List<Endorsement> getAllById(long id) throws SQLException;
    public List<Endorsement> getAllByProfileId(long profileId) throws SQLException;
    public List<Endorsement> getAllBySkillId(long skillId) throws SQLException;
    public List<EndorsementPoJo> getAllBySkillIdJson(long skillId) throws SQLException;
    public boolean isThereAnotherEndorsement(Endorsement endorsement) throws SQLException;
}
