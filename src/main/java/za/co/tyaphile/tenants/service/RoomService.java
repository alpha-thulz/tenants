package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dao.RoomDao;
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

    public Room addRoom(String id, RoomDao roomDao) {
        Building building = buildingService.getBuildingById(id);
        Room room = new Room(roomDao.getRoomNumber(), roomDao.getTenantsOccupied(), roomDao.getTotalTenants(),
                roomDao.getTotalRentalPrice(), roomDao.getServicesCosts(), roomDao.isUnderMaintenance(), building);
        return roomRepo.save(room);
    }

    public Room updateRoom(String id, RoomDao roomDao) {
        Room oldRoom = findRoomById(id);
        if (oldRoom != null) {
            oldRoom.setRoomNumber(roomDao.getRoomNumber());
            oldRoom.setTenantsOccupied(roomDao.getTenantsOccupied());
            oldRoom.setTotalTenants(roomDao.getTotalTenants());
            oldRoom.setTotalRentalPrice(roomDao.getTotalRentalPrice());
            oldRoom.setServicesCosts(roomDao.getServicesCosts());
            oldRoom.setUnderMaintenance(roomDao.isUnderMaintenance());
            return roomRepo.save(oldRoom);
        }
        return null;
    }

    public void deleteRoom(String id) {
        findRoomById(id);
        roomRepo.deleteById(id);
    }
}
