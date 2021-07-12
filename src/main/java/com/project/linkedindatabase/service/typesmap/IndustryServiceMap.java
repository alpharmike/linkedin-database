package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.repository.types.IndustryRepository;
import com.project.linkedindatabase.service.types.IndustryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class IndustryServiceMap  implements IndustryService {

    private final IndustryRepository industryRepository;

    public IndustryServiceMap() throws SQLException {
        industryRepository = new IndustryRepository();
    }

    @Override
    public Industry findById(Long id) throws SQLException {
        return industryRepository.findById(id);
    }

    @Override
    public void save(Industry object) throws SQLException {
        industryRepository.save(object);
    }

    @Override
    public List<Industry> findAll() throws SQLException {
        return industryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        industryRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        industryRepository.createTable();
    }

    @Override
    public void deleteByObject(Industry object) throws SQLException {
        industryRepository.deleteByObject(object);
    }

    @Override
    public void saveIfNotExist(String name) throws SQLException {
        industryRepository.saveIfNotExist(name);

    }

    @Override
    public Industry findByName(String name) throws SQLException {
        return industryRepository.findByName(name);

    }

    @Override
    public Industry defaultType() throws SQLException {
        return industryRepository.defaultType();
    }
}