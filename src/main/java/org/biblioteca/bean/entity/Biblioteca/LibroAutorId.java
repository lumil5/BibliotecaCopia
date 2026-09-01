package org.biblioteca.bean.entity.Biblioteca;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LibroAutorId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "idLibro")
    private Integer idLibro;

    @Column(name = "idAutor")
    private Integer idAutor;
}
