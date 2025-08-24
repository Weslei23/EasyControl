package com.wsdev.maintenanceSystem.Database.Repository;

import com.wsdev.maintenanceSystem.Database.Models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long>
{
    RoleModel getByName(String name );
}
