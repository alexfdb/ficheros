package com.alexfdb.crud;

import java.util.HashSet;
import java.util.Set;

import com.alexfdb.model.Empleado;
import com.alexfdb.util.XmlUtil;

public class XmlCrud extends XmlUtil{
    
    private Set<Empleado> empleados;

    public XmlCrud() {
        empleados = new HashSet<>(xmlToSet());
    }

    public boolean create(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return false;
        empleados.add(empleado);
        return setToXml(empleados);
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
            return setToXml(empleados);
        }
        return false;
    }

    public boolean delete(Empleado empleado) {
        if(empleado == null || empleado.getId() == null || empleado.getId().isEmpty()) return false;
        if(empleados.removeIf(e -> e.equals(empleado))) {
            return setToXml(empleados);
        }
        return false;
    }

}