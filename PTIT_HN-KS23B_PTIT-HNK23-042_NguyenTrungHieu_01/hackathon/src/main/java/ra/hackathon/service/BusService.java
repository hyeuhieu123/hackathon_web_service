package ra.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ra.hackathon.advice.customException;
import ra.hackathon.model.dto.request.BusDto;
import ra.hackathon.model.entity.Bus;
import ra.hackathon.repository.BusRepository;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BusService {
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private BusRepository busRepository;

    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    public Bus save(BusDto dto) throws IOException {


        if (dto.getImage_bus() == null || dto.getImage_bus().isEmpty()) {
            throw new customException("Image bus is required");
        }
        Bus bus = convertDtoToEntity(dto);
        String url = cloudinaryService.uploadImage(dto.getImage_bus());
        bus.setImage_bus(url);
        return busRepository.save(bus);
    }

    public Bus update(Long id, BusDto dto) throws IOException {
        Bus bus = findById(id);
        if (dto.getImage_bus() != null && !dto.getImage_bus().isEmpty()) {
            String url = cloudinaryService.uploadImage(dto.getImage_bus());
            bus.setImage_bus(url);
        }
        bus.setBus_name(dto.getBus_name());
        bus.setRegistration_number(dto.getRegistration_number());
        bus.setTotal_seats(dto.getTotal_seats());
        bus.setStatus(dto.isStatus());
        return busRepository.save(bus);
    }

    public Bus findById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bus not found with id: " + id));
    }

    public Bus delete(Long id) {
        Bus bus = findById(id);
        if(bus==null){
            throw new customException("Bus not found with id: " + id);
        }
       if ( busRepository.existsBusByBusRoutes(bus.getBusRoutes())){
            throw new customException("xoa that bai, xe da co lich trinh");
       }
        busRepository.updateStatusById(bus.getBus_id());
        return bus;
    }
    public Bus convertDtoToEntity(BusDto busDto) {
        return Bus.builder()
                .bus_name(busDto.getBus_name())
                .registration_number(busDto.getRegistration_number())
                .total_seats(busDto.getTotal_seats())
                .build();
    }

}
