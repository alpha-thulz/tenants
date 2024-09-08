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
    public ResponseEntity<List<Building>> getAllProperties() {
        List<Building> buildings = buildingService.getAllBuildings();
        if (buildings.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<Building> getBuildingById(@PathVariable("buildingId") String building_id) {
        return ResponseEntity.ok(buildingService.getBuildingById(building_id));
    }

    @PostMapping("/{userID}")
    public ResponseEntity<Building> addBuilding(@PathVariable String userID, @RequestBody @Valid BuildingDto building) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(userID, building));
    }

    @PutMapping("/{buildingId}")
    public ResponseEntity<Building> updateBuilding(@PathVariable("buildingId") String id, @RequestBody @Valid BuildingDto building) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(buildingService.updateBuilding(id, building));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{buildingId}")
    public void deleteBuilding(@PathVariable("buildingId") String id) {
        buildingService.deleteBuilding(id);
    }
}
