package boot.spring.eventmanager_1.Locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final LocationConverter locationConverter;
    private final LocationRepository locationRepository;

    public LocationService(LocationConverter locationConverter,
                           LocationRepository locationRepository) {
        this.locationConverter = locationConverter;
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        var locations = locationRepository
                .findAll()
                .stream()
                .map(locationConverter::fromEntityToDomain)
                .toList();

        return locations;
    }

}
