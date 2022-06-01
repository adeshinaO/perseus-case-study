package co.adeshina.perseus.service.impl;

import co.adeshina.perseus.exception.InvalidEntityIdException;
import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.model.dto.response.UserDto;
import co.adeshina.perseus.model.entity.Email;
import co.adeshina.perseus.model.entity.PhoneNumber;
import co.adeshina.perseus.model.entity.User;
import co.adeshina.perseus.repository.EmailRepository;
import co.adeshina.perseus.repository.PhoneNumberRepository;
import co.adeshina.perseus.repository.UserRepository;
import co.adeshina.perseus.service.UserService;
import co.adeshina.perseus.util.EntityMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PhoneNumberRepository phoneNumberRepository,
            EmailRepository emailRepository,
            EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.emailRepository = emailRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public UserDto createUser(NewUserDto createUserDto) {
        User user = entityMapper.map(createUserDto, User.class);
        user = userRepository.save(user);
        return entityMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new InvalidEntityIdException("No user with given ID"));
        return entityMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findByName(String firstName, String lastName) {
        List<User> users = userRepository.findByFirstNameAndLastName(firstName, lastName);
        return users.stream().map(user -> entityMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PhoneNumberDto> addPhoneNumber(long userId, NewPhoneNumberDto createPhoneNumberDto) {
        User user = userRepository.findById(userId)
                              .orElseThrow(() -> new InvalidEntityIdException("No user with given ID"));

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(createPhoneNumberDto.getPhoneNumber());
        List<PhoneNumber> userNumbers = user.getPhoneNumbers();
        userNumbers.add(phoneNumber);
        user = userRepository.save(user);

        return user.getPhoneNumbers().stream()
                   .map(p -> entityMapper.map(p, PhoneNumberDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> addEmail(long userId, NewEmailDto createEmailDto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new InvalidEntityIdException("No user with given ID"));

        Email email = new Email();
        email.setMail(createEmailDto.getEmail());
        List<Email> emails = user.getEmails();
        emails.add(email);
        user = userRepository.save(user);
        return user.getEmails().stream().map(e -> entityMapper.map(e, EmailDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
