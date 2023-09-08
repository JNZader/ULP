package Entidades;

public class Materia {
    private int idMateria, año;
    private String nombre;
    private boolean estado;

    public Materia() {
    }

    public Materia(int año, String nombre, boolean estado) {
        this.año = año;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Materia(int idMateria, int año, String nombre, boolean estado) {
        this.idMateria = idMateria;
        this.año = año;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", a\u00f1o=" + año + ", nombre=" + nombre + ", estado=" + estado + '}';
    }
    
}
