package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long>
{
}
