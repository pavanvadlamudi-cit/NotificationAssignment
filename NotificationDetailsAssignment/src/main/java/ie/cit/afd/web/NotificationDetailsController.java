package ie.cit.afd.web;

import ie.cit.afd.dao.NotificationDetailsRepository;
import ie.cit.afd.models.NotificationDetails;
import ie.cit.afd.models.NotificationTypes;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
public class NotificationDetailsController {
	private NotificationDetailsRepository ntdrepo;

	@Autowired
	public NotificationDetailsController(NotificationDetailsRepository ntdrepo) {
		this.ntdrepo = ntdrepo;
	}

	@RequestMapping(value = { "/notificationdetails",
			"/notificationdetails/all" }, method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("notificationdetails", ntdrepo.getAll());
		return "notificationdetails";
	}

	@RequestMapping(value = "/notificationdetails", method = RequestMethod.POST)
	public String create(@RequestParam String notificationTypeID,
			@RequestParam String organisationdetailsID,
			@RequestParam String details) {

		NotificationDetails notificationDetails = new NotificationDetails();
		notificationDetails.setNotificationTypeID(notificationTypeID);
		notificationDetails.setOrganisationdetailsID(organisationdetailsID);
		notificationDetails.setDetails(details);
		notificationDetails.setStatus(true);
		ntdrepo.insert(notificationDetails);
		return "redirect:notificationdetails";
	}

	@RequestMapping(value = "/notificationdetails/{notificationDetailsID}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String notificationDetailsID) {
		ntdrepo.delete(notificationDetailsID);
		return "redirect:../notificationdetails";
	}

	@RequestMapping(value = "{notificationDetailsID}", method = RequestMethod.PUT)
	public String update(@RequestParam String id,
			@RequestParam String notificationTypeID,
			@RequestParam String organisationdetailsID,
			@RequestParam String details) {
		NotificationDetails notificationDetails = ntdrepo.findById(id);
		if (notificationDetails != null) {
			notificationDetails.setNotificationTypeID(notificationTypeID);
			notificationDetails.setOrganisationdetailsID(organisationdetailsID);
			notificationDetails.setDetails(details);
			notificationDetails.setStatus(true);
			ntdrepo.update(notificationDetails);
		}
		return "redirect:all";
	}
	// REST end-points
		// curl
		// http://localhost:8081/NotificationDetailsAssignment/Notification/notificationtypes
		@RequestMapping(value = { "/notificationdetails/all", "/notificationdetails" }, method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<NotificationDetails> getAllNotificationDetailsItems() {
			return ntdrepo.getAll();
		}
	private String getLocationForNotificationDetailsResource(
			NotificationDetails notificationDetails, HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(notificationDetails.getNotificationDetailsID(),
				template).toASCIIString();
	}

	// Exception handler for findById if "Todo" item does not exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}
}