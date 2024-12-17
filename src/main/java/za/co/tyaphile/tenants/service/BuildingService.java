package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dao.BuildingDao;
import za.co.tyaphile.tenants.model.Building;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.repo.BuildingRepo;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepo repo;
    private final UserService userService;

    @Autowired
    public BuildingService(BuildingRepo repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public List<Building> getAllBuildings() {
        return repo.findAll();
    }

    public Building getBuildingById(String id) {
        return repo.findById(id).orElseThrow(() -> new IndexOutOfBoundsException("Unable to find building with id " + id));
    }

    public Building createBuilding(String id, BuildingDao building) {
        User user = userService.getUser(id);
        Building property = new Building(building.getName(), building.getAddress(), user);
        return repo.save(property);
    }

    public Building updateBuilding(String id, BuildingDao building) {
        Building oldBuilding = getBuildingById(id);
        oldBuilding.setName(building.getName());
        oldBuilding.setAddress(building.getAddress());
        return repo.save(oldBuilding);
    }

    public void deleteBuilding(String id) {
        getBuildingById(id);
        repo.deleteById(id);
    }
}