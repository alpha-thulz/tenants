package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dto.RoomDto;
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
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAllRoom();

        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") String id) {
        Room room = roomService.findRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{buildingId}")
    public ResponseEntity<Room> createRoom(@PathVariable("buildingId") String buildingId, @RequestBody @Valid RoomDto room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(buildingId, room));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable("roomId") String id, @RequestBody @Valid RoomDto room) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(roomService.updateRoom(id, room));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable("roomId") String id) {
        roomService.deleteRoom(id);
    }
}
