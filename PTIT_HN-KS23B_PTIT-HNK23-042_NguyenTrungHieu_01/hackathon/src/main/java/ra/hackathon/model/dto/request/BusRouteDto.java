package ra.hackathon.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusRouteDto {

   @NotBlank(message = "start_point cannot be blank")
    private String start_point;
    @NotBlank(message = "end_point cannot be blank")
     private String end_point;
    @NotBlank(message = "trip_information cannot be blank")
    private String trip_information;
    @NotBlank(message = "driver_name cannot be blank")
    private String driver_name;
    @NotNull(message = "status cannot be null")
    private boolean status=true;
    @NotNull( message = "bus_id cannot be null")
    private Long bus_id;
}
