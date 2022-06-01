package co.adeshina.perseus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class EmailController {

    @PutMapping("/update-phone/{phone_number_id}")
    public ResponseEntity<Void> updatePhoneNumber() {

    }

    @PutMapping("/update-email/{email_id}")
    public ResponseEntity<Void> updateEmail() {

    }
}
