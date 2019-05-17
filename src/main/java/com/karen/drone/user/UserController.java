package com.karen.drone.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@RestController
public class UserController {

    @GetMapping(path="/")
    public ResponseEntity getConnectionDetails() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
