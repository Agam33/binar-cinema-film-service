package com.ra.filmservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class BioskopException {

    public BioskopException() {}

    public static RuntimeException throwException(ExceptionType exceptionType,
                                        HttpStatus statusCode, String msg) {
        return switch (exceptionType) {
            case DUPLICATE_ENTITY -> new DuplicateEntityException(statusCode, msg);
            case NOT_FOUND -> new EntityNotFoundException(statusCode, msg);
            default -> new RuntimeException(msg);
        };
    }

    @Setter
    @Getter
    public static class DuplicateEntityException extends RuntimeException {
        private final HttpStatus statusCode;

        public DuplicateEntityException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class EntityNotFoundException extends RuntimeException {
        private final HttpStatus statusCode;

        public EntityNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }
}
