package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;
import com.project.linkedindatabase.service.types.IndustryService;
import com.project.linkedindatabase.service.types.PhoneTypeService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@Slf4j
@Service
public class ProfileRepository extends BaseRepository<Profile,Long>   {

    public Long DEFAULT_PHONE_TYPE;
    public Long DEFAULT_INDUSTRY_TYPE;
    public Long DEFAULT_FORMER_NAME_VISIBILITY_TYPE;

    private final PhoneTypeService phoneTypeService;
    private final IndustryService industryService;
    private final FormerNameVisibilityTypeService formerNameVisibilityTypeService;






    public ProfileRepository(PhoneTypeService phoneTypeService, IndustryService industryService, FormerNameVisibilityTypeService formerNameVisibilityTypeService) throws SQLException {
        super(Profile.class);
        this.phoneTypeService = phoneTypeService;
        this.industryService = industryService;
        this.formerNameVisibilityTypeService = formerNameVisibilityTypeService;
    }




    @Override
    public void save(Profile object) throws SQLException {



        DEFAULT_PHONE_TYPE = phoneTypeService.defaultType().getId();
        DEFAULT_INDUSTRY_TYPE = industryService.defaultType().getId();
        DEFAULT_FORMER_NAME_VISIBILITY_TYPE = formerNameVisibilityTypeService.defaultType().getId();


        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(username, email, phoneNumber, phoneType, " +
                "password, firstName, lastName, formerName, formerNameVisibilityType, headline, " +
                "country, locationInCountry, " +
                "industry, address, dateOfBirth, about, urlToProfile) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        savePs.setString(1, object.getUsername());
        savePs.setString(2, object.getEmail());
        savePs.setString(3, object.getPhoneNumber());

        if (object.getPhoneType() != null){
            savePs.setLong(4, object.getPhoneType());}
        else{
            savePs.setLong(4, DEFAULT_PHONE_TYPE);
        }

        savePs.setString(5, object.getPassword());
        savePs.setString(6, object.getFirstName());
        savePs.setString(7, object.getLastName());

        if (object.getFormerName() != null){
            savePs.setString(8, object.getFormerName());}
        else{
            savePs.setString(8, "");
        }


        if (object.getFormerNameVisibilityType() != null){
            savePs.setLong(9, object.getFormerNameVisibilityType());}
        else{
            savePs.setLong(9, DEFAULT_FORMER_NAME_VISIBILITY_TYPE);
             }

        if (object.getHeadline() != null){
            savePs.setString(10, object.getHeadline());}
        else{
            savePs.setString(10, " ");
        }
        savePs.setString(11, object.getCountry());
        savePs.setString(12, object.getLocationInCountry());

        if (object.getIndustry() != null){
            savePs.setLong(13, object.getIndustry());}
        else
        {
            savePs.setLong(13, DEFAULT_INDUSTRY_TYPE);
        }

        if (object.getAddress() != null){
            savePs.setString(14, object.getAddress());}
        else
        {
            savePs.setString(14, "");
        }

        savePs.setString(15, DateConverter.convertDate(object.getDateOfBirth(), "yyyy-MM-dd"));

        if (object.getAbout() != null){
            savePs.setString(16, object.getAbout());}
        else
        {
            savePs.setString(16, "");
        }

        savePs.setString(17, object.getUrlToProfile());
        savePs.execute();
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
                "formerName NVARCHAR(255) ,"+
                "formerNameVisibilityType BIGINT," +
                "FOREIGN KEY (formerNameVisibilityType) REFERENCES " +  BaseEntity.getTableName(FormerNameVisibilityType.class) + "(id),"+
                "headline NVARCHAR(255),"+
                "country NVARCHAR(255) NOT NULL,"+
                "locationInCountry NVARCHAR(255) NOT NULL,"+
                "industry BIGINT ," +
                "FOREIGN KEY (industry) REFERENCES " +  BaseEntity.getTableName(Industry.class) + "(id),"+
                "address NVARCHAR(255) ,"+
                "dateOfBirth NVARCHAR(255) NOT NULL,"+
                "about TEXT ,"+
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

        if (!resultSet.isBeforeFirst())
        {
            return null;
        }
        resultSet.next();
        return convertSql(resultSet);
    }


    @Override
    public Profile convertSql(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();

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
        profile.setUsername(resultSet.getString("username"));
        try {
            profile.setDateOfBirth(DateConverter.parse(resultSet.getString("dateOfBirth"), "yyyy-MM-dd"));
        }catch (Exception e)
        {
            throw new SQLException("there is a problem with date save in data base");
        }

        profile.setAbout(resultSet.getString("about"));
        profile.setUrlToProfile(resultSet.getString("urlToProfile"));


        return profile;
    }


    public boolean uniqueUsernameEmailPhone(String username,String email,String phone) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("select COUNT(*) from " + this.getTableName() + " where username = ? " +
                "or email = ? or phoneNumber = ?");
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, phone);
        ResultSet resultSet = ps.executeQuery();
        int size =0;
        if (resultSet != null)
        {
            resultSet.next();
            size = resultSet.getInt(1);
        }else
        {
            return false;
        }
        return (size == 0 );
    }

}
