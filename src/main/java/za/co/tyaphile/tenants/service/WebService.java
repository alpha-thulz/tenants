package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dto.UserDto;
import za.co.tyaphile.tenants.model.User;

import java.util.List;

@Service
public class WebService {

    private final UserService userService;
    private final BuildingService buildingService;
    private final RoomService roomService;
    private final TenantService tenantService;

    @Autowired
    public WebService(UserService userService, BuildingService buildingService, RoomService roomService, TenantService tenantService) {
        this.userService = userService;
        this.buildingService = buildingService;
        this.roomService = roomService;
        this.tenantService = tenantService;
    }

    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(String id) {
        return userService.getUserById(id);
    }

    public void createUser(UserDto user) {
        userService.createUser(user);
    }

    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    public void updateUser(String id, UserDto user) {
        userService.updateUser(id, user);
    }
}
