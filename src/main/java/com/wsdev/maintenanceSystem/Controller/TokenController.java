package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.LoginRequestDTO;
import com.wsdev.maintenanceSystem.Dto.LoginResponseDTO;
import com.wsdev.maintenanceSystem.Database.Models.Role;
import com.wsdev.maintenanceSystem.Database.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/v1/auth" )
@CrossOrigin( origins = "http://localhost:5173" )
public class TokenController
{
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public TokenController( JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder )
    {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login( @RequestBody LoginRequestDTO loginRequest )
    {
        var user = userRepository.getUserByUsername( loginRequest.username() );

        if( user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder ) )
        {
            throw new BadCredentialsException( "user or password is invalid!" );
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.get().getRoles()
                .stream()
                .map( Role::getName )
                .collect( Collectors.joining( " " ) );

        var claims = JwtClaimsSet.builder()
                .issuer( "mybackend" )
                .subject( user.get().getId().toString() )
                .issuedAt( now )
                .expiresAt( now.plusSeconds( expiresIn ) )
                .claim( "scope", scopes )
                .build();

        var jwtValue = jwtEncoder.encode( JwtEncoderParameters.from( claims ) ).getTokenValue();

        return ResponseEntity.ok( new LoginResponseDTO( jwtValue, expiresIn ) );
    }
}
