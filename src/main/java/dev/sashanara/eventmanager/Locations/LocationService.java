package dev.sashanara.eventmanager.Locations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

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

    @Transactional
    public List<Location> getAllLocations() {
        var locations = locationRepository
                .findAll()
                .stream()
                .map(locationConverter::fromEntityToDomain)
                .toList();

        return locations;
    }

    @Transactional
    public Location createLocation(Location location) {

        LocationEntity locationEntity = locationRepository
                .save(locationConverter.toEntity(location));

        return locationConverter
                .fromEntityToDomain(locationEntity);
    }

    @Transactional
    public Location getLocationById(Long id) {

        if(!locationRepository.existsById(id)){
            throw new EntityNotFoundException("Location not found with id: " + id);
        }

        LocationEntity locationEntity = locationRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Location not found with id: " + id));

        Location location = locationConverter
                .fromEntityToDomain(locationEntity);

        return location;
    }

    @Transactional
    public void deleteLocationById(Long id) {

        if (!locationRepository.existsById(id)) {
            throw new EntityNotFoundException("Location not found with id: " + id);
        }

        locationRepository.deleteById(id);
    }

    @Transactional
    public Location updateLocation(Long id, Location locationToUpdate) {

        if(!locationRepository.existsById(id)){
            throw new EntityNotFoundException("Location not found with id: " + id);
        }

        locationRepository.updateLocation(
                id,
                locationToUpdate.name(),
                locationToUpdate.address(),
                locationToUpdate.capacity(),
                locationToUpdate.description()
        );

        return locationConverter.fromEntityToDomain(
                locationRepository.findById(id)
                        .orElseThrow());
    }

}
