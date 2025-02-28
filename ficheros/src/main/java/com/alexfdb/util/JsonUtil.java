package com.alexfdb.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.alexfdb.model.Empleado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    
    private String path;
    private File file;
    ObjectMapper objectMapper;

    public JsonUtil() {
        path = "src/main/resources/empleados.json";
        file = new File(path);
        objectMapper = new ObjectMapper();
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Set<Empleado> jsonToSet() {
        try {
            return objectMapper.readValue(file, new TypeReference<Set<Empleado>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public boolean setToJson(Set<Empleado> empleados) {
        try {
            objectMapper.writeValue(file, empleados);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}