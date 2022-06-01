package co.adeshina.perseus.service;

import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberService {
    PhoneNumberDto updatePhoneNumber(long id, NewPhoneNumberDto newPhoneNumberDto);
}
