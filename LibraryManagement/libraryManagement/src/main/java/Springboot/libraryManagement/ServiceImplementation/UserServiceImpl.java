package Springboot.libraryManagement.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Springboot.libraryManagement.Model.Book;
import Springboot.libraryManagement.Model.User;
import Springboot.libraryManagement.Repository.UserRepository;
import Springboot.libraryManagement.Service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	public boolean addUser(User user) {

		this.userRepository.save(user);
		return false;
	}

	public Optional<User> getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	//READ ALL Users
	@Override
	public List<User> readAllUsers() {
		return userRepository.findAll();
		// TODO Auto-generated method stub
	}
	public boolean updateuserDetails(Integer userId, User user) {
		User userObjFromDb = userRepository.findByUserId(userId); 
		if(userObjFromDb != null)	{
			userObjFromDb.setEmail(user.getEmail());
			userRepository.save(userObjFromDb);
			return true;
		}
		return false;
	}

	public boolean addUserDetails(User user) {
		if (userRepository.save(user) != null) {
			emailService.sendEmail("sonykonda1@gmail.com", "Book Added Successfully", "Congratulations...!\nNew User Added Successfully.\n\tUser Details: \n\t\t" + user);
			return true;
		} else {
		return false;
		}
	}

	public boolean deleteUserDetails(Integer userId) {
		User userObjFromDb = userRepository.findByUserId(userId);
		if(userObjFromDb != null)	{
			userRepository.deleteByUserId(userId);
			return true;
		}
		return false;
	}
	//READ BOOK BY ID
	public User getUserByUserId(Integer userId) {
		return userRepository.findByUserId(userId);
	}
}
