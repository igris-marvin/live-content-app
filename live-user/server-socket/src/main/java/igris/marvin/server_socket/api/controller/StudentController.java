package igris.marvin.server_socket.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igris.marvin.server_socket.database.entity.Student;
import igris.marvin.server_socket.database.repository.StudentRepository;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    public StudentRepository repo;

    @GetMapping("/{id}")
    public Mono<Student> getStudent(
        @PathVariable("id") String id
    ) {
        return repo.findByRefId(id);
    }
}
