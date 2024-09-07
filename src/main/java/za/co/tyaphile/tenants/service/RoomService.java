package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.repo.RoomRepo;

@Service
public class RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }
}
