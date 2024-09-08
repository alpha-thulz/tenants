package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.tyaphile.tenants.model.Tenant;

public interface TenantRepo extends JpaRepository<Tenant, String> {
}
