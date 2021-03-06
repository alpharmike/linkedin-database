package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.jsonToPojo.AccomplishmentJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccomplishmentRepository extends BaseRepository<Accomplishment,Long>   {


    protected static final String ID = "id";
    protected static final String PROFILE_ID = "profile_id";
    protected static final String SUBJECT = "subject";
    protected static final String DESCRIPTION = "description";
    protected static final String ACCOMPLISHMENT_TYPE = "accomplishment_type";
    protected static final String FILE = "file";

    private final AccomplishmentTypeService accomplishmentTypeService;

    public AccomplishmentRepository(AccomplishmentTypeService accomplishmentTypeService) throws SQLException {
        super(Accomplishment.class);
        this.accomplishmentTypeService = accomplishmentTypeService;
    }


    @Override
    public void save(Accomplishment object) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("insert into "+this.getTableName()+" " +
                "("+PROFILE_ID+","+SUBJECT +","+DESCRIPTION+ ","+ACCOMPLISHMENT_TYPE + ","+FILE + " ) " +
                "values (?,?,?,?,?);");

        ps.setLong(1, object.getProfileId());
        ps.setString(2, object.getSubject());
        ps.setString(3, object.getDescription());
        ps.setLong(4, object.getAccomplishmentType());


        if(object.getFile() != null) {// if file wasn't null we will convert it to byteStream and save it
            Byte[] file = object.getFile();
            byte[] bytes = new byte[file.length];
            for (int i = 0; i < file.length; i++) {
                bytes[i] = file[i];
            }
            ps.setBinaryStream(5, new ByteArrayInputStream((bytes)));
        }else
        {
            ps.setNull(5, Types.BLOB);
        }




        ps.execute();

    }

    @Override
    public void createTable() throws SQLException {
        final String createQuery = String.format("create table if not exists %s ("
                + " " + ID +" bigint not null auto_increment,"
                + " " + PROFILE_ID + " bigint not null,"
                + " " + SUBJECT + " nvarchar(255) not null,"
                + " " + DESCRIPTION + " TEXT not null,"
                + " " + ACCOMPLISHMENT_TYPE + " bigint not null,"
                + " " + FILE + " MEDIUMBLOB,"
                + " primary key (" + ID + "),"
                + " foreign key " + "(" + PROFILE_ID + ")" + " references " + BaseEntity.getTableName(Profile.class) + "(id),"
                + " foreign key " + "(" + ACCOMPLISHMENT_TYPE + ")" + " references " + BaseEntity.getTableName(AccomplishmentType.class) + "(id)"
                + ");", this.tableName);
        PreparedStatement ps = conn.prepareStatement(createQuery);
        ps.execute();

    }


    @Override
    public Accomplishment convertSql(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long profileId = resultSet.getLong(PROFILE_ID);
        String subject = resultSet.getString(SUBJECT);
        String description = resultSet.getString(DESCRIPTION);
        Long accomplishmentType = resultSet.getLong(ACCOMPLISHMENT_TYPE);



        //get file
        InputStream fileStream = resultSet.getBinaryStream(FILE);
        byte[] bytes = null;
        try {
            if (fileStream != null)
                bytes = fileStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Byte[] fileBytes = null;
        if (bytes != null)
        {
            fileBytes = new Byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                fileBytes[i] = bytes[i];
            }
        }




        var accomplishment = new Accomplishment();
        accomplishment.setId(id);
        accomplishment.setAccomplishmentType(accomplishmentType);
        accomplishment.setProfileId(profileId);
        accomplishment.setDescription(description);
        accomplishment.setSubject(subject);
        accomplishment.setFile(fileBytes);


        return accomplishment;

    }

    public List<Accomplishment> findByProfileId(Long id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from " + this.getTableName() + " where "+PROFILE_ID+" = ?");
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();

        List<Accomplishment> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    public void update(Accomplishment object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE  "+this.getTableName()+"  SET " +
                "" + SUBJECT + " = ?," + DESCRIPTION + " = ? ," + ACCOMPLISHMENT_TYPE + " = ? ,"+FILE + " = ? " +
                "where " + ID + " = ?;");


        ps.setString(1, object.getSubject());
        ps.setString(2, object.getDescription());
        ps.setLong(3, object.getAccomplishmentType());



        if(object.getFile() != null) {// if file wasn't null we will convert it to byteStream and save it
            Byte[] file = object.getFile();
            byte[] bytes = new byte[file.length];
            for (int i = 0; i < file.length; i++) {
                bytes[i] = file[i];
            }
            ps.setBinaryStream(4, new ByteArrayInputStream((bytes)));
        }else
        {
            ps.setNull(4, Types.BLOB);
        }
        ps.setLong(5, object.getId());



        ps.execute();
    }

    public void updateWithProfileId(Accomplishment accomplishment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE  "+this.getTableName()+"  SET " +
                "" + SUBJECT + " = ?," + DESCRIPTION + " = ? ," + ACCOMPLISHMENT_TYPE + " = ? ,"+FILE + " = ? " +
                "where " + ID + " = ? and " +  PROFILE_ID + " = ? ;");


        ps.setString(1, accomplishment.getSubject());
        ps.setString(2, accomplishment.getDescription());
        ps.setLong(3, accomplishment.getAccomplishmentType());



        if(accomplishment.getFile() != null) {// if file wasn't null we will convert it to byteStream and save it
            Byte[] file = accomplishment.getFile();
            byte[] bytes = new byte[file.length];
            for (int i = 0; i < file.length; i++) {
                bytes[i] = file[i];
            }
            ps.setBinaryStream(4, new ByteArrayInputStream((bytes)));
        }else
        {
            ps.setNull(4, Types.BLOB);
        }
        ps.setLong(5, accomplishment.getId());
        ps.setLong(6, accomplishment.getProfileId());


        ps.execute();
    }

    public void deleteByIdAndProfileId(Accomplishment accomplishment) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where " + ID + " = ? and  " + PROFILE_ID +" = ?");

        ps.setLong(1, accomplishment.getId());
        ps.setLong(2, accomplishment.getProfileId());
        ps.execute();
    }

    public List<AccomplishmentJson> getAllByProfileIdJson(Long profileId) throws SQLException {
        List<Accomplishment> accomplishmentList = findByProfileId(profileId);

        List<AccomplishmentJson> accomplishmentJsons = new ArrayList<>();

        for (Accomplishment i: accomplishmentList)
        {
            accomplishmentJsons.add(convertToJson(i));
        }

        return accomplishmentJsons;
    }

    public AccomplishmentJson convertToJson(Accomplishment accomplishment) throws SQLException
    {
        AccomplishmentJson accomplishmentJson = AccomplishmentJson.convertToJson(accomplishment);
        String name = accomplishmentTypeService.findById(accomplishmentJson.getAccomplishmentType()).getName();
        accomplishmentJson.setAccomplishmentTypeName(name);
        return accomplishmentJson;

    }
}