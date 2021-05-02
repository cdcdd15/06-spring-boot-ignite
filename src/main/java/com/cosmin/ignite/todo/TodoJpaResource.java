package com.cosmin.ignite.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cosmin.rest.webservices.restfulwebservices.todo.Todo;

@RestController
public class TodoJpaResource {

	@Autowired
	private TodoJpaRepository todoJpaRepository;

	@GetMapping("/hello-world")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello world updated 1", HttpStatus.OK);
	}
	
//	@GetMapping("/jpa/users/{username}/todos")
//	public List<Todo> getAllTodos(@PathVariable String username){
//		return todoJpaRepository.findAll();
//	}
//
//	@GetMapping("/jpa/users/todos/{id}")
//	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
//		return todoJpaRepository.findById(id).get();
//	}
//
//	@DeleteMapping("/jpa/delete-all")
//	public ResponseEntity<Void> deleteAll() {
//
//		todoJpaRepository.deleteAll();
//
//		return ResponseEntity.noContent().build();
//	}
//
//	@PostMapping("/jpa/users/todos")
//	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
//
//		todo.setId(-1L);
//
//		Todo createdTodo = todoJpaRepository.save(todo);
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();
//	}

}
