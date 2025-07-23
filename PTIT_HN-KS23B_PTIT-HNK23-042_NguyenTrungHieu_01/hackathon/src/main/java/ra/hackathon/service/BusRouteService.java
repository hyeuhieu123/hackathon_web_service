package ra.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.hackathon.model.dto.request.BusRouteDto;
import ra.hackathon.model.entity.BusRoute;
import ra.hackathon.repository.BusRepository;
import ra.hackathon.repository.BusRouteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BusRouteService {
    @Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusRepository busRepository;
    public List<BusRoute> getAllBusRoutes(){
        return busRouteRepository.findAll();
    }

    public BusRoute saveBusRoute(BusRouteDto  dto) {
        BusRoute busRoute = convertDtoToEntity(dto);
        return busRouteRepository.save(busRoute);
    }

    public BusRoute updateBusRoute(Long id, BusRouteDto busRouteDto) {
        BusRoute busRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bus route not found with id: " + id));

        BusRoute updatedBusRoute = convertDtoToEntity(busRouteDto);
        updatedBusRoute.setBus_route_id(busRoute.getBus_route_id());



        return busRouteRepository.save(updatedBusRoute);
    }
    public void deleteBusRoute(Long id) {
        BusRoute busRoute = getBusRouteById(id);
        busRouteRepository.updateStatusById(id);
    }

    public BusRoute getBusRouteById(Long id) {
        return busRouteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bus route not found with id: " + id));
    }
    public BusRoute convertDtoToEntity(BusRouteDto dto) {
        return BusRoute.builder()
                .start_point(dto.getStart_point())
                .end_point(dto.getEnd_point())
                .trip_information(dto.getTrip_information())
                .driver_name(dto.getDriver_name())
                .status(dto.isStatus())
                .bus(busRepository.findById(dto.getBus_id()).orElse(null))
                .build();
    }
}
