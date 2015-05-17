package ie.cit.afd.web;

import ie.cit.afd.dao.NotificationDetailsRepository;
import ie.cit.afd.models.NotificationDetails;
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

public class NotificationDetailsTest {
	private NotificationDetailsRepository repo;
	private NotificationDetailsController tested;

	@Before
	public void setup() {
		repo = mock(NotificationDetailsRepository.class);
		tested = new NotificationDetailsController(repo);

	}

	@Test
	public void testGetAll() {
		List<NotificationDetails> all = tested.getAllNotificationDetailsItems();
		assertThat(all, notNullValue());
		verify(repo).getAll();
	}

	@Test
	public void testGetAllNotificaitonTypeItems() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		String view = tested.getAll(model);
		assertThat(view, CoreMatchers.equalTo("notificationdetails"));
		assertThat(model.get("notificationdetails"), notNullValue());
		verify(repo).getAll();
	}

	@Test
	public void testCreate() {
		tested.create(
				" NF01",
				"AbbeyBreaffy Nursing Home",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ac justo volutpat, interdum");
		Mockito.verify(repo).insert(
				Matchers.argThat(new ArgumentMatcher<NotificationDetails>() {

					@Override
					public boolean matches(Object argument) {
						return ((NotificationDetails) argument)
								.getOrganisationdetailsID().equals(
										"AbbeyBreaffy Nursing Home");
					}

					@Override
					public void describeTo(Description description) {
						description
								.appendText("expected: some AbbeyBreaffy Nursing Home");
					}
				}));

	}

}
