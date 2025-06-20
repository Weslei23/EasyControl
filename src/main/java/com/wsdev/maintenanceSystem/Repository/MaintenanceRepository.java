package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.MaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, UUID>
{
}
