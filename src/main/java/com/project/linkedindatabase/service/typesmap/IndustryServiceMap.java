package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.repository.types.IndustryRepository;
import com.project.linkedindatabase.service.types.IndustryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class IndustryServiceMap extends IndustryRepository implements IndustryService {
    public IndustryServiceMap() throws SQLException {
        super();
    }

    @Override
    public Industry findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Industry> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(Industry object) throws SQLException {
        super.deleteByItem(object);
    }
}