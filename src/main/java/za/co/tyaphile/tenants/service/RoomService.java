package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dto.RoomDto;
import za.co.tyaphile.tenants.model.Building;
import za.co.tyaphile.tenants.model.Room;
import za.co.tyaphile.tenants.repo.RoomRepo;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepo roomRepo;
    private final BuildingService buildingService;

    @Autowired
    public RoomService(RoomRepo roomRepo, BuildingService buildingService) {
        this.roomRepo = roomRepo;
        this.buildingService = buildingService;
    }

    public List<Room> findAllRoom() {
        return roomRepo.findAll();
    }

    public Room findRoomById(String id) {
        return roomRepo.findById(id).orElseThrow(() -> new IndexOutOfBoundsException("Room with id " + id + " not found"));
    }

    public Room addRoom(String id, RoomDto roomDto) {
        Building building = buildingService.getBuildingById(id);
        Room room = new Room(roomDto.getRoomNumber(), roomDto.getTotalTenants(),
                roomDto.getTotalRentalPrice(), roomDto.getServicesCosts(), roomDto.isUnderMaintenance(), building);
        return roomRepo.save(room);
    }

    public Room updateRoom(String id, RoomDto roomDto) {
        Room oldRoom = findRoomById(id);
        if (oldRoom != null) {
            oldRoom.setRoomNumber(roomDto.getRoomNumber());
            oldRoom.setTotalTenants(roomDto.getTotalTenants());
            oldRoom.setTotalRentalPrice(roomDto.getTotalRentalPrice());
            oldRoom.setServicesCosts(roomDto.getServicesCosts());
            oldRoom.setUnderMaintenance(roomDto.isUnderMaintenance());
            return roomRepo.save(oldRoom);
        }
        return null;
    }

    public void deleteRoom(String id) {
        findRoomById(id);
        roomRepo.deleteById(id);
    }
}
