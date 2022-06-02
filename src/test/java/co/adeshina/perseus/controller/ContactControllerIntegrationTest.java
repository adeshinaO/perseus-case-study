package co.adeshina.perseus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.entity.Email;
import co.adeshina.perseus.model.entity.PhoneNumber;
import co.adeshina.perseus.model.entity.User;
import co.adeshina.perseus.repository.EmailRepository;
import co.adeshina.perseus.repository.PhoneNumberRepository;
import co.adeshina.perseus.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String MOCK_NAME = "Adeshina";
    private static final String MOCK_EMAIL = "email@";
    private static final String MOCK_PHONE_NUMBER = "09100";

    @Test
    @DisplayName("Update A Phone Number")
    public void updatePhoneNumber() throws Exception {

        User user = new User();
        user.setFirstName(MOCK_NAME);
        user.setLastName(MOCK_NAME);
        user = userRepository.save(user);

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("old_phone_number");
        phoneNumber.setUser(user);
        long phoneNumberId = phoneNumberRepository.save(phoneNumber).getId();

        NewPhoneNumberDto newPhoneNumberDto = new NewPhoneNumberDto();
        newPhoneNumberDto.setPhoneNumber(MOCK_PHONE_NUMBER);
        String jsonBody = objectMapper.writeValueAsString(newPhoneNumberDto);

        String uri = String.format("/contacts/update/phone/%s", phoneNumberId);

        this.mockMvc.perform(put(uri)
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.phoneNumber").value(MOCK_PHONE_NUMBER));

    }


    @Test
    @DisplayName("Update An Email")
    public void updateEmail() throws Exception {

        User user = new User();
        user.setFirstName(MOCK_NAME);
        user.setLastName(MOCK_NAME);
        user = userRepository.save(user);

        Email email = new Email();
        email.setEmail("old_email");
        email.setUser(user);
        long emailId = emailRepository.save(email).getId();

        NewEmailDto emailDto = new NewEmailDto();
        emailDto.setEmail(MOCK_EMAIL);
        String jsonBody = objectMapper.writeValueAsString(emailDto);

        String uri = String.format("/contacts/update/email/%s", emailId);

        this.mockMvc.perform(put(uri)
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value(MOCK_EMAIL));

    }
}
