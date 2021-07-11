package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageRepository extends BaseRepository<Language,Long>  {


    protected static final String ID = "id";
    protected static final String PROFILE_ID = "profile_id";
    protected static final String LANGUAGE = "language";
    protected static final String LANGUAGE_LEVEL = "language_level";

    public LanguageRepository() throws SQLException {
        super(Language.class);
    }



    @Override
    public void save(Language object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into "+ this.getTableName()+" ("+PROFILE_ID+","+LANGUAGE +","+LANGUAGE_LEVEL+") " +
                "values (?,?,?);");

        ps.setLong(1, object.getProfileId());
        ps.setString(2, object.getLanguage());
        ps.setLong(3, object.getLanguageLevel());
        ResultSet resultSet = ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        final String createQuery = String.format("create table if not exists %s ("
                + " "+ID+" bigint not null auto_increment,"
                + " "+PROFILE_ID+" bigint not null,"
                + " "+LANGUAGE+" nvarchar(100) not null,"
                + " "+LANGUAGE_LEVEL+" bigint not null,"
                + " primary key ("+ID+")"
                + ");", this.tableName);
        PreparedStatement ps = conn.prepareStatement(createQuery);
        ps.execute();

    }


    @Override
    public Language convertSql(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(ID);
        String languageName = resultSet.getString(LANGUAGE);
        Long profileId = resultSet.getLong(PROFILE_ID);
        Long languageLevel = resultSet.getLong(LANGUAGE_LEVEL);


        var language = new Language();
        language.setId(id);
        language.setLanguage(languageName);
        language.setLanguageLevel(languageLevel);
        language.setProfileId(profileId);


        return language;


    }
}