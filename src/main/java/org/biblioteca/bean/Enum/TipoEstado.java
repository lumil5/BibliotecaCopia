package org.biblioteca.bean.Enum;

public enum TipoEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    SUSPENDIDO("Suspendido");

    private final String descripcion;

    TipoEstado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}