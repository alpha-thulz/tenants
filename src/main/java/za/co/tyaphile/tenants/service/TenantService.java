package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dao.TenantDao;
import za.co.tyaphile.tenants.model.Room;
import za.co.tyaphile.tenants.model.Tenant;
import za.co.tyaphile.tenants.repo.TenantRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class TenantService {

    private final TenantRepo repo;
    private final RoomService roomService;

    @Autowired
    public TenantService(TenantRepo tenantRepo, RoomService roomService) {
        this.repo = tenantRepo;
        this.roomService = roomService;
    }

    public List<Tenant> getAllTenants() {
        return repo.findAll();
    }

    public Tenant getTenantById(String id) {
        return repo.findById(id).orElseThrow(() -> new IndexOutOfBoundsException("Cannot find tenant with id " + id));
    }

    public Tenant createTenant(String id, TenantDao tenant) {
        Room room = roomService.findRoomById(id);
        if (room.getTenant().size() >= room.getTotalTenants())
            throw new IndexOutOfBoundsException(String.format("Room %s, cannot add another tenant to this room", room.getRoomNumber()));

        LocalDate rentalDate = getRentalDate(tenant.getRentalDate());

        Tenant newTenant = new Tenant(tenant.getName(), tenant.getSurname(), tenant.getEmail(), tenant.getPhone(),
                tenant.getRentalFee(), rentalDate, room);
        return repo.save(newTenant);
    }

    public Tenant updateTenant(String id, TenantDao tenant) {
        Tenant oldTenant = getTenantById(id);
        LocalDate rentalDate = getRentalDate(tenant.getRentalDate());

        Tenant updateTenant = new Tenant(oldTenant.getId(), tenant.getName(), tenant.getSurname(), tenant.getEmail(),
                tenant.getPhone(), tenant.getRentalFee(), rentalDate, oldTenant.getCreatedAt(), oldTenant.getRoom());
        return repo.save(updateTenant);
    }

    public void deleteTenant(String id) {
        getTenantById(id);
        repo.deleteById(id);
    }

    private LocalDate getRentalDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new IndexOutOfBoundsException("Rental date cannot be in the past");
        if (date.isAfter(LocalDate.now().plusDays(35)))
            throw new IndexOutOfBoundsException("Rental date cannot be cannot be more than 35 days from today.");
        return date;
    }
}