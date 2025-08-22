package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.Role;
import com.wsdev.maintenanceSystem.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role getByName( String name );
}
