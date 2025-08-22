package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>
{
    Optional<UserModel> getUserByUsername( String username );
}
