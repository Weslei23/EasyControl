package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID>
{
}
