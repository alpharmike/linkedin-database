package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.repository.types.PhoneTypeRepository;
import com.project.linkedindatabase.service.model.ProfileService;
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
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO ?(email, phoneNumber, phoneType, " +
                "password, firstName, lastName, formerName, formerNameVisibilityType, headline, currentPositionId, " +
                "showCurrentPositionId, currentEducationId, showCurrentEducationId, country, locationInCountry, " +
                "industry, address, dateOfBirth, about, urlToProfile) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?");
        savePs.setString(1, this.tableName);
        savePs.setString(2, object.getEmail());
        savePs.setString(3, object.getPhoneNumber());
        savePs.setLong(4, object.getPhoneType());
        savePs.setString(5, object.getPassword());
        savePs.setString(6, object.getFirstName());
        savePs.setString(7, object.getLastName());
        savePs.setString(8, object.getFormerName());
        savePs.setLong(9, object.getFormerNameVisibilityType());
        savePs.setString(10, object.getHeadline());
        savePs.setLong(11, object.getCurrentPositionId());
        savePs.setBoolean(12, object.getShowCurrentPositionId());
        savePs.setLong(13, object.getCurrentEducationId());
        savePs.setBoolean(14, object.getShowCurrentEducationId());
        savePs.setString(15, object.getCountry());
        savePs.setString(16, object.getLocationInCountry());
        savePs.setLong(17, object.getIndustry());
        savePs.setString(18, object.getAddress());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(object.getDateOfBirth());
        savePs.setString(19, date);
        savePs.setString(20, object.getAbout());
        savePs.setString(21, object.getUrlToProfile());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+this.tableName+"(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "email VARCHAR(255) NOT NULL,"+
                "phoneNumber VARCHAR(255) NOT NULL,"+
                "phoneType BIGINT," +
                "FOREIGN KEY (phoneType) REFERENCES phone_type(id),"+
                "password VARCHAR(255) NOT NULL,"+
                "firstName VARCHAR(255) NOT NULL,"+
                "lastName VARCHAR(255) NOT NULL,"+
                "formerName VARCHAR(255) NOT NULL,"+
                "formerNameVisibilityType BIGINT," +
                "FOREIGN KEY (formerNameVisibilityType) REFERENCES former_name_visibility_type(id),"+ // ref
                "headline VARCHAR(255) NOT NULL,"+
                "currentPositionId BIGINT," +
                "FOREIGN KEY (currentPositionId) REFERENCES current_position(id),"+ // ref
                "showCurrentPositionId BOOLEAN,"+
                "currentEducationId BIGINT," +
                "FOREIGN KEY (currentEducationId) REFERENCES current_education(id),"+ // ref
                "showCurrentEducationId BOOLEAN,"+
                "country VARCHAR(255),"+
                "locationInCountry VARCHAR(255),"+
                "industry BIGINT," +
                "FOREIGN KEY (industry) REFERENCES industry(id),"+ // ref
                "address VARCHAR(255),"+
                "dateOfBirth VARCHAR(255),"+
                "about VARCHAR(255),"+
                "urlToProfile VARCHAR(255),"+
                "PRIMARY KEY (id)"+
                ")");


        createTablePs.execute();
    }


    @Override
    public Profile convertSql(ResultSet resultSet) {
        Profile profile = new Profile();
        try{
            resultSet.first();
            profile.setId(resultSet.getLong("id"));
            profile.setEmail(resultSet.getString("email"));
            profile.setPhoneNumber(resultSet.getString("phoneNumber"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return null;
    }
}
