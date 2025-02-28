package com.alexfdb.model;
import java.util.Objects;

public class Empleado {
    
    private String id;
    private String nombre;
    private String puesto;
    private float salario;
    private boolean alta;

    public Empleado() {
    }

    public Empleado(String id) {
        this.id = id;
    }

    public Empleado(String id, String nombre, String puesto, float salario, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.alta = alta;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isAlta() {
        return this.alta;
    }

    public boolean getAlta() {
        return this.alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", puesto='" + getPuesto() + "'" +
            ", salario='" + getSalario() + "'" +
            ", alta='" + isAlta() + "'" +
            "}";
    }

}