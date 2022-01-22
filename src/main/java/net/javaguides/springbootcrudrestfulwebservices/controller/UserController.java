package net.javaguides.springbootcrudrestfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootcrudrestfulwebservices.entity.User;
import net.javaguides.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import net.javaguides.springbootcrudrestfulwebservices.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id") long userId)
	{
		return userRepository.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User Not Found with id"+userId));
	}
	
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") long userId)
	{
		User existing = userRepository.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User Not Found with id"+userId));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return userRepository.save(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
		User existing = userRepository.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User Not Found with id"+userId));

		userRepository.delete(existing);
		return ResponseEntity.ok().build();
		
	}

}
