package co.adeshina.perseus.controller;

import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone-numbers")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(
            @PathVariable("id") long id,
            @RequestBody NewPhoneNumberDto newPhoneNumberDto) {
        PhoneNumberDto phoneNumberDto = phoneNumberService.updatePhoneNumber(id, newPhoneNumberDto);
        return ResponseEntity.ok(phoneNumberDto);
    }
}
