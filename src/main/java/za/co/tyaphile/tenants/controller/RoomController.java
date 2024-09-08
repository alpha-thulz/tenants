package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dao.RoomDao;
import za.co.tyaphile.tenants.model.Room;
import za.co.tyaphile.tenants.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        List<Room> rooms = roomService.findAllRoom();

        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id") String id) {
        Room room = roomService.findRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{buildingId}")
    public ResponseEntity<?> createRoom(@PathVariable("buildingId") String buildingId, @RequestBody @Valid RoomDao room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(buildingId, room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable String id, @RequestBody @Valid RoomDao room) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(roomService.updateRoom(id, room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
