package ie.cit.adf.web;
import ie.cit.afd.dao.NotificationTypesRepository;
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
public class NotificationTypesController {
	private NotificationTypesRepository ntrepo;
	@Autowired
	public NotificationTypesController(NotificationTypesRepository ntrepo) {
		this.ntrepo = ntrepo;
	}

	@RequestMapping(value = {"/notificationtypes","/notificationtypes/all"}, method = RequestMethod.GET)
	public String getAllNotificationTypes(Model model) {
		model.addAttribute("notificationtypes", ntrepo.getAll());
		return "notificationtypes";
	}
	

	@RequestMapping(value = "/notificationtypes", method = RequestMethod.POST)
	public String create(@RequestParam String code,@RequestParam String name) {
		
		NotificationTypes notificationType = new NotificationTypes();
		notificationType.setCode(code);
		notificationType.setName(name);
		notificationType.setStatus(true);
		ntrepo.insert(notificationType);
		//return "redirect:/notificationtypes/all";
		//return "redirect:notificationtypes/all"; == http://localhost:8081/NotificationDetailsAssignment/Notification/Notification/notificationtypes
		//return "redirect:/notificationtypes/all"; http://localhost:8081/NotificationDetailsAssignment/Notification/Notification/notificationtypes
		return "redirect:notificationtypes";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		ntrepo.delete(id);
		return "redirect:notificationtypes";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable String id,String name) {
		NotificationTypes notificationType = ntrepo.findById(id);
		notificationType.setName(name);
		ntrepo.update(notificationType);
		return "redirect:all";
	}
	
	private String getLocationForTodoResource(NotificationTypes notificationType,
			HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(notificationType.getNotificationTypeID(), template).toASCIIString();
	}

	// Exception handler for findById if "Todo" item does not exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}
	
	
}