/**
 * The Role class is an entity class that represents a role in the Hemlock Studios website and
 * implements the GrantedAuthority interface.
 */
package com.HemlockStudiosWebsite.entity;

import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
// The `@Table(name="roles")` annotation is used to specify the name of the database table that
// corresponds to the Role entity class. In this case, it indicates that the Role entity is mapped to a
// table named "roles" in the database.
@Table(name="roles")
public class Role implements GrantedAuthority{
// The code snippet `@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="role_id")
// private Integer roleId;` is defining the primary key field for the Role entity class.
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="role_id")
private Integer roleId;
// The `@Column(name="authority")` annotation is used to specify the mapping of the `authority` field
// to a column in the database table. In this case, it indicates that the `authority` field should be
// mapped to a column named "authority" in the "roles" table.
@Column(name="authority")
private String authority;
// The `public Role(){ super(); }` is a default constructor for the Role class.
public Role(){
super();
}
// The `public Role(String authority)` constructor is initializing the `authority` field of the `Role`
// class with the value passed as a parameter. This constructor allows you to create a new `Role`
// object and set its authority at the same time.
public Role(String authority){
this.authority = authority;
}
// The `public Role(Integer roleId, String authority)` constructor is initializing the `roleId` and
// `authority` fields of the `Role` class with the values passed as parameters. This constructor allows
// you to create a new `Role` object and set its `roleId` and `authority` at the same time.
public Role(Integer roleId, String authority){
this.roleId = roleId;
this.authority = authority;
}
@Override
public String getAuthority() {
return this.authority;
}
public void setAuthority(String authority){
this.authority = authority;
}
public Integer getRoleId() {
return roleId;
}
public void setRoleId(Integer roleId) {
this.roleId = roleId;
}
}