package com.HemlockStudiosWebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.AdminAccountRequest;
import com.HemlockStudiosWebsite.dto.AdminAccountResponse;
import com.HemlockStudiosWebsite.dto.DeleteRequest;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.UpdateAdminRequest;
import com.HemlockStudiosWebsite.entity.Administrator;
import com.HemlockStudiosWebsite.service.AdministratorService;

@RestController
@RequestMapping(value="/administrator")
@CrossOrigin("*")
public class AdministratorController  {
    
@Autowired
AdministratorService administratorService;

@RequestMapping(
    value="/signUpAsAdministrator",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)
public ResponseEntity<Object> signUpAsAdministrator(@RequestBody AdminAccountRequest request) {
    try {
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null || request.getPredefinedAdminId() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }

        Administrator savedAdministrator = administratorService.signUpAsAdministrator(request.getUsername(), request.getEmail(), request.getPassword(), request.getPredefinedAdminId());
        
       
        AdminAccountResponse response = new AdminAccountResponse();
        response.setId(savedAdministrator.getId());
        response.setUsername(savedAdministrator.getUsername());
        response.setEmail(savedAdministrator.getEmail());

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@RequestMapping(
    value="/deleteAdministrator",
    method = RequestMethod.DELETE,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> deleteAdministrator(@RequestBody DeleteRequest request) {
    try {
        administratorService.deleteAdministratorById(request.getId());

        DeleteResponse response = new DeleteResponse();
        response.setMessage("Administrator deleted successfully.");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@RequestMapping(
    value="/signInAsAdministrator",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)

public ResponseEntity<Object> signInAsAdministrator(@RequestBody AdminAccountRequest request){

    try{
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        Administrator savedAdministrator = administratorService.signInAsAdministrator(
            request.getUsername(), request.getEmail(), request.getPassword(), request.getPredefinedAdminId());
               
        AdminAccountResponse response = new AdminAccountResponse();
        response.setId(savedAdministrator.getId());
        response.setUsername(savedAdministrator.getUsername());
        response.setEmail(savedAdministrator.getEmail());

       return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@RequestMapping(
    value="/updateAdministrator",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.PUT
)
public ResponseEntity<Object> updateAdministrator(@RequestBody UpdateAdminRequest request){

    try{
        if (request.getId() == null || request.getUsername() == null || request.getEmail() == null|| request.getPredefinedAdminId()==null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        Administrator updatedAdministrator = administratorService.updateAdministrator(request.getId(), request.getUsername(), request.getEmail());
               
        AdminAccountResponse response = new  AdminAccountResponse();
        response.setId(updatedAdministrator.getId());
        response.setUsername(updatedAdministrator.getUsername());
        response.setEmail(updatedAdministrator.getEmail());

       return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

}
