package ie.cit.afd.web;

import ie.cit.afd.dao.UsersRepository;
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
public class UsersController {
	private UsersRepository usrrepo;
	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	public UsersController(UsersRepository usrrepo) {
		this.usrrepo = usrrepo;
	}

	@RequestMapping(value = { "/users", "/users/all" }, method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("users", usrrepo.getAll());
		return "users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String create(@RequestParam String username1,
			@RequestParam String password1) {

		Users users = new Users();
		users.setUsername(username1);
		users.setPassword(password1);
		users.setEnabled(true);

		usrrepo.insert(users);
		return "redirect:users";
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String username) {
		usrrepo.delete(username);
		return "redirect:../users";
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
	public String update(@RequestParam String userDetailsID,
			@RequestParam String username, @RequestParam String password,
			@RequestParam String organisationDetailsID,
			@RequestParam String details) {
		Users users = usrrepo.findByUsername(username);
		if (users != null) {
			users.setUsername(username);
			users.setPassword(password);
			users.setEnabled(true);
			usrrepo.update(users);
		}
		return "redirect:all";
	}

	@RequestMapping(value = "/users/edit/{username}", method = RequestMethod.GET)
	public String findById(@PathVariable("username") String username, Model model) {

		logger.debug("Received request to show edit page");

		model.addAttribute("users", usrrepo.findByUsername(username));

		return "editusers";
	}

	@RequestMapping(value = "/users/save/{id}", method = RequestMethod.POST)
	public String saveEdit(

	@PathVariable("id") String username, @RequestParam String password, Model model) {
		logger.debug("**********************save request to show edit page");
		Users users = usrrepo.findByUsername(username);
		if (users != null) {

			users.setPassword(password);
			usrrepo.update(users);
		}
		return "redirect:/Notification/users";
	}
	
	private String getLocationForUsersResource(Users users,
			HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(users.getUsername(), template).toASCIIString();
	}

	// Exception handler for findById if "Todo" item does not exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}
}
