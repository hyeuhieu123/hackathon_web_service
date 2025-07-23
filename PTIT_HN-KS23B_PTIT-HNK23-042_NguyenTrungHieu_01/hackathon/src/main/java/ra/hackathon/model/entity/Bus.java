package ra.hackathon.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long bus_id;
    @Column(name = "bus_name",unique = true,nullable = false,length = 100)
    private String bus_name;
    @Column(name = "registration_number",nullable = false,unique = true,length = 30)
    private String registration_number;
    @Column(name = "total_seats",nullable = false)
    private int total_seats;
    @Column(name = "image_bus")
    private String image_bus;
    @Column(name = "status")
    private boolean status=true;
    @OneToMany(mappedBy = "bus")
    List<BusRoute> busRoutes;



}
