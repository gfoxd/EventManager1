package dev.sashanara.eventmanager.Locations;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        return locationService.getAllLocations()
                .stream()
                .map(locationConverter::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<LocationDto> postLocation(
            @Valid @RequestBody LocationDto locationDto
    ){
        log.info("LocationController request postLocation");

        LocationDto location = locationConverter
                .toDto(locationService
                                .createLocation(locationConverter
                                        .toDomain(locationDto)));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(location);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity deleteLocation(
            @PathVariable ("locationId") Long id
    ){
        log.info("LocationController request deleteLocation");

        locationService.deleteLocationById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(
            @PathVariable ("locationId")  Long id
    ){
        log.info("LocationController request getLocationById");

        LocationDto locationDto = locationConverter
                .toDto(locationService
                        .getLocationById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationDto);

    }

    @PutMapping("/{locationId}")
    public ResponseEntity updateLocation(
            @PathVariable ("locationId") Long id,
            @Valid @RequestBody LocationDto locationDto
    ){
        log.info("LocationController request updateLocation");

        locationDto = locationConverter.toDto(
                locationService.updateLocation(
                        id, locationConverter.toDomain(locationDto)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationDto);
    }

}
