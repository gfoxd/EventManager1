package dev.sashanara.eventmanager.Exeptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServerErrorDto> handleEntityNotFoundException(
            EntityNotFoundException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Сущность не найдена",
                e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServerErrorDto> handleValidationExceptions(
            ValidationException e
    ){
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Некорректный запрос (ошибка валидации)",
                e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ServerErrorDto> handleGenerisException(
            Exception e
    ){
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Внутренняя ошибка сервера",
                e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDto);
    }

    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<ServerErrorDto> handleViolationLoginException(
            LoginAlreadyExistsException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Ошибка валидации данных",
                "Пользователь с таким логином уже существует",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(AgeValidationException.class)
    public ResponseEntity<ServerErrorDto> handleValidationAgeException(
            AgeValidationException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Ошибка валидации данных",
                "Пользователь должен быть совершеннолетним",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(DurationValidationException.class)
    public ResponseEntity<ServerErrorDto> handleValidationDurationException(
            DurationValidationException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Ошибка валидации данных",
                "Длительность мероприятия должна быть больше или равна 30",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(DateTimeValidationException.class)
    public ResponseEntity<ServerErrorDto> handleValidationDateTimeException(
            DateTimeValidationException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Ошибка валидации данных",
                "кол-во мест мероприятия должно быть меньше или равно вместимости места",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(InsufficientRightsException.class)
    public ResponseEntity<ServerErrorDto> handleInsufficientRightsException(
            InsufficientRightsException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "недостаточно прав",
                "запрос должен исходить от владельца или админа",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(EventStatusExceptions.class)
    public ResponseEntity<ServerErrorDto> handleEventStatusExceptions(
            EventStatusExceptions e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Статус события несовместим",
                "запрос невозможен с текущим статусом события",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(NotEnoughPlacesException.class)
    public ResponseEntity<ServerErrorDto> handleNotEnoughPlacesException(
            NotEnoughPlacesException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Недостаточно мест",
                "Все места уже заняты",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(maxPlacesDuringUpdateException.class)
    public ResponseEntity<ServerErrorDto> handleMaxPlacesDuringUpdateException(
            maxPlacesDuringUpdateException e
    ) {
        logger.error("Got exception: " + e.getMessage());

        var errorDto = new ServerErrorDto(
                "Недостаточно мест при обновлении",
                "Мест при обновлении должно быть больше чем уже зарегистрировано",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }
}
