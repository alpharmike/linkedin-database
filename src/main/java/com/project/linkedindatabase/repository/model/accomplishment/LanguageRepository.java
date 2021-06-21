package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageRepository extends BaseRepository<Language,Long>  {

    public LanguageRepository() throws SQLException {
        super(Language.class);
    }



    @Override
    public void save(Language object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }


    @Override
    public Language convertSql(ResultSet resultSet) {
        return null;
    }
}