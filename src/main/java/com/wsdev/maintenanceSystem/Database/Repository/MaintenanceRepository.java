package com.wsdev.maintenanceSystem.Database.Repository;

import com.wsdev.maintenanceSystem.Database.Models.MaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, Long>
{
}
