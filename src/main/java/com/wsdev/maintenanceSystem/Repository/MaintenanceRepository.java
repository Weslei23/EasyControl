package com.wsdev.maintenanceSystem.Repository;

import com.wsdev.maintenanceSystem.Models.MaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, Long>
{
}
