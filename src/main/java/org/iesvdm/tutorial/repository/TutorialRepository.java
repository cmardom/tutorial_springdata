package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.modelo.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//long por el tipo de id

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
