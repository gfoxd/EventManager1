package boot.spring.eventmanager_1.Locations;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);

    private LocationService locationService;
    private LocationConverter locationConverter;

    public LocationController(
            LocationService locationService,
            LocationConverter locationConverter
    ) {
        this.locationService = locationService;
        this.locationConverter = locationConverter;
    }

    @GetMapping
    public List<LocationDto> getAllLocations() {
        log.info("LocationController request getAllLocations");

        List<LocationDto> locations = locationService.getAllLocations()
                .stream()
                .map(locationConverter::toDto)
                .toList();

        return locations;
    }

    @PostMapping
    public String postLocation(
            @Valid @RequestBody LocationDto locationDto
    ){
        return "Location";
    }

    @DeleteMapping("/locations/{locationId}")
    public void deleteLocation(
            @PathVariable ("locationId") Long id
    ){

    }

    @GetMapping("/locations/{locationId}")
    public String getLocationById(
            @PathVariable ("locationId")  Long id
    ){
        return "Location";
    }

    @PutMapping("/locations/{locationId}")
    public String updateLocation(
            @PathVariable ("locationId") Long id,
            @Valid @RequestBody LocationDto locationDto
    ){
        return "Location";
    }

}
