package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @JsonIgnore
    @ManyToOne
    private Building building;

    @OneToOne
    private Tenant tenant;

    public Room(String roomNumber, int tenantsOccupied, int totalTenants, double totalRentalPrice, double servicesCosts) {
        this.roomNumber = roomNumber;
        this.tenantsOccupied = tenantsOccupied;
        this.totalTenants = totalTenants;
        this.totalRentalPrice = totalRentalPrice;
        this.servicesCosts = servicesCosts;
    }
}
