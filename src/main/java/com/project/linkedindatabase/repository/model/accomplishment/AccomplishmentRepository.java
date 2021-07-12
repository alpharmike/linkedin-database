package com.project.linkedindatabase.repository.model.accomplishment;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentRepository extends BaseRepository<Accomplishment,Long>   {


    protected static final String ID = "id";
    protected static final String PROFILE_ID = "profile_id";
    protected static final String SUBJECT = "subject";
    protected static final String DESCRIPTION = "description";
    protected static final String ACCOMPLISHMENT_TYPE = "accomplishment_type";
    protected static final String FILE = "file";

    public AccomplishmentRepository() throws SQLException {
        super(Accomplishment.class);
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
            ps.setBinaryStream(5,  null);
        }




        ResultSet resultSet = ps.executeQuery();

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
}