package co.adeshina.perseus.service.impl;

import co.adeshina.perseus.service.PhoneNumberService;
import co.adeshina.perseus.exception.InvalidEntityIdException;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.model.entity.PhoneNumber;
import co.adeshina.perseus.repository.PhoneNumberRepository;
import co.adeshina.perseus.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository, EntityMapper entityMapper) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public PhoneNumberDto updatePhoneNumber(long id, NewPhoneNumberDto newPhoneNumberDto) {
        PhoneNumber phoneNumber =
                phoneNumberRepository.findById(id).orElseThrow(() -> new InvalidEntityIdException("Invalid ID"));
        phoneNumber.setPhoneNumber(newPhoneNumberDto.getPhoneNumber());
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return entityMapper.map(phoneNumber, PhoneNumberDto.class);
    }
}
