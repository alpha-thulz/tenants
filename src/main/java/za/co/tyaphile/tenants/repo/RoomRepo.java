package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.tyaphile.tenants.model.Room;

public interface RoomRepo extends JpaRepository<Room, String> {
}
