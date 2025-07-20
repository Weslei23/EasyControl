package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.UserDTO;
import com.wsdev.maintenanceSystem.Models.UserModel;
import com.wsdev.maintenanceSystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers()
    {
        return userRepository.findAll().stream()
                .map( UserDTO::from ).toList();
    }

    public UserDTO getUserByUsername( String username )
    {
        UserModel userModel = userRepository.getUserByUsername( username )
                .orElseThrow( () -> new RuntimeException( "Nenhum usuario encontrado com o username " + username ) );

        return UserDTO.from( userModel );
    }

    public UserDTO getUserByName( String name )
    {
        UserModel userModel = userRepository.getUserByName( name )
                .orElseThrow( () -> new RuntimeException( "Nenhum usuário encontrado com este nome." ) );

        return UserDTO.from( userModel );
    }

    public UserDTO getUserByEmail( String email )
    {
        UserModel userModel = userRepository.getUserByEmail( email )
                .orElseThrow( () -> new RuntimeException( "Nenhum usuário encontrado com este email." ) );
        return UserDTO.from( userModel );
    }

    public UserDTO getUserById( Long id )
    {
        UserModel userModel = userRepository.findById( id )
                .orElseThrow( () -> new  RuntimeException( "Nenhum usuário encontrado com este id." ) );
        return UserDTO.from(  userModel );
    }

    public UserDTO addUser( UserDTO userDTO )
    {
        UserModel userModel = userRepository.save( userDTO.toEntity() );
        return UserDTO.from( userModel );
    }

    public UserDTO updateUser( Long id, UserDTO userDTO )
    {
        UserModel userModel = userRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Nenhum usuário encontrado com este id." ) );

        userModel.setId( userDTO.id() );
        userModel.setName( userDTO.name() );
        userModel.setEmail( userDTO.username() );
        userModel.setEmail( userDTO.email() );
        userModel.setPassword( userDTO.password() );
        userModel.setTelephone( userDTO.telephone() );

        UserModel userModelUpdate = userRepository.save( userModel );

        return UserDTO.from( userModelUpdate );
    }

    public void deleteUserById( Long id )
    {
        userRepository.deleteById( id );
    }
}
