package dev.cris.instructores.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cris.instructores.Models.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
}
