package com.wsdev.maintenanceSystem.Models;

import com.wsdev.maintenanceSystem.Dto.LoginRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "tb_users" )
@AllArgsConstructor
@NoArgsConstructor
public class UserModel
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull( message = "O nome de usuário é obrigatório." )
    @Size( min = 4, max = 50, message = "O username deve ter entre 4 a 50 caracteres." )
    @Column( unique = true, nullable = false, length = 50 )
    private String username;

    @NotNull( message = "A senha é obrigatória." )
    @Size( min = 8, max = 120, message = "A senha deve ter entre 8 e 120 caracteres." )
    @Column( nullable = false, length = 120 )
    private String password;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public boolean isLoginCorrect( LoginRequestDTO loginRequest, PasswordEncoder passwordEncoder )
    {
        return passwordEncoder.matches( loginRequest.password(), this.password );
    }
}
