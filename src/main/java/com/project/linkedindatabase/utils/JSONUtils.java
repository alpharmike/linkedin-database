package com.project.linkedindatabase.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.linkedindatabase.domain.Profile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JSONUtils<T> {
    final Class<T> typeParameterClass;

    public JSONUtils(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T convertJsonToObject(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(filepath).toFile(), typeParameterClass);
    }

    public List<T> convertJsonToObjects(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Collections.singletonList(mapper.readValue(Paths.get(filepath).toFile(), this.typeParameterClass));
    }
}
