package co.adeshina.perseus.service.impl;

import co.adeshina.perseus.exception.InvalidEntityIdException;
import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.entity.Email;
import co.adeshina.perseus.repository.EmailRepository;
import co.adeshina.perseus.service.EmailService;
import co.adeshina.perseus.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, EntityMapper entityMapper) {
        this.emailRepository = emailRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public EmailDto updatePhoneNumber(long id, NewEmailDto newEmailDto) {
        Email email = emailRepository.findById(id).orElseThrow(() -> new InvalidEntityIdException("Invalid ID"));
        email.setEmail(newEmailDto.getEmail());
        email = emailRepository.save(email);
        return entityMapper.map(email, EmailDto.class);
    }
}
