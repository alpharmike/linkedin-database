package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EndorsementRepository extends BaseRepository<Endorsement,Long>  {

    public EndorsementRepository() throws SQLException {
        super(Endorsement.class);
    }


    @Override
    public void save(Endorsement object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }


    @Override
    public Endorsement convertSql(ResultSet resultSet) {
        return null;
    }
}

