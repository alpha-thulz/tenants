package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dto.BuildingDto;
import za.co.tyaphile.tenants.model.Building;
import za.co.tyaphile.tenants.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProperties() {
        List<Building> buildings = buildingService.getAllBuildings();
        if (buildings.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBuildingById(@PathVariable String id) {
        return ResponseEntity.ok(buildingService.getBuildingById(id));
    }

    @PostMapping("/{userID}")
    public ResponseEntity<?> addBuilding(@PathVariable String userID, @RequestBody @Valid BuildingDto building) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(userID, building));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBuilding(@PathVariable String id, @RequestBody @Valid BuildingDto building) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(buildingService.updateBuilding(id, building));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable String id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }
}
