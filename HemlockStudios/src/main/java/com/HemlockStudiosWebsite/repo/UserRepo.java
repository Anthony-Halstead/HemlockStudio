// This code is defining a repository interface called `UserRepo` for accessing and manipulating data
// related to the `User` entity in a Spring Boot application.
package com.HemlockStudiosWebsite.repo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.HemlockStudiosWebsite.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    
  /**
   * The function findByUsername searches for a user by their username and returns an Optional object
   * that may contain the user if found.
   * 
   * @param username The username parameter is a String that represents the username of the user you
   * want to find.
   * @return The method is returning an Optional object that contains a User object.
   */
    public Optional<User> findByUsername(String username);

    /**
     * The function "signIn" queries the database to find a user with a specific email and password.
     * 
     * @param email The email parameter is used to specify the email address of the user you want to
     * sign in.
     * @param password The password parameter is a string that represents the user's password.
     * @return The method is returning a User object.
     */
    @Query(value="select * from user where email = ?1 and password = ?2", nativeQuery = true)
    public User signIn(String email, String password);

    /**
     * The function `findByUserName` in the `User` repository class retrieves a user from the database
     * based on their username.
     * 
     * @param email The email parameter is used to search for a user in the database based on their
     * email address.
     * @return The method is returning a User object.
     */
    @Query(value="select * from user where user_Name = ?1", nativeQuery = true) //make sure field matches database exactly
    public User findByUserName(String email);

   /**
    * The function `findByUsernameOrEmailAndPassword` in the `User` repository class retrieves a user
    * from the database based on their username or email and password.
    * 
    * @param username The username parameter is used to search for a user by their username. If a user
    * with the specified username is found, it will be returned.
    * @param email The email parameter is used to search for a user by their email address.
    * @param password The password parameter is used to specify the password that will be used to
    * search for a user in the database.
    * @return The method is returning a User object.
    */
    @Query(value="select * from user where (username = ?1 or email = ?2) and password = ?3", nativeQuery = true) 
    public User findByUsernameOrEmailAndPassword(String username, String email, String password);

   /**
    * This function retrieves a user from the database based on their username or email.
    * 
    * @param username The username parameter is used to search for a user by their username. It is a
    * String type parameter.
    * @param email The email parameter is a string that represents the email address of a user.
    * @return The method is returning a User object.
    */
    @Query(value="select * from user where (username = ?1 or email = ?2)", nativeQuery = true) 
    public User findByUsernameOrEmail(String username, String email);

    /**
     * The function `findByEmail` in the `User` repository class retrieves a user from the database
     * based on their email.
     * 
     * @param email The email parameter is a string that represents the email address of the user you
     * want to find.
     * @return The method `findByEmail` is returning a `User` object.
     */
    @Query(value="select * from user where email = ?1", nativeQuery = true)
    public User findByEmail(String email);

} 