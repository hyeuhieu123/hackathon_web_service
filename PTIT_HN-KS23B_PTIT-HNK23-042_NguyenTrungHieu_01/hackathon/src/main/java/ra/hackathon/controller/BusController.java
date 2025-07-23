package ra.hackathon.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.hackathon.model.dto.request.BusDto;
import ra.hackathon.model.dto.response.ApiResponse;
import ra.hackathon.model.entity.Bus;
import ra.hackathon.repository.BusSpecification;
import ra.hackathon.service.BusService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/buses")
public class BusController {
    @Autowired
    private BusService busService;
        @GetMapping
        public ResponseEntity<ApiResponse<List<Bus>>>getAllBuses(@RequestParam(required = false) String status){


//            Specification<Bus> specification = Specification.allOf(BusSpecification.hasStatus(status));
            List<Bus> buses = busService.findAll();
            return new ResponseEntity<>(new ApiResponse<>(true,"danh sach bus",buses, HttpStatus.OK), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<ApiResponse<Bus>> saveBus(@Valid @ModelAttribute BusDto busDto) throws IOException {
            Bus bus = busService.save(busDto);
            return new ResponseEntity<>(new ApiResponse<>(true,"them bus thanh cong",bus, HttpStatus.CREATED), HttpStatus.CREATED);

        }
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Bus>> updateBus(@PathVariable Long id, @Valid @ModelAttribute BusDto busDto) throws IOException {
            Bus bus = busService.update(id, busDto);
            return new ResponseEntity<>(new ApiResponse<>(true, "Update bus successfully", bus, HttpStatus.OK), HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Bus>> deleteBus(@PathVariable Long id) {
            Bus bus = busService.delete(id);
            return new ResponseEntity<>(new ApiResponse<>(true, "Delete bus successfully", bus, HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
        }
}
