package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentRepository extends BaseRepository<Accomplishment,Long>   {

    public AccomplishmentRepository() throws SQLException {
        super(Accomplishment.class);
    }


    @Override
    public void save(Accomplishment object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }


    @Override
    public Accomplishment convertSql(ResultSet resultSet) {
        return null;
    }
}