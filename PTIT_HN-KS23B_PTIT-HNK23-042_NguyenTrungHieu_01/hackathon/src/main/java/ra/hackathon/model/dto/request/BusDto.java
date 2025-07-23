package ra.hackathon.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    @NotBlank(message = "Bus name is required")
    private String bus_name;
    @NotBlank(message = "Registration number is required")
    private String registration_number;
    @NotNull(message = "Total seats is required")
    private int total_seats;
    @NotNull(message = "Image is required")
    private MultipartFile image_bus;
    @NotNull
    private boolean status;
}
