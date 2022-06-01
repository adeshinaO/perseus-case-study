package co.adeshina.perseus.controller;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PutMapping("/{email_id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable("email_id") long id, NewEmailDto newEmailDto) {
        EmailDto emailDto = emailService.updatePhoneNumber(id, newEmailDto);
        return ResponseEntity.ok(emailDto);
    }
}
