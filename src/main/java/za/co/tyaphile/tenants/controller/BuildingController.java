package za.co.tyaphile.tenants.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dao.BuildingDao;
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

    @Operation(summary = "Used to get all buildings")
    @GetMapping
    public ResponseEntity<List<Building>> getAllProperties() {
        List<Building> buildings = buildingService.getAllBuildings();
        if (buildings.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(buildings);
    }

    @Operation(summary = "Used to get a building matching building ID")
    @GetMapping("/{buildingId}")
    public ResponseEntity<Building> getBuildingById(@PathVariable("buildingId") String building_id) {
        return ResponseEntity.ok(buildingService.getBuildingById(building_id));
    }

    @Operation(summary = "Used to register a building to a specific user")
    @PostMapping("/{userID}")
    public ResponseEntity<Building> addBuilding(@PathVariable String userID, @RequestBody @Valid BuildingDao building) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(userID, building));
    }

    @Operation(summary = "Used to update building matching building ID")
    @PutMapping("/{buildingId}")
    public ResponseEntity<Building> updateBuilding(@PathVariable("buildingId") String id, @RequestBody @Valid BuildingDao building) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(buildingService.updateBuilding(id, building));
    }

    @Operation(summary = "Used to delete a building matching building ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{buildingId}")
    public void deleteBuilding(@PathVariable("buildingId") String id) {
        buildingService.deleteBuilding(id);
    }
}
