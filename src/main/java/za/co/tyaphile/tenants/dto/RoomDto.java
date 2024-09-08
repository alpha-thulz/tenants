package za.co.tyaphile.tenants.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    @NotBlank(message = "Please enter the room number")
    private String roomNumber;
    @NotNull(message = "Please advise how many tenants can share the room, enter 1 if tenant will not share the room")
    private int totalTenants;
    @NotNull(message = "How much is the total rent of the room?")
    private double totalRentalPrice;
    private double servicesCosts;
    private boolean isUnderMaintenance;
}