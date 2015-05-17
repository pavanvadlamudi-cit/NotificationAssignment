package ie.cit.afd.web;

import ie.cit.afd.dao.NotificationDetailsRepository;
import ie.cit.afd.dao.NotificationTypesRepository;
import ie.cit.afd.dao.OrganisationDetailsRepository;
import ie.cit.afd.models.NotificationDetails;

import ie.cit.afd.models.NotificationTypes;
import ie.cit.afd.models.OrganisationDetails;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
public class NotificationDetailsController {
	protected static Logger logger = Logger.getLogger("controller");

	private NotificationDetailsRepository ntdrepo;
	@Autowired
	private NotificationTypesRepository ntrepo;
	@Autowired
	private OrganisationDetailsRepository odrrepo;

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

	/*
	 * @RequestMapping(value = "/notificationdetails/{notificationDetailsID}",
	 * method = RequestMethod.POST) public String findById(@PathVariable String
	 * notificationDetailsID,Model model) { NotificationDetails
	 * notificationDetails =ntdrepo.findById(notificationDetailsID);
	 * System.out.println("(notificationDetails != null) "+(notificationDetails
	 * != null) );
	 * 
	 * if (notificationDetails != null) {
	 * model.addAttribute("editnotificationdetails", notificationDetails);
	 * 
	 * } return "redirect:../editnotificationdetails"; }
	 */

	@RequestMapping(value = "/notificationdetails/edit/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") String id, Model model) {

		logger.debug("Received request to show edit page");

		// Retrieve person with matching id then add this person to the model
		// The editpage.jsp references a model attribute named "personAttribute"
		// So we add a "personAttribute" to the model.
		// This "personAttribute" will be referenced again once we send the
		// update form data
		// We could have chosen a different name like "person" for the model
		// If you do, make sure you update the JSP that references this name
		// And update the POST method below that receives the request to do the
		// actual update!
		model.addAttribute("notificationdetails", ntdrepo.findById(id));

		// The editpage.jsp references a model attribute named "currencies"
		// This model attribute is passed automatically when used
		// @ModelAttribute("currencies") earlier

		// This will resolve to /WEB-INF/jsp/editpage.jsp
		return "editnotificationdetails";
	}

	@RequestMapping(value = "/notificationdetails/save/{id}", method = RequestMethod.POST)
	public String saveEdit(

	@PathVariable("id") String id, @RequestParam String details, Model model) {
		logger.debug("**********************save request to show edit page");
		NotificationDetails notificationDetails = ntdrepo.findById(id);
		if (notificationDetails != null) {
			// notificationDetails.setNotificationTypeID(notificationTypeID);
			// notificationDetails.setOrganisationdetailsID(organisationdetailsID);
			notificationDetails.setDetails(details);
			notificationDetails.setStatus(true);
			ntdrepo.update(notificationDetails);
		}
		return "redirect:/Notification/notificationdetails";
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

	@ModelAttribute("NotificationTypeList")
	public Map<String, String> getAllNotificationTypes() {
		Map<String, String> referenceData = new HashMap();
		List<NotificationTypes> ntr = ntrepo.getAll();
		if (ntr != null) {
			for (Iterator<NotificationTypes> i = ntr.iterator(); i.hasNext();) {
				NotificationTypes nt = i.next();
				referenceData.put(nt.getNotificationTypeID(), nt.getCode()
						+ " - " + nt.getName());
			}
		}
		return referenceData;
	}

	@ModelAttribute("OrganisationdetailsList")
	public Map<String, String> getAllOrganisationdetails() {
		Map<String, String> referenceData = new HashMap();
		List<OrganisationDetails> ntr = odrrepo.getAll();
		if (ntr != null) {
			for (Iterator<OrganisationDetails> i = ntr.iterator(); i.hasNext();) {
				OrganisationDetails od = i.next();
				referenceData.put(od.getOrganisationDetailsID(), od.getName());
			}
		}
		return referenceData;
	}

	// REST end-points
	// curl
	// http://localhost:8081/NotificationDetailsAssignment/Notification/notificationtypes
	@RequestMapping(value = { "/notificationdetails/all",
			"/notificationdetails" }, method = RequestMethod.GET, produces = "application/json")
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