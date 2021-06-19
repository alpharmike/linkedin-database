package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class RelationKnowledgeRepository extends BaseTypeRepository<RelationKnowledge> implements RelationKnowledgeService {
    public RelationKnowledgeRepository() throws SQLException {
        super(RelationKnowledge.class);
    }


    @Override
    public RelationKnowledge convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public RelationKnowledge findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(RelationKnowledge object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<RelationKnowledge> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(RelationKnowledge object) throws SQLException {
        super.deleteByObject(object);
    }


    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }


    @Override
    public void createTable() throws SQLException {
        super.createTable();
    }
}
