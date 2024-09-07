package za.co.tyaphile.tenants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dao.BuildingDao;
import za.co.tyaphile.tenants.model.Building;
import za.co.tyaphile.tenants.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public List<Building> getAllProperties() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable String id) {
        return buildingService.getBuildingById(id);
    }

    @PostMapping("/{userID}")
    public Building addBuilding(@PathVariable String userID, @RequestBody BuildingDao building) {
        return buildingService.createBuilding(userID, building);
    }
}
