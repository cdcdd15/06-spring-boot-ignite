package com.cosmin.ignite.beans;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cosmin.ignite.model.EmployeeDTO;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeIgniteService employeeIgniteService;

	@GetMapping("/hello-world")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello world updated 1", HttpStatus.OK);
	}

	@PostMapping("/ignite")
	public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO dto) {
		int id = new Random().nextInt();
		id = Math.abs(id);
		dto.setId(id);
		this.employeeIgniteService.insertDTOs(dto);
		return new ResponseEntity<EmployeeDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/ignite/process-all-records-on-server-nodes")
	public ResponseEntity<Boolean> processAllRecordsOnServerNodes() {
		boolean b = this.employeeIgniteService.processAllRecordsOnServerNodes();
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

	@GetMapping("/ignite")
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<EmployeeDTO> list = this.employeeIgniteService.findAll();
		return new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/ignite")
	public ResponseEntity<Boolean> deleteCache() {
		boolean b = this.employeeIgniteService.deleteCache();
		return new ResponseEntity<Boolean>(b, HttpStatus.NO_CONTENT);
	}
	
}