package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dto.TenantDto;
import za.co.tyaphile.tenants.model.Tenant;
import za.co.tyaphile.tenants.service.TenantService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        if (tenants.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenantId") String id) {
        return ResponseEntity.ok(tenantService.getTenantById(id));
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<Tenant> createTenant(@PathVariable("roomId") String id, @RequestBody @Valid TenantDto tenant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.createTenant(id, tenant));
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("tenantId") String id, @RequestBody @Valid TenantDto tenant) {
        return ResponseEntity.ok(tenantService.updateTenant(id, tenant));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{tenantId}")
    public void deleteTenant(@PathVariable("tenantId") String id) {
        tenantService.deleteTenant(id);
    }
}