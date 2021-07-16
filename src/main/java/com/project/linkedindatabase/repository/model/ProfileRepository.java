package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;
import com.project.linkedindatabase.service.types.IndustryService;
import com.project.linkedindatabase.service.types.PhoneTypeService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProfileRepository extends BaseRepository<Profile,Long>   {

    public Long DEFAULT_PHONE_TYPE;
    public Long DEFAULT_INDUSTRY_TYPE;
    public Long DEFAULT_FORMER_NAME_VISIBILITY_TYPE;

    private final PhoneTypeService phoneTypeService;
    private final IndustryService industryService;
    private final FormerNameVisibilityTypeService formerNameVisibilityTypeService;
    private final BackgroundService backgroundService;






    public ProfileRepository(PhoneTypeService phoneTypeService, IndustryService industryService,
                             FormerNameVisibilityTypeService formerNameVisibilityTypeService, BackgroundService backgroundService) throws SQLException {
        super(Profile.class);
        this.phoneTypeService = phoneTypeService;
        this.industryService = industryService;
        this.formerNameVisibilityTypeService = formerNameVisibilityTypeService;
        this.backgroundService = backgroundService;
    }


    @Override
    public void save(Profile object) throws SQLException {



        DEFAULT_PHONE_TYPE = phoneTypeService.defaultType().getId();
        DEFAULT_INDUSTRY_TYPE = industryService.defaultType().getId();
        DEFAULT_FORMER_NAME_VISIBILITY_TYPE = formerNameVisibilityTypeService.defaultType().getId();


        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(username, email, phoneNumber, phoneType, " +
                "password, firstName, lastName, formerName, formerNameVisibilityType, headline, " +
                "country, locationInCountry, " +
                "industry, address, dateOfBirth, about, urlToProfile,showCurrentEducationId,currentEducationId,showCurrentPositionId,currentPositionId) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");



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
            savePs.setString(10, "");
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

        if (object.getShowCurrentEducationId() != null){
            savePs.setBoolean(18, object.getShowCurrentEducationId());}
        else
        {
            savePs.setBoolean(18, false);
        }

        if (object.getCurrentEducationId() != null){
            savePs.setLong(19, object.getCurrentEducationId());}
        else
        {
            savePs.setNull(19, Types.BIGINT);
        }

        if (object.getShowCurrentPositionId() != null){
            savePs.setBoolean(20, object.getShowCurrentPositionId());}
        else
        {
            savePs.setBoolean(20, false);
        }

        if (object.getCurrentPositionId() != null){
            savePs.setLong(21, object.getCurrentPositionId());}
        else
        {
            savePs.setNull(21, Types.BIGINT);
        }


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
                "showCurrentEducationId BIT ," +
                "currentEducationId BIGINT ," +
                "showCurrentPositionId BIT ," +
                "currentPositionId BIGINT ," +
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

        profile.setShowCurrentEducationId(resultSet.getBoolean("showCurrentEducationId"));
        profile.setCurrentEducationId(resultSet.getLong("currentEducationId"));
        profile.setShowCurrentPositionId(resultSet.getBoolean("showCurrentPositionId"));
        profile.setCurrentPositionId(resultSet.getLong("currentPositionId"));


        return profile;
    }


    public boolean uniqueUsernameEmailPhone(String username,String email,String phone) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("select COUNT(*) from " + this.tableName + " where username = ? " +
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

    public List<Profile> searchOtherBaseCurrentCompany(String companyName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select " + this.tableName +".* from "+ this.tableName +" , " +
                BaseEntity.getTableName(Background.class) + " , "  + BaseEntity.getTableName(BackgroundType.class) + " " +
                " where "+ this.tableName +".id = " + BaseEntity.getTableName(Background.class) + ".profileId and " +
                this.tableName +".currentPositionId = " + BaseEntity.getTableName(Background.class) + ".id and" +
                " " + BaseEntity.getTableName(BackgroundType.class) + ".id = " + BaseEntity.getTableName(Background.class) +
                ".backgroundType and " + BaseEntity.getTableName(BackgroundType.class) + ".name = 'Work experience' and" +
                " " + BaseEntity.getTableName(Background.class) + ".title like ?  ");
        ps.setString(1, companyName+"%");


        System.out.println(ps.toString());

        ResultSet resultSet = ps.executeQuery();
        List<Profile> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;

    }

    public void update(Profile profile) throws SQLException {

        DEFAULT_PHONE_TYPE = phoneTypeService.defaultType().getId();
        DEFAULT_INDUSTRY_TYPE = industryService.defaultType().getId();
        DEFAULT_FORMER_NAME_VISIBILITY_TYPE = formerNameVisibilityTypeService.defaultType().getId();

        PreparedStatement savePs = this.conn.prepareStatement("UPDATE " + this.tableName + "  set phoneType = ?, " +
                " firstName = ?, lastName = ? , formerName = ?, formerNameVisibilityType = ? , headline = ?, " +
                "country = ? , locationInCountry = ? , industry = ?, address = ?, dateOfBirth = ?, about = ?, urlToProfile = ?," +
                "showCurrentEducationId = ? ,currentEducationId = ?,showCurrentPositionId = ?,currentPositionId = ?" +
                " where id = ?;");




        if (profile.getPhoneType() != null){
            savePs.setLong(1, profile.getPhoneType());}
        else{
            savePs.setLong(1, DEFAULT_PHONE_TYPE);
        }


        savePs.setString(2, profile.getFirstName());
        savePs.setString(3, profile.getLastName());

        if (profile.getFormerName() != null){
            savePs.setString(4, profile.getFormerName());}
        else{
            savePs.setString(4, "");
        }


        if (profile.getFormerNameVisibilityType() != null){
            savePs.setLong(5, profile.getFormerNameVisibilityType());}
        else{
            savePs.setLong(5, DEFAULT_FORMER_NAME_VISIBILITY_TYPE);
        }

        if (profile.getHeadline() != null){
            savePs.setString(6, profile.getHeadline());}
        else{
            savePs.setString(6, "");
        }
        savePs.setString(7, profile.getCountry());
        savePs.setString(8, profile.getLocationInCountry());

        if (profile.getIndustry() != null){
            savePs.setLong(9, profile.getIndustry());}
        else
        {
            savePs.setLong(9, DEFAULT_INDUSTRY_TYPE);
        }

        if (profile.getAddress() != null){
            savePs.setString(10, profile.getAddress());}
        else
        {
            savePs.setString(10, "");
        }

        savePs.setString(11, DateConverter.convertDate(profile.getDateOfBirth(), "yyyy-MM-dd"));

        if (profile.getAbout() != null){
            savePs.setString(12, profile.getAbout());}
        else
        {
            savePs.setString(12, "");
        }

        savePs.setString(13, profile.getUrlToProfile());

        if (profile.getShowCurrentEducationId() != null){
            savePs.setBoolean(14, profile.getShowCurrentEducationId());}
        else
        {
            savePs.setBoolean(14, false);
        }

        if (profile.getCurrentEducationId() != null){
            savePs.setLong(15, profile.getCurrentEducationId());}
        else
        {
            savePs.setNull(15, Types.BIGINT);
        }

        if (profile.getShowCurrentPositionId() != null){
            savePs.setBoolean(16, profile.getShowCurrentPositionId());}
        else
        {
            savePs.setBoolean(16, false);
        }

        if (profile.getCurrentPositionId() != null){
            savePs.setLong(17, profile.getCurrentPositionId());}
        else
        {
            savePs.setNull(17, Types.BIGINT);
        }

        savePs.setLong(18, profile.getId());

        System.out.println(savePs.toString());
        savePs.execute();
    }

    public List<Profile> searchOtherBaseLanguage( String language) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.tableName+" as pf where exists " +
                "(select * from "+BaseEntity.getTableName(Language.class)+" as lan where lan.language like ? and lan.profile_id = pf.id )");
        ps.setString(1, language+"%");


        System.out.println(ps.toString());
        ResultSet resultSet = ps.executeQuery();
        List<Profile> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }


    public List<Profile> searchOtherBaseLocation( String location) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.tableName+" where locationInCountry like ? or country like ? or CONCAT( country,' ', locationInCountry) like ?");
        ps.setString(1, location+"%");
        ps.setString(2, location+"%");
        ps.setString(3, location+"%");


        ResultSet resultSet = ps.executeQuery();
        List<Profile> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    public List<Profile> searchOtherBaseName(String name) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("select * from "+this.tableName+" where firstName like ? " +
                "or lastName like ? or CONCAT( firstName,' ', lastName) like ? or username like ?");
        ps.setString(1, name+"%");
        ps.setString(2, name+"%");
        ps.setString(3, name+"%");
        ps.setString(4, name+"%");

        ResultSet resultSet = ps.executeQuery();
        List<Profile> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }


    public ProfileJson getProfileByIdJson(Long id) throws Exception
    {
        Profile profile = findById(id);
        return convertToJson(profile);
    }


    public ProfileJson convertToJson(Profile profile) throws Exception {
        ProfileJson profileJson = ProfileJson.convertToJson(profile);

        if(profileJson.getCurrentEducationId() != null) {
            BackgroundJson education = backgroundService.findByIdAndProfileIdJson(profileJson.getId(), profileJson.getCurrentEducationId());
            profileJson.setCurrentEducation(education);
        }
        if(profileJson.getCurrentPositionId() != null){
            BackgroundJson work = backgroundService.findByIdAndProfileIdJson(profileJson.getId(),profileJson.getCurrentPositionId());
            profileJson.setCurrentPosition(work);}

        FormerNameVisibilityType fnvt = formerNameVisibilityTypeService.findById(profileJson.getFormerNameVisibilityType());
        profileJson.setFormerNameVisibilityTypeName(fnvt.getName());

        PhoneType phoneType = phoneTypeService.findById(profileJson.getPhoneType());
        profileJson.setPhoneTypeName(phoneType.getName());

        return profileJson;

    }
    public void setCurrentPosition(Profile profile) throws Exception {
        BackgroundJson backgroundJson = backgroundService.findByIdAndProfileIdJson(profile.getId(),profile.getCurrentPositionId());
        if (backgroundJson == null)
            return;
        if (!backgroundJson.getBackgroundTypeName().equals("Work experience"))
            return;
        PreparedStatement savePs = this.conn.prepareStatement("UPDATE " + this.tableName + " set currentPositionId = ?" +
                " where id = ?;");

        savePs.setLong(1,profile.getCurrentPositionId());
        savePs.setLong(2,profile.getId());

        savePs.execute();

    }
    public void setCurrentEducation(Profile profile) throws Exception {
        BackgroundJson backgroundJson = backgroundService.findByIdAndProfileIdJson(profile.getId(),profile.getCurrentEducationId());
        if (backgroundJson == null)
            return;
        if (!backgroundJson.getBackgroundTypeName().equals("Education"))
            return;
        PreparedStatement savePs = this.conn.prepareStatement("UPDATE " + this.tableName + " set  currentEducationId = ?" +
                " where id = ?;");
        savePs.setLong(1,profile.getCurrentEducationId());
        savePs.setLong(2,profile.getId());

        savePs.execute();

    }
    public void removeCurrentPosition(Profile profile) throws Exception {
        PreparedStatement savePs = this.conn.prepareStatement("UPDATE " + this.tableName + " set currentPositionId = ? " +
                " where id = ?;");

        savePs.setLong(1,0L);
        savePs.setLong(2,profile.getId());


        savePs.execute();
    }
    public void removeCurrentEducation(Profile profile) throws Exception {
        PreparedStatement savePs = this.conn.prepareStatement("UPDATE " + this.tableName + " set currentEducationId = ?" +
                " where id = ?;");

        savePs.setLong(1,0L);
        savePs.setLong(2,profile.getId());
        System.out.println(savePs.toString());
        savePs.execute();

    }



}
