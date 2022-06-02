package co.adeshina.perseus.controller;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.service.EmailService;
import co.adeshina.perseus.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts/update")
public class ContactDataController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Operation(summary = "Update existing email address")
    @PutMapping("/email/{id}")
    public ResponseEntity<EmailDto> updateEmail(
            @Parameter(description = "ID of the email address to update") @PathVariable("id") long id,
            @Parameter(description = "Email Data") @RequestBody NewEmailDto newEmailDto) {
        EmailDto emailDto = emailService.updateEmail(id, newEmailDto);
        return ResponseEntity.ok(emailDto);
    }

    @Operation(summary = "Update existing phone number")

    @PutMapping("/phone/{id}")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(
            @Parameter(description = "ID of phone number to update") @PathVariable("id") long id,
            @Parameter(description = "Phone number data") @RequestBody NewPhoneNumberDto newPhoneNumberDto) {
        PhoneNumberDto phoneNumberDto = phoneNumberService.updatePhoneNumber(id, newPhoneNumberDto);
        return ResponseEntity.ok(phoneNumberDto);
    }
}
