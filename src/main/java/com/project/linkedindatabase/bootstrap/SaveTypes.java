package com.project.linkedindatabase.bootstrap;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class SaveTypes {

    public static final String TYPE = "type";





    public static List<String>   readAccomplishmentType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/AccomplishmentType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }


    public static List<String>   readBackgroundType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/BackgroundType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readConnectType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/ConnectType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readSkillLevel() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/EndorsementType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readFormerNameVisibilityType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/FormerNameVisibilityType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readIndustryType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/Industry.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readLanguageLevel() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/LanguageLevel.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readNotificationType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/NotificationType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readPhoneType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/PhoneType.json").toAbsolutePath();


        List<String> itemsObject = getType(filePath);



        return itemsObject;
    }

    public static List<String>   readRelationKnowledge() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/RelationKnowledge.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    public static List<String>   readShowPostType() throws Exception {
        SaveTypes saveTypes  = null;

        Path filePath = Paths.get(new File(".").getCanonicalPath() +"/src/main/data/ShowPostType.json").toAbsolutePath();

        List<String> itemsObject = getType(filePath);


        return itemsObject;
    }

    private static List<String> getType(Path filePath) throws FileNotFoundException, ParseException {
        FileReader reader = new FileReader(filePath.toString());
        JSONParser jsonParser = new JSONParser(reader);
        LinkedHashMap<String, Object> json = jsonParser.object();
        return (List<String>) json.get(TYPE);
    }

}
