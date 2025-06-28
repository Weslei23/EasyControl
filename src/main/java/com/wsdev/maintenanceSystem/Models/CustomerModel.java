package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "tb_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerModel
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "O nome é obrigatório" )
    private String name;

    @NotBlank
    @Pattern( regexp = "^(\\(\\d{2}\\)\\s?|\\d{2})\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (11) 91234-5678 ou 11912345678" )
    private String telephone;

    @Email( message = "O campo [email] deve conter um e-mail valído." )
    private String email;

    @Embedded
    @NotNull( message = "O endereço é obrigatório" )
    private AddressModel address;

    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<MaintenanceModel> maintenances = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
}
