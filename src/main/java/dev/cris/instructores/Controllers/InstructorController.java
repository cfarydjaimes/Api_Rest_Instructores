package dev.cris.instructores.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import dev.cris.instructores.Excepciones.ResourceNotFoundException;
import dev.cris.instructores.Models.Instructor;
import dev.cris.instructores.Repositories.InstructorRepository;

@RestController
public class InstructorController {
    
    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructores")
    public List<Instructor> listarInstructores(){
        return instructorRepository.findAll();
    }

    @GetMapping("/instructores/{id}")
    public ResponseEntity<Instructor> verDetallesDelInstructor(@PathVariable Long id){
		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor con el ID : " + id + " no encontrado"));
		return ResponseEntity.ok().body(instructor);
	}

    @PostMapping("/instructores")
    public Instructor guardarInstructor(@Valid @RequestBody Instructor instructor){
        return instructorRepository.save(instructor);
    }

    @PutMapping("/instructores")
    public ResponseEntity<Instructor> actualizarInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructorBody){
        Instructor instructor = instructorRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("Instructor No encontrado"));
        instructor.setEmail(instructorBody.getEmail());
        return ResponseEntity.ok(instructorRepository.save(instructor));
    }

    @DeleteMapping("/instructores/{id}")
    public Map<String, Boolean> eliminarInstructor(@PathVariable Long id){
        Instructor instructor = instructorRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("Instructor No encontrado"));
        instructorRepository.delete(instructor);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Instructor Eliminado", Boolean.TRUE);
        return response;
    }
}
