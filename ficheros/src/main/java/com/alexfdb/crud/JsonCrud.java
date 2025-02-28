package com.alexfdb.crud;

import java.util.HashSet;
import java.util.Set;

import com.alexfdb.model.Empleado;
import com.alexfdb.util.JsonUtil;

public class JsonCrud extends JsonUtil{
    
    private Set<Empleado> empleados;

    public JsonCrud() {
        empleados = new HashSet<>(jsonToSet());
    }

    public boolean create(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return false;
        empleados.add(empleado);
        return setToJson(empleados);
    }

    public Empleado read(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return null;
        for (Empleado e : empleados) {
            if(e.equals(empleado)) {
                return e;
            }
        }
        return null;
    }

    public boolean update(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return false;
        if(empleados.removeIf(e -> e.equals(empleado))) {
            empleados.add(empleado);
            return setToJson(empleados);
        }
        return false;
    }

    public boolean delete(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return false;
        if(empleados.removeIf(e -> e.equals(empleado))) {
            return setToJson(empleados);
        }
        return false;
    }

}