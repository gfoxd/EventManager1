package boot.spring.eventmanager_1.Locations;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.UniqueElements;

public record LocationDto (

        @Null
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String address,

        @Min(0)
        @Max(1_000_000)
        Integer capacity,

        String description
){

}
