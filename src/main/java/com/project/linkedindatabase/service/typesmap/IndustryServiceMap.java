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

    }

    @Override
    public void deleteByObject(Industry object) throws SQLException {
        industryRepository.deleteByObject(object);
    }
}