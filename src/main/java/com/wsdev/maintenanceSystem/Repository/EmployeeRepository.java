package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>
{
    Optional<EmployeeModel> findByName( String name );
}
