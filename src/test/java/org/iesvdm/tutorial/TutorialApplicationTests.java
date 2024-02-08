package org.iesvdm.tutorial;

import jakarta.transaction.Transactional;
import org.iesvdm.tutorial.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
