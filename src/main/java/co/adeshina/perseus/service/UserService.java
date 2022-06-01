package co.adeshina.perseus.service;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.model.dto.response.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(NewUserDto createUserDto);

    UserDto findById(long id);

    List<UserDto> findByName(String firstName, String lastName);

    List<PhoneNumberDto> addPhoneNumber(long userId, NewPhoneNumberDto createPhoneNumberDto);

    List<EmailDto> addEmail(long userId, NewEmailDto createEmailDto);

    void delete(long id);
}
