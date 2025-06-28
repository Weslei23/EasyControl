package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>
{
}
