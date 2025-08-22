package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long>
{
    Optional<CustomerModel> findByEmail( String email );
    Optional<CustomerModel> findByName( String name );
    Boolean existsByEmail( String email );
}
