package org.biblioteca.bean.entity.Biblioteca;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libro_autor", schema = "biblioteca")
@Builder
public class LibroAutor implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private LibroAutorId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idLibro")
    @JoinColumn(name = "idLibro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idAutor")
    @JoinColumn(name = "idAutor")
    private Autor autor;
}
