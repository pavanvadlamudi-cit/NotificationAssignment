package ie.cit.afd.web;
import ie.cit.afd.dao.UserDetailsRepository;
import ie.cit.afd.models.UserDetails;

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
public class UserDetailsController {
	private UserDetailsRepository udrrepo;
	@Autowired
	public UserDetailsController(UserDetailsRepository udrrepo) {
		this.udrrepo = udrrepo;
	}

	@RequestMapping(value = {"/userdetails","/userdetails/all"}, method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("userdetail", udrrepo.getAll());
		return "userdetails";
	}
	

	@RequestMapping(value = "/userdetails", method = RequestMethod.POST)
	public String create(@RequestParam String username,
	@RequestParam String password,
	@RequestParam String organisationdetailsID) {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUsername(username);
		userDetails.setPassword(password);
		userDetails.setOrganisationDetailsID(organisationdetailsID);
		userDetails.setStatus(true);
		udrrepo.insert(userDetails);
		return "redirect:userdetails";
	}

	@RequestMapping(value = "{userDetailsID}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String userDetailsID) {
		udrrepo.delete(userDetailsID);
		return "redirect:userdetails";
	}

	@RequestMapping(value = "{userDetailsID}", method = RequestMethod.PUT)
	public String update(@RequestParam String userDetailsID,
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String organisationDetailsID,
			@RequestParam String details) {
		UserDetails userDetails = udrrepo.findById(userDetailsID);
		if (userDetails!=null){
			userDetails.setUsername(username);
			userDetails.setPassword(password);
			userDetails.setOrganisationDetailsID(organisationDetailsID);
			userDetails.setStatus(true);
			udrrepo.update(userDetails);
		}
		return "redirect:all";
	}
	
	private String getLocationForUserDetailsResource(UserDetails userDetails,
			HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(userDetails.getUserDetailsID(), template).toASCIIString();
	}

	// Exception handler for findById if "Todo" item does not exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}
}
