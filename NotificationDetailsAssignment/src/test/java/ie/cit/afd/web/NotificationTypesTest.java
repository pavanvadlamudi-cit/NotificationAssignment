package ie.cit.afd.web;

import ie.cit.afd.dao.NotificationTypesRepository;
import ie.cit.afd.models.NotificationTypes;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;


public class NotificationTypesTest {
	private NotificationTypesRepository repo;
	private NotificationTypesController tested;
	@Before
	public void setup() {
		repo = mock(NotificationTypesRepository.class);
		tested = new NotificationTypesController(repo);
		
	}
	
	@Test
	public void testGetAll() {
		List<NotificationTypes> all = tested.getAllNotificationTypeItems();
		assertThat(all, notNullValue());
		verify(repo).getAll();
	}

	@Test
	public void testGetAllNotificaitonTypeItems() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		String view = tested.getAll(model);
		assertThat(view, CoreMatchers.equalTo("notificationtypes"));
		assertThat(model.get("notificationtypes"), notNullValue());
		verify(repo).getAll();
	}

	@Test
	public void testCreate() {
		tested.create("NF03","Description of NF03");
		Mockito.verify(repo).insert(
				Matchers.argThat(new ArgumentMatcher<NotificationTypes>() {

					@Override
					public boolean matches(Object argument) {
						return ((NotificationTypes) argument).getCode().equals("NF03");
					}

					@Override
					public void describeTo(Description description) {
						description.appendText("expected: some NF03");
					}
				}));

	}
	
}
