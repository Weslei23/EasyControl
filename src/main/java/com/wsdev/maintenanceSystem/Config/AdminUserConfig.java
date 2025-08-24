package com.wsdev.maintenanceSystem.Config;

import com.wsdev.maintenanceSystem.Database.Models.Role;
import com.wsdev.maintenanceSystem.Database.Models.UserModel;
import com.wsdev.maintenanceSystem.Database.Repository.RoleRepository;
import com.wsdev.maintenanceSystem.Database.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner
{
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig( RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder )
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run( String... args ) throws Exception
    {
        var roleAdmin = roleRepository.getByName( Role.Values.ADMIN.name() );

        var userAdmin = userRepository.getUserByUsername( "admin" );

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println( "admin ja existe" );
                },
                () -> {
                    var user = new UserModel();
                    user.setUsername( "admin" );
                    user.setPassword( passwordEncoder.encode( "123" ) );
                    Role basicRole = roleRepository.getByName( Role.Values.BASIC.name() );
                    if ( basicRole == null )
                    {
                        throw new IllegalStateException( "Role BASIC não encontrada no banco" );
                    }

                    user.setRoles( Set.of( basicRole ) );

                    userRepository.save( user );
                }
        );
    }
}