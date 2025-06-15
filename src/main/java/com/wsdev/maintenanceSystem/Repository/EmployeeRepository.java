package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID>
{
}
