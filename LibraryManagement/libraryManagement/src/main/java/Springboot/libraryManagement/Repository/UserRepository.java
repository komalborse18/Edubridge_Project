package Springboot.libraryManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Springboot.libraryManagement.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT * FROM user u WHERE u.email = ?1",nativeQuery = true )
	public Optional<User> findByEmail(String email);

	public User findByUserId(Integer userId);

	public void deleteByUserId(Integer userId);
}
