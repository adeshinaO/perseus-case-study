package co.adeshina.perseus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.entity.User;
import co.adeshina.perseus.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String MOCK_NAME = "Adeshina";
	private static final String MOCK_EMAIL = "email@";
	private static final String MOCK_PHONE_NUMBER = "09100";

	@Test
	@DisplayName("Create New User")
	public void shouldCreateUser() throws Exception {

		NewPhoneNumberDto phoneNumberDto = new NewPhoneNumberDto();
		phoneNumberDto.setPhoneNumber(MOCK_PHONE_NUMBER);

		NewUserDto newUserDto = new NewUserDto();
		newUserDto.setFirstName(MOCK_NAME);
		newUserDto.setLastName("Ade");
		newUserDto.setPhoneNumbers(List.of(phoneNumberDto));

		String jsonBody = objectMapper.writeValueAsString(newUserDto);

		this.mockMvc.perform(post("/users/create")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.firstName").value(MOCK_NAME))
					.andExpect(jsonPath("$.phoneNumbers[0].phoneNumber").value(MOCK_PHONE_NUMBER));
	}

	@Test
	@DisplayName("Get A User")
	public void getUser() throws Exception {
		User user = new User();
		user.setFirstName(MOCK_NAME);
		user.setLastName(MOCK_NAME);

		long userId = userRepository.save(user).getId();

		this.mockMvc.perform(get("/users/" + userId))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.firstName").value(MOCK_NAME));
	}

	@Test
	@DisplayName("Trigger Error Response")
	public void getError() throws Exception {
		this.mockMvc.perform(get("/users/" + 9999))
					.andDo(print())
					.andExpect(status().is4xxClientError())
					.andExpect(jsonPath("$.message").isNotEmpty());
	}

	@Test
	@DisplayName("Find User By Name")
	public void findByName() throws Exception {
		User user = new User();
		user.setFirstName(MOCK_NAME);
		user.setLastName(MOCK_NAME);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("firstName", MOCK_NAME);
		params.add("lastName", MOCK_NAME);

		this.mockMvc.perform(get("/users/find-by-name").params(params))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].firstName").value(MOCK_NAME))
					.andExpect(jsonPath("$[0].lastName").value(MOCK_NAME));
	}

	@Test
	@DisplayName("Add User Phone Number")
	public void addPhoneNumber() throws Exception {

		User user = new User();
		user.setFirstName(MOCK_NAME);
		user.setLastName(MOCK_NAME);
		long userId = userRepository.save(user).getId();

		NewPhoneNumberDto newPhoneNumberDto = new NewPhoneNumberDto();
		newPhoneNumberDto.setPhoneNumber(MOCK_PHONE_NUMBER);
		String jsonBody = objectMapper.writeValueAsString(newPhoneNumberDto);

		String uri = String.format("/users/%s/add/phone", userId);

		this.mockMvc.perform(patch(uri)
					 .content(jsonBody)
					 .contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].phoneNumber").value(MOCK_PHONE_NUMBER));
	}

	@Test
	@DisplayName("Add User Email")
	public void addEmail() throws Exception {

		User user = new User();
		user.setFirstName(MOCK_NAME);
		user.setLastName(MOCK_NAME);
		long userId = userRepository.save(user).getId();

		NewEmailDto emailDto = new NewEmailDto();
		emailDto.setEmail(MOCK_EMAIL);
		String jsonBody = objectMapper.writeValueAsString(emailDto);
		String uri = String.format("/users/%s/add/email", userId);

		this.mockMvc.perform(patch(uri)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].email").value(MOCK_EMAIL));
	}

	@Test
	@DisplayName("Delete User")
	public void deleteUser() throws Exception {
		User user = new User();
		user.setFirstName(MOCK_NAME);
		user.setLastName(MOCK_NAME);

		long userId = userRepository.save(user).getId();

		this.mockMvc.perform(delete("/users/" + userId))
					.andDo(print())
					.andExpect(status().isOk());
	}
}
