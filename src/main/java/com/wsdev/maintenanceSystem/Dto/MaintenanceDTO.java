package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Models.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MaintenanceDTO(
        Long id,
        LocalDate scheduledDate,
        StatusModel status,
        TypeMaterialModel typeMaterial,
        String descriptionService,
        Long customerId,
        String customerName,
        Long employeeId,
        String employeeName,
        LocalDateTime createdAt )
{
    public static MaintenanceDTO from( MaintenanceModel model )
    {
        return new MaintenanceDTO(
                model.getId(),
                model.getScheduledDate(),
                model.getStatus(),
                model.getTypeMaterial(),
                model.getDescriptionService(),
                model.getCustomer() != null ? model.getCustomer().getId() : null,
                model.getCustomer() != null ? model.getCustomer().getName() : null,
                model.getEmployee() != null ? model.getEmployee().getId() : null,
                model.getEmployee() != null ? model.getEmployee().getName() : null,
                model.getCreatedAt()
        );
    }

    public MaintenanceModel toEntity()
    {
        CustomerModel customer = new CustomerModel();
        customer.setId( this.customerId );

        EmployeeModel employee = new EmployeeModel();
        employee.setId( this.employeeId );

        return MaintenanceModel.builder()
                .id( this.id )
                .scheduledDate( this.scheduledDate )
                .status( this.status)
                .typeMaterial( this.typeMaterial )
                .descriptionService( this.descriptionService )
                .customer( customer )
                .employee( employee )
                .createdAt( this.createdAt )
                .build();
    }
}