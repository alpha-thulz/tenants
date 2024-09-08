package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String roomNumber;
    private int tenantsOccupied;
    private int totalTenants;
    private double totalRentalPrice;
    private double servicesCosts;
    private boolean isUnderMaintenance;

    @JsonIgnore
    @ManyToOne
    private Building building;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<Tenant> tenant;

    public Room(String roomNumber, int tenantsOccupied, int totalTenants, double totalRentalPrice,
                double servicesCosts, boolean isUnderMaintenance, Building building) {
        this.roomNumber = roomNumber;
        this.tenantsOccupied = tenantsOccupied;
        this.totalTenants = totalTenants;
        this.totalRentalPrice = totalRentalPrice;
        this.servicesCosts = servicesCosts;
        this.isUnderMaintenance = isUnderMaintenance;
        this.building = building;
    }
}
