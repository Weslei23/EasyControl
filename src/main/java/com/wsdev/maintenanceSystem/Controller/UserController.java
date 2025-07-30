package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.UserDTO;
import com.wsdev.maintenanceSystem.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/api/v1/users" )
@CrossOrigin( origins = "http://localhost:5173" )
@AllArgsConstructor
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        return ResponseEntity.ok( userService.getUsers() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDTO> getUserById( @PathVariable Long id )
    {
        return ResponseEntity.ok( userService.getUserById( id ) );
    }

    @GetMapping( "/findByUsername/{username}" )
    public ResponseEntity<UserDTO> getUserByUsername( @PathVariable String username )
    {
        return ResponseEntity.ok( userService.getUserByUsername( username ) );
    }

    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<UserDTO> getUserByName( @PathVariable String name )
    {
        return ResponseEntity.ok( userService.getUserByName( name ) );
    }

    @GetMapping( "/findByEmail/{email}" )
    public ResponseEntity<UserDTO> getUserByEmail( @PathVariable String email )
    {
        return ResponseEntity.ok( userService.getUserByEmail( email ) );
    }

    @PostMapping( "/add" )
    public ResponseEntity<UserDTO> addUser( @RequestBody @Valid UserDTO userDTO )
    {
        ResponseEntity.ok( userService.addUser( userDTO ) );
        return new ResponseEntity<>( userDTO, HttpStatus.CREATED );
    }

    @PutMapping( "/update/{id}" )
    public ResponseEntity<UserDTO> updateUser( @PathVariable Long id, @RequestBody @Valid UserDTO userDTO )
    {
        return ResponseEntity.ok( userService.updateUser( id, userDTO ) );
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteUserById( @PathVariable Long id )
    {
        userService.deleteUserById( id );
        return ResponseEntity.noContent().build();
    }
}
