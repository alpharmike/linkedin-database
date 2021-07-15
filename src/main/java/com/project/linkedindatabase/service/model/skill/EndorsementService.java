package com.project.linkedindatabase.service.model.skill;

import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EndorsementService extends BaseService<Endorsement,Long> {
    public Endorsement editById(Long id, Long skillId, Long skillLevel, Long relationKnowledge, Long endorserId) throws SQLException;
    public void deleteById(Long id) throws SQLException;
    public ArrayList<Endorsement> getAllById(long id) throws SQLException;
    public ArrayList<Endorsement> getAllByProfileId(long profileId) throws SQLException;
}
