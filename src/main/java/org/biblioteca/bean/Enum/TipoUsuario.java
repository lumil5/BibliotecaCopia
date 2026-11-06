package org.biblioteca.bean.Enum;

public enum TipoUsuario {
    ESTUDIANTE("Estudiante"),
    DOCENTE("Docente"),
    ADMINISTRATIVO("Administrativo"),
    EXTERNO("Externo");

    private final String descripcion;

    TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    // Noe requiere setter porque lo hace automaticamente al inicializar el enum
    public String getDescripcion() {
        return descripcion;
    }

    // metodo que permite obtener el valor de la descripcion, siempre encuanto se
    // utilice ordinales
    // public static TipoCliente getByDescripcion(String descripcion) {
    // for (TipoCliente tipo : values()) {
    // if (tipo.getDescripcion().equals(descripcion)) {
    // return tipo;
    // }
    // }
    // return null;
    // }

}