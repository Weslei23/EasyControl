package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.CreateUserDTO;
import com.wsdev.maintenanceSystem.Database.Models.Role;
import com.wsdev.maintenanceSystem.Database.Models.UserModel;
import com.wsdev.maintenanceSystem.Database.Repository.RoleRepository;
import com.wsdev.maintenanceSystem.Database.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping( "/api/v1/user" )
@CrossOrigin( origins = "http://localhost:5173" )
public class UserController
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController( UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder )
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping( "/add" )
    public ResponseEntity<Void> addUser( @RequestBody CreateUserDTO dto )
    {
        var basicRole = roleRepository.getByName( Role.Values.BASIC.name() );

        var userFromDb = userRepository.getUserByUsername( dto.username() );
        if ( userFromDb.isPresent() )
        {
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY );
        }

        var user = new UserModel();
        user.setUsername( dto.username() );
        user.setPassword( passwordEncoder.encode( dto.password() ) );
        user.setRoles( Set.of( basicRole ) );

        userRepository.save( user );

        return ResponseEntity.ok().build();
    }

    @GetMapping( "/listUsers" )
    @PreAuthorize( "hasAuthority( 'SCOPE_ADMIN' )" )
    public ResponseEntity<List<UserModel>> listUsers()
    {
        var users = userRepository.findAll();
        return ResponseEntity.ok( users );
    }
}