package com.wsdev.maintenanceSystem.Database.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull( message = "O nome é obrigatório." )
    @Column( nullable = false )
    @Size(max = 50 )
    private String name;

    @NotNull( message = "O telefone é obrigatório." )
    @Column( nullable = false )
    @Pattern( regexp = "^(\\(\\d{2}\\)\\s?|\\d{2})\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (11) 91234-5678 ou 11912345678." )
    private String telephone;

    @NotNull( message = "Adicione uma especialidade." )
    @Size( max = 40 )
    @Column( nullable = false )
    private String specialty;

    @OneToMany( mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<MaintenanceModel> maintenances = new ArrayList<>();;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
