package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Models.UserModel;

public record UserDTO(
        Long id,
        String name,
        String username,
        String email,
        String password,
        String telephone )
{
    public static UserDTO from( UserModel userModel )
    {
        return new UserDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getTelephone() );
    }

    public UserModel toEntity()
    {
        UserModel userModel = new UserModel();
        userModel.setId( id );
        userModel.setName( name );
        userModel.setUsername( username );
        userModel.setEmail( email );
        userModel.setPassword( password );
        userModel.setTelephone( telephone );

        return userModel;
    }
}
