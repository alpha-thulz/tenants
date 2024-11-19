package za.co.tyaphile.tenants.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDao {
    @NotBlank(message = "Building name cannot be empty")
    private String name;
    @NotBlank(message = "Building address cannot be empty")
    private String address;
}
