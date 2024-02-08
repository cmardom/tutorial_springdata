
package org.iesvdm.tutorial.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para sql
    private long id;

    private String texto;

    @ManyToOne
    //comentario es el lado del mucho (un tutorial, muchos comentarios)
    @JoinColumn (name = "tutorial_id_fk", nullable=false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TUTO"))
    private Tutorial tutorial;
}

