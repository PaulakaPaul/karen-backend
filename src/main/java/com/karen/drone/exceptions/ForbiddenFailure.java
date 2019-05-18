package com.karen.drone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 01/04/2019
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenFailure extends RuntimeException {

    public ForbiddenFailure(String message) {
        super(message);
    }

}