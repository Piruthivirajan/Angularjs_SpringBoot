package user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.controller.UserController;
import com.example.model.User;
import com.example.service.UserService;

import user.builders.UserBuilder;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private static final Integer USER_ID=1;
	private static final User EXISTING_USER=new UserBuilder().setId(USER_ID).setName("raja").setPassword("raja").Build();
	private static final User ANOTHER_USER=new UserBuilder().setId(2).setName("test").setPassword("test").Build();
	private static final User NEW_USER=new UserBuilder().setId(USER_ID).setName("raja").setPassword("raja").Build();
	
	@InjectMocks
	private UserController usercontroller;
	@Mock
	private UserService userservice;  
	
	@Test
	public void WhenCreatingUserItShouldRetureTheSavedUser(){
		given(userservice.create(NEW_USER)).willReturn(EXISTING_USER);
		assertThat(usercontroller.addItem(NEW_USER)).isSameAs(EXISTING_USER);	
	}
	@Test
	public void WhenUpdateUserItShouldReturnTheSavedUser(){
		given(userservice.findone(USER_ID)).willReturn(EXISTING_USER);
		given(userservice.create(EXISTING_USER)).willReturn(EXISTING_USER);
		//assertThat(usercontroller.updateItem(EXISTING_USER,USER_ID)).isSameAs(EXISTING_USER);
	}
	@Test
	public void whenReadingdingCharacterItShouldReturnAllCharacters() {
		given(userservice.findall()).willReturn(Arrays.asList(EXISTING_USER, ANOTHER_USER));
		assertThat(usercontroller.findItems()).containsOnly(EXISTING_USER, ANOTHER_USER);
	}
	@Test
	public void whenDeletingACharacterItShouldUseTheRepository() {
		usercontroller.deleteItem(USER_ID);
		verify(userservice).delete(USER_ID);
	}
}
