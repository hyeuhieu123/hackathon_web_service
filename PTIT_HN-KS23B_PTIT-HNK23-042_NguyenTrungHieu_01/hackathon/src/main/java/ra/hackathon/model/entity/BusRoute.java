package ra.hackathon.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bus_route_id;
    @Column(name = "start_point",nullable = false,length = 255)
    private String start_point;
    @Column(name = "end_point",nullable = false,length = 255)
    private String end_point;
    @Column(name = "trip_information",nullable = false,length = 255)
    private String trip_information;
    @Column(name = "driver_name",nullable = false,length = 70)
    private String driver_name;
    @Column(name = "status ",nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonIgnore
    private Bus bus;
}
