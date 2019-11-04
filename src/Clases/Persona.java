package Clases;


public class Persona {
    
    private String nombre;
    private String apellido;
    private String nit;

    public Persona(String nombre, String apellido, String nit) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
}



