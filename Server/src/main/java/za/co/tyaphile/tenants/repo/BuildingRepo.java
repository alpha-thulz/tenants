package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.tyaphile.tenants.model.Building;

@Repository
public interface BuildingRepo extends JpaRepository<Building, String> {
}
