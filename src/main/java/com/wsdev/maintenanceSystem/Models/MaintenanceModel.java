package com.wsdev.maintenanceSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "tb_maintenances" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceModel
{
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id;

    @NotNull( message = "A data de execução é obrigatória" )
    private LocalDate scheduledDate;

    @NotNull( message = "O status de execução é obrigatório" )
    @Enumerated( EnumType.STRING )
    private StatusModel status;

    @NotNull( message = "O tipo de material é obrigatório" )
    @Enumerated( EnumType.STRING )
    private TypeMaterialModel typeMaterial;

    @NotBlank( message = "A descrição é obrigatória" )
    private String descriptionService;

    @ManyToOne
    @JoinColumn( name = "customer_id" )
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn( name = "employee_id" )
    private EmployeeModel employee;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
