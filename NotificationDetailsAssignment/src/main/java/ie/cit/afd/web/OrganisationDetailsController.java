package ie.cit.afd.web;

import ie.cit.afd.dao.OrganisationDetailsRepository;
import ie.cit.afd.models.OrganisationDetails;
import ie.cit.afd.models.Users;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
public class OrganisationDetailsController {
	private OrganisationDetailsRepository ordrepo;
	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	public OrganisationDetailsController(OrganisationDetailsRepository ordrepo) {
		this.ordrepo = ordrepo;
	}

	@RequestMapping(value = { "/organisationdetails",
			"/organisationdetails/all" }, method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("organisationdetails", ordrepo.getAll());
		return "organisationdetails";
	}

	@RequestMapping(value = "/organisationdetails", method = RequestMethod.POST)
	public String create(@RequestParam String name) {

		OrganisationDetails organisationDetails = new OrganisationDetails();
		organisationDetails.setName(name);
		organisationDetails.setStatus(true);
		ordrepo.insert(organisationDetails);
		return "redirect:organisationdetails";
	}

	@RequestMapping(value = "/organisationdetails/{organisationDetailsID}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String organisationDetailsID) {
		ordrepo.delete(organisationDetailsID);
		return "redirect:../organisationdetails";
	}

	@RequestMapping(value = "{organisationDetailsID}", method = RequestMethod.PUT)
	public String update(@RequestParam String organisationDetailsID,
			@RequestParam String name) {
		OrganisationDetails organisationDetails = ordrepo
				.findById(organisationDetailsID);
		if (organisationDetails != null) {
			organisationDetails.setName(name);
			organisationDetails.setStatus(true);
			ordrepo.update(organisationDetails);
		}
		return "redirect:all";
	}

	private String getLocationForOrganisationDetailsResource(
			OrganisationDetails organisationDetails, HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(organisationDetails.getOrganisationDetailsID(),
				template).toASCIIString();
	}
	
	@RequestMapping(value = "/organisationdetails/edit/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") String id, Model model) {

		
		model.addAttribute("organisationdetails", ordrepo.findById(id));

		return "editorganisationdetails";
	}

	@RequestMapping(value = "/organisationdetails/save/{id}", method = RequestMethod.POST)
	public String saveEdit(

	@PathVariable("id") String id, @RequestParam String name, Model model) {
		logger.debug("**********************save request to show edit page");
		OrganisationDetails org = ordrepo.findById(id);
		if (org != null) {

			org.setName(name);
			ordrepo.update(org);
		}
		return "redirect:/Notification/organisationdetails";
	}
	// Exception handler for findById if "Todo" item does not exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}
}
