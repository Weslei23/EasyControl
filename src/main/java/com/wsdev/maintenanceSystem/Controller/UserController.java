package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.UserDTO;
import com.wsdev.maintenanceSystem.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/users" )
@CrossOrigin( origins = "http://localhost:5173" )
@AllArgsConstructor
public class UserController
{
    @Autowired
    private UserService userService;

    @Operation( description = "Irá retornar uma lista de usuários cadastrados" )
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        return ResponseEntity.ok( userService.getUsers() );
    }

    @Operation( description = "Irá buscar um usuário pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<UserDTO> getUserById( @PathVariable Long id )
    {
        try
        {
            return ResponseEntity.ok( userService.getUserById( id ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá buscar um usuário pelo 'Username' informado." )
    @GetMapping( "/findByUsername/{username}" )
    public ResponseEntity<UserDTO> getUserByUsername( @PathVariable String username )
    {
        try
        {
            return ResponseEntity.ok( userService.getUserByUsername( username ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá retornar um usuário pelo 'Nome' informado." )
    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<UserDTO> getUserByName( @PathVariable String name )
    {
        try
        {
            return ResponseEntity.ok( userService.getUserByName( name ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá retornar um usuário pelo 'Email' informado." )
    @GetMapping( "/findByEmail/{email}" )
    public ResponseEntity<UserDTO> getUserByEmail( @PathVariable String email )
    {
        try
        {
            return ResponseEntity.ok( userService.getUserByEmail( email ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá adicionar um usuário ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<String> addUser( @RequestBody @Valid UserDTO userDTO )
    {
        try
        {
            userService.addUser( userDTO );
            return ResponseEntity.ok( "User succesfully added" );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation( description = "Irá atualizar um usuário a partir do 'ID' informado." )
    @PutMapping( "/update/{id}" )
    public ResponseEntity<String> updateUser( @PathVariable Long id, @RequestBody @Valid UserDTO userDTO )
    {
        try
        {
            userService.updateUser( id, userDTO );
            return ResponseEntity.ok( "User succesfully updated" );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation( description = "Irá deletar um usuário a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteUserById( @PathVariable Long id )
    {
        try
        {
            userService.deleteUserById( id );
            return ResponseEntity.noContent().build();
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }
}
