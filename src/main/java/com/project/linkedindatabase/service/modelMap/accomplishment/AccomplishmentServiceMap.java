package com.project.linkedindatabase.service.modelMap.accomplishment;

import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.repository.model.accomplishment.AccomplishmentRepository;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentServiceMap implements AccomplishmentService {

    private final AccomplishmentRepository accomplishmentRepository;

    public AccomplishmentServiceMap() throws SQLException {
        this.accomplishmentRepository = new AccomplishmentRepository();
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
}
