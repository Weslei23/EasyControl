package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Models.UserModel;
import com.wsdev.maintenanceSystem.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService
{
    private final UserRepository userRepository;

    public UserDetailService( UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        UserModel user = userRepository.getUserByUsername( username )
                .orElseThrow( () -> new UsernameNotFoundException( "User not found: " + username ) );

        return User.builder()
                .username( user.getUsername() )
                .password( user.getPassword() )
                .roles( "USER_ROLE" )
                .build();
    }
}
