package za.co.tyaphile.tenants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.tyaphile.tenants.model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, String> {
}
