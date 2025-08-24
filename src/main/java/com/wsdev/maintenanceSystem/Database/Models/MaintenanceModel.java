package com.wsdev.maintenanceSystem.Database.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull( message = "A data de execução é obrigatória." )
    @Column( nullable = false )
    private LocalDate scheduledDate;

    @NotNull( message = "O status de execução é obrigatório." )
    @Column( nullable = false )
    @Enumerated( EnumType.STRING )
    private StatusModel status;

    @NotNull( message = "O tipo de material é obrigatório." )
    @Column( nullable = false )
    @Enumerated( EnumType.STRING )
    private TypeMaterialModel typeMaterial;

    @NotNull( message = "A descrição é obrigatória." )
    @Column( nullable = false )
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
