package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.LoginRequestDTO;
import com.wsdev.maintenanceSystem.Dto.RegisterRequestDTO;
import com.wsdev.maintenanceSystem.Dto.UserDTO;
import com.wsdev.maintenanceSystem.Exception.CustomerExistsException;
import com.wsdev.maintenanceSystem.Exception.UserNotFoundException;
import com.wsdev.maintenanceSystem.Models.UserModel;
import com.wsdev.maintenanceSystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getUsers()
    {
        return userRepository.findAll().stream()
                .map( UserDTO::from ).toList();
    }

    public UserDTO getUserByUsername( String username )
    {
        UserModel userModel = userRepository.getUserByUsername( username )
                .orElseThrow( UserNotFoundException::new );

        return UserDTO.from( userModel );
    }

    public UserDTO getUserByName( String name )
    {
        UserModel userModel = userRepository.getUserByName( name )
                .orElseThrow( UserNotFoundException::new );

        return UserDTO.from( userModel );
    }

    public UserDTO getUserByEmail( String email )
    {
        UserModel userModel = userRepository.getUserByEmail( email )
                .orElseThrow( UserNotFoundException::new );
        return UserDTO.from( userModel );
    }

    public UserDTO getUserById( Long id )
    {
        UserModel userModel = userRepository.findById( id )
                .orElseThrow( UserNotFoundException::new );
        return UserDTO.from(  userModel );
    }

    public UserDTO updateUser( Long id, UserDTO userDTO )
    {
        UserModel userModel = userRepository.findById( id )
                .orElseThrow( UserNotFoundException::new );

        userModel.setId( userDTO.id() );
        userModel.setName( userDTO.name() );
        userModel.setEmail( userDTO.username() );
        userModel.setEmail( userDTO.email() );
        userModel.setPassword( passwordEncoder.encode( userDTO.password() ) );
        userModel.setTelephone( userDTO.telephone() );

        UserModel userModelUpdate = userRepository.save( userModel );

        return UserDTO.from( userModelUpdate );
    }

    public void deleteUserById( Long id )
    {
        userRepository.deleteById( id );
    }

    public UserDTO registerUser( RegisterRequestDTO registerDTO )
    {
        if ( userRepository.existsByEmail( registerDTO.email() ) )
        {
            throw new CustomerExistsException( "User with this email already exists" );
        }

        UserModel user = new UserModel();
        user.setName( registerDTO.name() );
        user.setUsername( registerDTO.username() );
        user.setEmail( registerDTO.email() );
        user.setTelephone( registerDTO.telephone() );
        user.setPassword( passwordEncoder.encode( registerDTO.password() ) );

        UserModel saved = userRepository.save( user );
        return UserDTO.from( saved );
    }

    public boolean authenticate( LoginRequestDTO loginDTO )
    {
        UserModel user = userRepository.getUserByUsername( loginDTO.username() )
                .orElseThrow( UserNotFoundException::new );

        return passwordEncoder.matches( loginDTO.password(), user.getPassword() );
    }
}
