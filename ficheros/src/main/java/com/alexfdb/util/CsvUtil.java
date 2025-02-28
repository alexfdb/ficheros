package com.alexfdb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.alexfdb.model.Empleado;

public class CsvUtil {
    
    private String path;
    private File file;

    public CsvUtil() {
        path = "src/main/resources/empleados.csv";
        file = new File(path);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<Empleado> csvToSet() {
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if(datos.length == 5) {
                    Empleado empleado = new Empleado(datos[0], datos[1], datos[2], Float.parseFloat(datos[3]), Boolean.parseBoolean(datos[4]));
                    empleados.add(empleado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public boolean setToCsv(Set<Empleado> empleados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Empleado empleado : empleados) {
                writer.write(empleado.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}