package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
        PreparedStatement ps = conn.prepareStatement("insert into "+ this.getTableName() + " (" + PROFILE_ID + "," + LANGUAGE + "," + LANGUAGE_LEVEL + ") " +
                "values (?,?,?);");

        ps.setLong(1, object.getProfileId());
        ps.setString(2, object.getLanguage());
        ps.setLong(3, object.getLanguageLevel());
        ps.execute();
    }

    @Override
    public void createTable() throws SQLException {
        final String createQuery = String.format("create table if not exists %s ("
                + " " + ID + " bigint not null auto_increment,"
                + " " + PROFILE_ID + " bigint not null,"
                + " " + LANGUAGE + " nvarchar(100) not null,"
                + " " + LANGUAGE_LEVEL + " bigint not null,"
                + " primary key (" + ID + "),"
                + " foreign key " + "(" + PROFILE_ID + ")" + " references " + BaseEntity.getTableName(Profile.class) + "(id),"
                + " foreign key " + "(" + LANGUAGE_LEVEL + ")" + " references " + BaseEntity.getTableName(LanguageLevel.class) + "(id)"
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

    public List<Language> findByProfileId(Long id) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("select * from " + this.getTableName() + " where " + PROFILE_ID + " = ?");
        ps.setLong(1,id);


        ResultSet resultSet = ps.executeQuery();
        List<Language> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    public void update(Language language) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("UPDATE  "+this.getTableName()+"  SET " +
                "" + LANGUAGE + " = ?," + LANGUAGE_LEVEL + " = ? " +
                "where " + ID + " = ?;");


        ps.setString(1, language.getLanguage());
        ps.setLong(2, language.getLanguageLevel());
        ps.setLong(3, language.getId());


        ps.execute();
    }

    public void updateWithProfileId(Language language) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("UPDATE  "+this.getTableName()+"  SET " +
                "" + LANGUAGE + " = ?," + LANGUAGE_LEVEL + " = ? " +
                "where " + ID + " = ? and "  + PROFILE_ID + " = ?;");


        ps.setString(1, language.getLanguage());
        ps.setLong(2, language.getLanguageLevel());
        ps.setLong(3, language.getId());
        ps.setLong(4, language.getProfileId());


        ps.execute();
    }

    public void deleteByIdAndProfileId(Language language) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where " + ID + " = ? and  " + PROFILE_ID +" = ?");

        ps.setLong(1, language.getId());
        ps.setLong(2, language.getProfileId());
        ps.execute();
    }
}