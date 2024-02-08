package org.iesvdm.tutorial;

import jakarta.transaction.Transactional;
import org.iesvdm.tutorial.modelo.Comentario;
import org.iesvdm.tutorial.modelo.Tutorial;
import org.iesvdm.tutorial.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootTest
class TutorialApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    @Test
    void contextLoads() {
    }




    //cuando es lazy hay que poner transactional
    @Transactional
    @Test
    void pruebaOneToMany(){

        var listaTutoriales = tutorialRepository.findAll();
        listaTutoriales.forEach(System.out::println);

    }

    @Test
    void pruebaGrabarOneToMany(){
        //hay que poner @builder en la clase
       Tutorial tutorial = Tutorial.builder()
                       .descripcion("descripcion").titulo("hola").comentarios(new HashSet<>()).build();
       //permite instanciar clases sin declarar constructores específicos.

        Comentario comentario = Comentario.builder().texto("un comentario").build();
        tutorial.addComentario(comentario);

        tutorialRepository.save(tutorial);


    }

    @Test
    @Transactional //porque es lazy
    void pruebaEliminarOneToMany(){
        var optionalTutorial = this.tutorialRepository.findById(1l);
        optionalTutorial.ifPresent(tutorial -> {tutorial.getComentarios().forEach(System.out::println);
            var optionalcomentario  = tutorial.getComentarios().stream().findFirst();
            tutorial.removeComentario(optionalcomentario.get());
            this.tutorialRepository.save(tutorial);

        });


    }

}
