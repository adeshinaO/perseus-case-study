package co.adeshina.perseus.service;

import co.adeshina.perseus.exception.InvalidEntityIdException;
import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.model.dto.response.UserDto;
import java.util.List;

public interface UserService {

    /**
     * Creates a new user with the given data.
     * @param createUserDto
     * @return
     */
    UserDto createUser(NewUserDto createUserDto);

    /**
     * Returns the user matching the given ID.
     * @param id ID of user to find
     * @return Data of the user.
     * @throws InvalidEntityIdException if no database record matching given user ID.
     */
    UserDto findById(long id);

    List<UserDto> findByName(String firstName, String lastName);

    List<PhoneNumberDto> addPhoneNumber(long userId, NewPhoneNumberDto createPhoneNumberDto);

    List<EmailDto> addEmail(long userId, NewEmailDto createEmailDto);

    void delete(long id);
}
