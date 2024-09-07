package za.co.tyaphile.tenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 500, nullable = false)
    private String name;
    @Column(length = 500, nullable = false)
    private String address;

    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "building", orphanRemoval = true)
    private List<Room> room;

    public Building(String name, String address, User user) {
        this.name = name;
        this.address = address;
        this.user = user;
    }
}
