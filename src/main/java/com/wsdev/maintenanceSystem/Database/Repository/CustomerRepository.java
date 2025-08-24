package com.wsdev.maintenanceSystem.Database.Repository;

import com.wsdev.maintenanceSystem.Database.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long>
{
    Optional<CustomerModel> findByEmail( String email );
    Optional<CustomerModel> findByName( String name );
    Boolean existsByEmail( String email );
}
