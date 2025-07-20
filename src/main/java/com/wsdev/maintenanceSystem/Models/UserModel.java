package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @NotBlank( message = "O nome é obrigatório." )
    @Size(max = 50 )
    private String name;

    @NotBlank( message = "O nome de usuário é obrigatório." )
    @Column( unique = true )
    @Size( min = 4, max = 50, message = "O username deve ter entre 4 a 50 caracteres." )
    private String username;

    @NotBlank( message = "O email é obrigatório." )
    @Email( message = "O campo [email] deve conter um e-mail válido." )
    @Column( unique = true)
    @Size(max = 60 )
    private String email;

    @NotBlank( message = "A senha é obrigatória." )
    @Size( min = 8, max = 120, message = "A senha deve ter entre 8 e 120 caracteres." )
    private String password;

    @NotBlank( message = "O telefone é obrigatório." )
    @Pattern( regexp = "^(\\(\\d{2}\\)\\s?|\\d{2})\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (11) 91234-5678 ou 11912345678." )
    private String telephone;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
