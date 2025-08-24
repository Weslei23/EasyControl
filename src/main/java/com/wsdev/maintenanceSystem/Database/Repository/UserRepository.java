package com.wsdev.maintenanceSystem.Database.Repository;

import com.wsdev.maintenanceSystem.Database.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>
{
    Optional<UserModel> getUserByUsername( String username );
}
