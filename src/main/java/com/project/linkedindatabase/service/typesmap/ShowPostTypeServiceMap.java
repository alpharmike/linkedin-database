package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.types.ShowPostTypeRepository;
import com.project.linkedindatabase.service.types.ShowPostTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ShowPostTypeServiceMap  implements ShowPostTypeService {

    private final ShowPostTypeRepository showPostTypeRepository;
    public ShowPostTypeServiceMap() throws SQLException {
        showPostTypeRepository = new ShowPostTypeRepository();
    }

    @Override
    public ShowPostType findById(Long id) throws SQLException {
        return showPostTypeRepository.findById(id);
    }

    @Override
    public void save(ShowPostType object) throws SQLException {
        showPostTypeRepository.save(object);
    }

    @Override
    public List<ShowPostType> findAll() throws SQLException {
        return showPostTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        showPostTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        showPostTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(ShowPostType object) throws SQLException {
        showPostTypeRepository.deleteByObject(object);
    }


    @Override
    public void saveIfNotExist(String name) throws SQLException {
        showPostTypeRepository.saveIfNotExist(name);

    }

    @Override
    public ShowPostType findByName(String name) throws SQLException {
        return showPostTypeRepository.findByName(name);

    }
}