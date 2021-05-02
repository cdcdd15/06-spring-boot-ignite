package com.cosmin.ignite.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoJpaResource {

	@Autowired
	private EmployeeIgniteService employeeIgniteService;

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
