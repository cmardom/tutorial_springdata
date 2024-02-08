package org.iesvdm.tutorial.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tutorial", schema = "tutorial_bbdd", indexes = {@Index(name = "index_titulo", columnList = "titulo", unique = false)})
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para sql
                                            // SEQUENCE para postgres
    private long id;

    @Column (name = "titulo", length = 50)
    private String titulo;

    @Column (name = "descripcion", length = 150)
    private String descripcion;

    private boolean publicado;

    @Column (name = "fecha_publicacion", nullable = true)
    private Date fecha_publicacion;

    //un tutorial, muchos comentarios
    @OneToMany(mappedBy = "tutorial", fetch = FetchType.LAZY)
    //mappedBy: el nombre del atributo: minuscula
    //fetch type lazy: crea proxys, no ejecuta toda la bbdd. Hay que poner Transactional
    //fetch type eager: carga toda la bbdd
    @ToString.Exclude //para que no lo imprima
    private List<Comentario> comentarios;
}
