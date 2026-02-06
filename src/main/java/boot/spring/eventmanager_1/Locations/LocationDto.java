package boot.spring.eventmanager_1.Locations;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record LocationDto (

        @Null
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String address,

        @Min(0)
        Integer capacity,

        String description
){

}
