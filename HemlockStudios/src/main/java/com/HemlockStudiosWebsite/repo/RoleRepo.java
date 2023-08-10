// The code is defining a repository interface for the `Role` entity in a Spring Boot application.
package com.HemlockStudiosWebsite.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HemlockStudiosWebsite.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
/**
 * The function findByAuthority searches for a role based on its authority.
 * 
 * @param authority The authority parameter is a String that represents the authority of a role.
 * @return The method findByAuthority is returning an Optional object that contains a Role object.
 */
Optional<Role> findByAuthority(String authority);
}