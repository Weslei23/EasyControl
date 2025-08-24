package com.wsdev.maintenanceSystem.Database.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull( message = "O nome é obrigatório." )
    @Column( nullable = false )
    private String name;

    @NotNull( message = "O telefone é obrigatório." )
    @Column( nullable = false )
    @Pattern( regexp = "^(\\(\\d{2}\\)\\s?|\\d{2})\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (11) 91234-5678 ou 11912345678." )
    private String telephone;

    @Email( message = "O campo [email] deve conter um e-mail válido." )
    @Column( unique = true, nullable = false )
    @Size(max = 60 )
    private String email;

    @Embedded
    @Column( nullable = false )
    @NotNull( message = "O endereço é obrigatório." )
    private AddressModel address;

    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<MaintenanceModel> maintenances = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
}
