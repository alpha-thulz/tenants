package za.co.tyaphile.tenants.repo;

import org.springframework.data.repository.CrudRepository;
import za.co.tyaphile.tenants.model.Tenant;

public interface TenantRepo extends CrudRepository<Tenant, String> {
}
