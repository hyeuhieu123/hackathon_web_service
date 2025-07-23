package ra.hackathon.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hackathon.model.dto.request.BusRouteDto;
import ra.hackathon.model.dto.response.ApiResponse;
import ra.hackathon.model.entity.BusRoute;
import ra.hackathon.service.BusRouteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bus-routes")
public class BusRouteController {
    @Autowired
    private BusRouteService busRouteService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<BusRoute>>> getAllBusRoutes() {
        List<BusRoute> busRoutes = busRouteService.getAllBusRoutes();
        return new ResponseEntity<>(new ApiResponse<>(true, "List of bus routes", busRoutes, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BusRoute>> saveBusRoute(@Valid @RequestBody BusRouteDto busRoute) {
        BusRoute savedBusRoute = busRouteService.saveBusRoute(busRoute);
        return new ResponseEntity<>(new ApiResponse<>(true, "Bus route created successfully", savedBusRoute, HttpStatus.CREATED), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<BusRoute>> updateBusRoute(@Valid @PathVariable Long id, @RequestBody BusRouteDto busRoute) {
        BusRoute updatedBusRoute = busRouteService.updateBusRoute(id, busRoute);
        return new ResponseEntity<>(new ApiResponse<>(true, "Bus route updated successfully", updatedBusRoute, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBusRoute(@PathVariable Long id) {
        busRouteService.deleteBusRoute(id);
        return new ResponseEntity<>(new ApiResponse<>(true, "Bus route deleted successfully", null, HttpStatus.OK), HttpStatus.OK);
    }
}
