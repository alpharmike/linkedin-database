package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.repository.types.PhoneTypeRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.utils.DateConverter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ProfileRepository extends BaseRepository<Profile,Long>   {

    public ProfileRepository() throws SQLException {
        super(Profile.class);
    }



    @Override
    public void save(Profile object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(username, email, phoneNumber, phoneType, " +
                "password, firstName, lastName, formerName, formerNameVisibilityType, headline, " +
                "country, locationInCountry, " +
                "industry, address, dateOfBirth, about, urlToProfile) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
        savePs.setString(1, object.getUsername());
        savePs.setString(2, object.getEmail());
        savePs.setString(3, object.getPhoneNumber());
        savePs.setLong(4, object.getPhoneType());
        savePs.setString(5, object.getPassword());
        savePs.setString(6, object.getFirstName());
        savePs.setString(7, object.getLastName());
        savePs.setString(8, object.getFormerName());
        savePs.setLong(9, object.getFormerNameVisibilityType());
        savePs.setString(10, object.getHeadline());
        savePs.setString(11, object.getCountry());
        savePs.setString(12, object.getLocationInCountry());
        savePs.setLong(13, object.getIndustry());
        savePs.setString(14, object.getAddress());
        savePs.setString(15, DateConverter.convertDate(object.getDateOfBirth(), "yyyy-MM-dd"));
        savePs.setString(16, object.getAbout());
        savePs.setString(17, object.getUrlToProfile());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+this.tableName+"(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "username NVARCHAR(255) NOT NULL,"+
                "email NVARCHAR(255) NOT NULL,"+
                "phoneNumber NVARCHAR(255) NOT NULL,"+
                "phoneType BIGINT NOT NULL," +
                "FOREIGN KEY (phoneType) REFERENCES " +  BaseEntity.getTableName(PhoneType.class) + "(id),"+
                "password NVARCHAR(255) NOT NULL,"+
                "firstName NVARCHAR(255) NOT NULL,"+
                "lastName NVARCHAR(255) NOT NULL,"+
                "formerName NVARCHAR(255) NOT NULL,"+
                "formerNameVisibilityType BIGINT NOT NULL," +
                "FOREIGN KEY (formerNameVisibilityType) REFERENCES " +  BaseEntity.getTableName(FormerNameVisibilityType.class) + "(id),"+
                "headline NVARCHAR(255) NOT NULL,"+
                "country NVARCHAR(255) NOT NULL,"+
                "locationInCountry NVARCHAR(255) NOT NULL,"+
                "industry BIGINT NOT NULL," +
                "FOREIGN KEY (industry) REFERENCES " +  BaseEntity.getTableName(Industry.class) + "(id),"+
                "address NVARCHAR(255) NOT NULL,"+
                "dateOfBirth NVARCHAR(255) NOT NULL,"+
                "about TEXT NOT NULL,"+
                "urlToProfile NVARCHAR(255) NOT NULL,"+
                "PRIMARY KEY (id)"+
            ")"
        );


        createTablePs.execute();
    }

    public Profile findByUsername(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from " + this.getTableName() + " where username = ?");
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        return convertSql(resultSet);
    }


    @Override
    public Profile convertSql(ResultSet resultSet) {
        Profile profile = new Profile();
        try{
            resultSet.first();
            profile.setId(resultSet.getLong("id"));
            profile.setEmail(resultSet.getString("email"));
            profile.setPhoneNumber(resultSet.getString("phoneNumber"));
            profile.setPhoneType(resultSet.getLong("phoneType"));
            profile.setPassword(resultSet.getString("password"));
            profile.setFirstName(resultSet.getString("firstName"));
            profile.setLastName(resultSet.getString("lastName"));
            profile.setFormerName(resultSet.getString("formerName"));
            profile.setFormerNameVisibilityType(resultSet.getLong("formerNameVisibilityType"));
            profile.setHeadline(resultSet.getString("headline"));
            profile.setCountry(resultSet.getString("country"));
            profile.setLocationInCountry(resultSet.getString("locationInCountry"));
            profile.setIndustry(resultSet.getLong("industry"));
            profile.setAddress(resultSet.getString("address"));
            profile.setDateOfBirth(DateConverter.parse(resultSet.getString("dateOfBirth"), "yyyy-MM-dd"));
            profile.setAbout(resultSet.getString("about"));
            profile.setUrlToProfile(resultSet.getString("urlToProfile"));

        }catch (SQLException | ParseException s){
            System.out.println(s.getMessage());
        }
        return null;
    }
}
