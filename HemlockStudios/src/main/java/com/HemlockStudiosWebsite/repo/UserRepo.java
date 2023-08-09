package com.HemlockStudiosWebsite.repo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.HemlockStudiosWebsite.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    
    public Optional<User> findByUsername(String username);

    @Query(value="select * from user where email = ?1 and password = ?2", nativeQuery = true)
    public User signIn(String email, String password);

    @Query(value="select * from user where user_Name = ?1", nativeQuery = true) //make sure field matches database exactly
    public User findByUserName(String email);

    @Query(value="select * from user where (username = ?1 or email = ?2) and password = ?3", nativeQuery = true) 
    public User findByUsernameOrEmailAndPassword(String username, String email, String password);

    @Query(value="select * from user where (username = ?1 or email = ?2)", nativeQuery = true) 
    public User findByUsernameOrEmail(String username, String email);

    @Query(value="select * from user where email = ?1", nativeQuery = true)
    public User findByEmail(String email);

} 