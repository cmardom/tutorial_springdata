package org.iesvdm.tutorial.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "Tutorial", schema = "tutorial_bbdd", indexes = {@Index(name = "index_titulo", columnList = "titulo", unique = false)})
public class Tutorial {

    @Id
    @EqualsAndHashCode.Include // se pone aqui para que solo se lo haga al id
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
    @OneToMany(mappedBy = "tutorial", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //mappedBy: el nombre del atributo: minuscula
    //fetch type lazy: crea proxys, no ejecuta toda la bbdd. Hay que poner Transactional
    //fetch type eager: carga toda la bbdd
    @ToString.Exclude //para que no lo imprima
    private Set<Comentario> comentarios;


    //helper es un metodo de ayuda. Este añade comentarios a la coleccion de comentarios bien inicializados
    //un comentario qeu no tiene padre tutorial
    //como la relacion es bidireccional, se necesita que el comentario tenga un padre
    //hay que setear tutorial
    //si no se hace esto, hay que poner tutorial en toda la cadena de construccion del builder

    public Tutorial addComentario (Comentario comentario){
        comentario.setTutorial(this);
        this.comentarios.add(comentario);
        return this;
    }

    public Tutorial removeComentario (Comentario comentario){
        this.comentarios.remove(comentario);
        comentario.setTutorial(null);
        return this;
    }
}
