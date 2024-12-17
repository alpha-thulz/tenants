package za.co.tyaphile.tenants.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dao.TenantDao;
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

    @Operation(summary = "Used to get all tenants")
    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        if (tenants.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tenants);
    }

    @Operation(summary = "Used to get a tenant by ID")
    @GetMapping("/{tenantId}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenantId") String id) {
        return ResponseEntity.ok(tenantService.getTenantById(id));
    }


    @Operation(summary = "Used to register a tenant into a room matching room ID")
    @PostMapping("/{roomId}")
    public ResponseEntity<Tenant> createTenant(@PathVariable("roomId") String id, @RequestBody @Valid TenantDao tenant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.createTenant(id, tenant));
    }

    @Operation(summary = "Used to update a tenant matching tenant ID")
    @PutMapping("/{tenantId}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("tenantId") String id, @RequestBody @Valid TenantDao tenant) {
        return ResponseEntity.ok(tenantService.updateTenant(id, tenant));
    }

    @Operation(summary = "Used to delete a tenant matching tenant ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{tenantId}")
    public void deleteTenant(@PathVariable("tenantId") String id) {
        tenantService.deleteTenant(id);
    }
}