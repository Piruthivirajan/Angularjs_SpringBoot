package user.controller;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.Application;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import user.builders.UserBuilder;

@RunWith(SpringJUnit4ClassRunner.class) // 1
@SpringApplicationConfiguration(classes = Application.class) // 2
@WebAppConfiguration // 3
@IntegrationTest("server.port:0")
public class UserControllerIT {

	private static final int USER_ID = 1;
	private static final String NAME_FIELD = "name";
	private static final String USER_RESOURCES = "/users";
	private static final String USER_RESOURCE = "/users/{id}";

	private static final String FIRST_USER_NAME = "Mickey";
	private static final String SECOND_USER_NAME = "Goofy";
	private static final String THIRD_USER_NAME = "Simba";

	private static final User FIRST_USER = new UserBuilder().setId(USER_ID).setName("Mickey").setPassword("raja")
			.Build();
	private static final User SECOND_USER = new UserBuilder().setId(2).setName("Goofy").setPassword("test").Build();
	private static final User THIRD_USER = new UserBuilder().setId(USER_ID).setName("Simba").setPassword("raja")
			.Build();

	@Autowired
	private UserRepository userrepository;

	@Value("${local.server.port}")
	private int serverPort;
	private User firstUser;
	private User secondUser;

	@Before
	public void setUp() {
		userrepository.deleteAll();
		firstUser = userrepository.save(FIRST_USER);
		secondUser = userrepository.save(SECOND_USER);
		RestAssured.port = serverPort;
	}

	@Test
	public void createUserhouldReturnSavedUser() {
		given().body(THIRD_USER).contentType(ContentType.JSON).when().post(USER_RESOURCES).then()
				.statusCode(HttpStatus.SC_OK).body(NAME_FIELD, is(THIRD_USER_NAME));
		System.out.println("addItemShouldReturnSavedItem completed !!!");
	}

	@Test
	public void updateUserShouldReturnUpdatedUser() {
		User user = new UserBuilder().setName(FIRST_USER_NAME).Build();
		given().body(user).contentType(ContentType.JSON).when().post(USER_RESOURCE, firstUser.getId()).then()
				.statusCode(HttpStatus.SC_OK).body(NAME_FIELD, is(FIRST_USER_NAME));
	}

	@Test
	public void getUserShouldReturnBothUsers() {
		when().get(USER_RESOURCES).then().statusCode(HttpStatus.SC_OK).body(NAME_FIELD,
				hasItems(FIRST_USER_NAME, SECOND_USER_NAME));
	}

	@Test
	public void deleteUserShouldReturnNoContent() {
		when().delete(USER_RESOURCE, firstUser.getId()).then().statusCode(HttpStatus.SC_NO_CONTENT);
	}

}
