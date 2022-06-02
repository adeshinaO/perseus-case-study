package co.adeshina.perseus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.entity.User;
import co.adeshina.perseus.repository.EmailRepository;
import co.adeshina.perseus.repository.PhoneNumberRepository;
import co.adeshina.perseus.repository.UserRepository;
import co.adeshina.perseus.service.impl.UserServiceImpl;
import co.adeshina.perseus.util.EntityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class UserServiceImplTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final PhoneNumberRepository phoneNumberRepository = Mockito.mock(PhoneNumberRepository.class);
    private final EmailRepository emailRepository = Mockito.mock(EmailRepository.class);
    private final EntityMapper entityMapper = Mockito.mock(EntityMapper.class);

    private static final String MOCK_FIRST_NAME = "Ade";
    private static final String MOCK_LAST_NAME = "Ogunmodede";

    @Test
    @DisplayName("Verify User Service Interactions With Repositories")
    public void userServiceInteractions() {
        UserServiceImpl userService =
                new UserServiceImpl(userRepository, phoneNumberRepository, emailRepository, entityMapper);

        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setFirstName(MOCK_FIRST_NAME);
        newUserDto.setLastName(MOCK_LAST_NAME);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        userService.createUser(newUserDto);
        verify(userRepository).save(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();

        assertEquals(MOCK_FIRST_NAME, user.getFirstName());
        assertEquals(MOCK_LAST_NAME, user.getLastName());
    }
}
