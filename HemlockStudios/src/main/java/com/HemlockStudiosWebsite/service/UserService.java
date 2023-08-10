/**
 * The UserService class is responsible for handling user-related operations such as saving, updating,
 * finding, and deleting users, as well as managing user authentication and authorization.
 */
package com.HemlockStudiosWebsite.service;
import com.HemlockStudiosWebsite.repo.UserRepo;
import com.HemlockStudiosWebsite.entity.User;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

   /**
    * The function saves a user object in the user repository and returns the saved user object.
    * 
    * @param user The user object that needs to be saved in the database.
    * @return The method is returning the saved User object.
    */
    public User save(User user) {
	    return userRepo.save(user);
	}
	/**
     * The function updates a user's information in a database if the user exists, otherwise it throws an
     * exception.
     * 
     * @param user The "user" parameter is an object of type User, which represents a user account.
     * @return The method is returning a User object.
     */
    public User update(User user) throws Exception {
        if(user.getId() != null) {
	        return userRepo.save(user);
        }

        throw new AccountNotFoundException("Account does not exist! id not present");
	}
    
    /**
     * The function findById takes an Integer userId as input and returns the User object with the
     * corresponding userId if it exists in the userRepo, otherwise it throws an Error.
     * 
     * @param userId The userId parameter is an Integer representing the unique identifier of the user
     * that needs to be found.
     * @return The method is returning a User object if the user with the specified userId is found in
     * the userRepo. If the user is not found, it throws an Error with the message "No user id
     * present".
     */
    public User findById(Integer userId) throws Error {
        if(userRepo.findById(userId).isPresent()) {
            return userRepo.findById(userId).get();
        }
        throw new Error("No user id present");
    }
	/**
     * The function findByEmail takes an email as input and returns a User object from the user repository
     * based on the email.
     * 
     * @param email The email parameter is a string that represents the email address of the user you want
     * to find.
     * @return The method is returning a User object.
     */
    public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	/**
     * The function returns a list of all users.
     * 
     * @return A List of User objects is being returned.
     */
    public List<User> findAll() {
		return userRepo.findAll();
	}

   /**
    * The function signIn takes a User object as input, attempts to sign in the user by checking their
    * email and password against the user repository, and returns the logged in user if successful,
    * otherwise throws an exception.
    * 
    * @param user The user object contains the email and password of the user trying to sign in.
    * @return The method is returning a User object.
    */
    public User signIn(User user) throws Exception {
		
		User loggedInUser = userRepo.signIn(user.getEmail(), user.getPassword());
		if(loggedInUser == null) {
			throw new Exception("User was not found");
		}
		return loggedInUser;
	}

    /**
     * The function deletes a user from the repository based on their ID.
     * 
     * @param id The "id" parameter is an Integer that represents the unique identifier of the entity
     * that needs to be deleted from the database.
     */
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
	}

    /**
     * The function returns a list of all users from the user repository.
     * 
     * @return A List of User objects is being returned.
     */
    public List<User> getAll()
    {
        return userRepo.findAll();
    }

	/**
     * The function findByUsername searches for a user in the user repository by their username and throws
     * an exception if the user is not found.
     * 
     * @param username The parameter "username" is a String that represents the username of the user we
     * are trying to find.
     * @return The method is returning a User object.
     */
    public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}


   /**
    * The function retrieves the notification status of the current user.
    * 
    * @return The method is returning a Boolean value, which represents the notification status of the
    * current user.
    */
    public Boolean getNotificationStatus()
    {
        User currentUser = findUserByEmail();
        Boolean notificationStatus = currentUser.getNotificationsEnabled();
        return notificationStatus;
    }

   /**
    * The function toggles the notification status of the current user and saves the changes to the
    * user repository.
    */
    public void setNotificationStatus(){
        User currentUser = findUserByEmail();
        Boolean notificationStatus = currentUser.getNotificationsEnabled();
        if(notificationStatus == true)
        {
            currentUser.setNotificationsEnabled(false);
            userRepo.save(currentUser);
      
        } 
        else{
            currentUser.setNotificationsEnabled(true);
            userRepo.save(currentUser);
    }
    }

    /**
     * The function deletes a user from the user repository by their ID, throwing an exception if the
     * user is not found.
     * 
     * @param id The id parameter is an Integer that represents the unique identifier of the user that
     * needs to be deleted.
     */
    public void deleteUserById(Integer id) {
        User user = userRepo.findById(id).orElse(null);
    if (user != null) {
        userRepo.deleteById(id);
    } else {
        throw new IllegalArgumentException("User not found.");
    }
    }


    /**
     * The function updates the username and email of a user with the given id and returns the updated
     * user.
     * 
     * @param id The id parameter is an Integer that represents the unique identifier of the user that
     * needs to be updated.
     * @param username The username parameter is a String that represents the new username for the
     * user.
     * @param email The email parameter is a String that represents the new email address for the user.
     * @return The method is returning the updated User object.
     */
    public User updateUser(Integer id, String username, String email) {
        User user = userRepo.findById(id).orElse(null);
        
        user.setUsername(username);
        user.setEmail(email);
        return userRepo.save(user);
    }

    /**
     * The function finds a user in the database based on the email claim from the JWT token.
     * 
     * @return The method is returning a User object.
     */
    public User findUserByEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
        if (auth == null) {
            System.out.println("Authentication is null");
            return null;
        }
    
        Jwt jwt = (Jwt) auth.getPrincipal();
    
        if (jwt == null) {
            System.out.println("JWT is null");
            return null;
        }
    
        String email = jwt.getClaim("email");
    
        if (email == null) {
            System.out.println("Email claim is null");
            return null;
        }
    
        User currentUser = userRepo.findByEmail(email);
    
        if (currentUser == null) {
            System.out.println("User not found in database with email: " + email);
            return null;
        }
    
        return currentUser;
    }
    /**
     * The function retrieves a user from the user repository based on their ID and throws an exception
     * if the user is not found.
     * 
     * @param userId The userId parameter is an Integer that represents the unique identifier of a
     * user.
     * @return The method is returning a User object.
     */
    public User getUserById(Integer userId) {
        return userRepo.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    }

	/**
     * The function loads user details by username and throws an exception if the user is not found.
     * 
     * @param username The username parameter is the username entered by the user for authentication.
     * @return The method is returning an instance of UserDetails.
     */
    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
System.out.println("in the userdetail service");
return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
}    
}


