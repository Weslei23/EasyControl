package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Database.Models.StatusModel;
import com.wsdev.maintenanceSystem.Database.Models.TypeMaterialModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MaintenanceRequestDTO(

        @NotNull( message = "A data de execução é obrigatória" )
        LocalDate scheduledDate,

        @NotNull( message = "O status de execução é obrigatório" )
        StatusModel statusModel,

        @NotNull( message = "O tipo de material é obrigatório" )
        TypeMaterialModel typeMaterialModel,

        @NotBlank( message = "A descrição do serviço é obrigatória" )
        String descriptionService,

        @NotNull( message = "O ID do cliente é obrigatório" )
        Long customerId,

        @NotNull( message = "O ID do funcionário é obrigatório" )
        Long employeeId
)
{
        
}