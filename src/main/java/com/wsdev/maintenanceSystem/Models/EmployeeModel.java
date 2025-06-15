package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "tb_employees" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeModel
{

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id;

    @NotBlank( message = "O nome é obrigatório" )
    private String name;

    @NotBlank( message = "O telefone é obrigatório" )
    @Pattern( regexp = "^(\\(\\d{2}\\)\\s?|\\d{2})\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (11) 91234-5678 ou 11912345678" )
    private String telephone;

    private String specialty;

    @OneToMany( mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<MaintenanceModel> maintenances = new ArrayList<>();;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
