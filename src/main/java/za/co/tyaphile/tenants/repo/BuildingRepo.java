package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.tyaphile.tenants.model.Building;

public interface BuildingRepo extends JpaRepository<Building, String> {
}
