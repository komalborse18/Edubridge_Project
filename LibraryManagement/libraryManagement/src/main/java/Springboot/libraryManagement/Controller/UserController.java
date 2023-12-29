package Springboot.libraryManagement.Controller;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Springboot.libraryManagement.Model.User;
import Springboot.libraryManagement.Repository.UserRepository;
import Springboot.libraryManagement.ServiceImplementation.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		Optional<User> existingUser = this.userServiceImpl.getUserByEmail(email);
		Map<String, String> response = new HashMap<String, String>();
		if (existingUser.isPresent()) {
			if (existingUser.get().getPassword().equals(password)) {
				response.put("status", "success");
				response.put("message", "User authenticated");
				response.put("userId", String.valueOf(existingUser.get().getUserId()));
				response.put("designation", existingUser.get().getDesignation());
				response.put("name", existingUser.get().getName());
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			} else {
				response.put("status", "Failed");
				response.put("message", "User password inncorrect");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}
		} else {
			response.put("status", "Failed");
			response.put("message", "User email does not exist");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
	    this.userServiceImpl.addUser(user);

	    // Assuming you want to return some response after signup
	    Optional<User> existingUser = this.userServiceImpl.getUserByEmail(user.getEmail());
	    Map<String, String> response = new HashMap<>();

	    if (existingUser.isPresent()) {
	        response.put("status", "success");
	        response.put("message", "User registered!!");
	        response.put("userId", String.valueOf(existingUser.get().getUserId()));
	        response.put("designation", existingUser.get().getDesignation());
	        response.put("name", existingUser.get().getName());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.put("status", "Failed");
	        response.put("message", "User registration failed");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}
	@GetMapping("/getUsers")
	List<User> getAllUser() {
		return userServiceImpl.readAllUsers();
	}
	
	//READ BOOK BY ID
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResolutionException("User not exist with userId :" + userId));
		return ResponseEntity.ok(user);
	}

	//ADD BOOK
	@PostMapping("/addNewUser")
	void addUser(@RequestBody User user) {
		boolean isAdded = userServiceImpl.addUser(user);
		if (isAdded) {
			System.out.println("New User details Added.");
		} else {
			System.out.println("Failed to add new User details.");
		}
	}

	//UPDATE BOOK
	@PutMapping("/updateBookDetails/{userId}")
	public ResponseEntity<User> updateBook(@PathVariable Integer userId, @RequestBody User userDetails){
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResolutionException("User not exist with userId :" + userId));
		
		user.setName(userDetails.getName());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		user.setDesignation(userDetails.getDesignation());
		User updatedBook = userRepository.save(user);
		return ResponseEntity.ok(updatedBook);
	}

	//DELETE BOOK BY ID
	@DeleteMapping("/deleteUserDetails/{userId}")
	void deleteUser(@PathVariable("userId") Integer userId) {
		boolean isDeleted = userServiceImpl.deleteUserDetails(userId);
		if (isDeleted) {
			System.out.println("User details deleted.");
		} else {
			System.out.println("Failed to delete User details.");
		}
	}
	
}
