package co.adeshina.perseus.service;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    EmailDto updatePhoneNumber(long id, NewEmailDto newEmailDto);
}
