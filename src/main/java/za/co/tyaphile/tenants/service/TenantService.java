package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.repo.TenantRepo;

@Service
public class TenantService {

    private final TenantRepo tenantRepo;

    @Autowired
    public TenantService(TenantRepo tenantRepo) {
        this.tenantRepo = tenantRepo;
    }
}
