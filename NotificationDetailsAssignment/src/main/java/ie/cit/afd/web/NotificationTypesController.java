package ie.cit.afd.web;

import ie.cit.afd.dao.NotificationTypesRepository;
import ie.cit.afd.models.NotificationTypes;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

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
public class NotificationTypesController {
	private NotificationTypesRepository ntrepo;

	@Autowired
	public NotificationTypesController(NotificationTypesRepository ntrepo) {
		this.ntrepo = ntrepo;
	}

	@RequestMapping(value = { "/notificationtypes", "/notificationtypes/all" }, method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("notificationtypes", ntrepo.getAll());
		return "notificationtypes";
	}

	@RequestMapping(value = "/notificationtypes", method = RequestMethod.POST)
	public String create(@RequestParam String code, @RequestParam String name) {

		NotificationTypes notificationType = new NotificationTypes();
		notificationType.setCode(code);
		notificationType.setName(name);
		notificationType.setStatus(true);
		ntrepo.insert(notificationType);
		return "redirect:notificationtypes";
	}

	@RequestMapping(value = "/notificationtypes/{notificationTypeID}", method = RequestMethod.DELETE)
	public String deleteByNotificationTypeId(@PathVariable String id) {
		ntrepo.delete(id);
		return "redirect:notificationtypes";
	}

	@RequestMapping(value = "{notificationTypeID}", method = RequestMethod.PUT)
	public String updateByNotificationTypeId(@PathVariable String id,
			String name) {
		NotificationTypes notificationType = ntrepo.findById(id);
		notificationType.setName(name);
		ntrepo.update(notificationType);
		return "redirect:all";
	}
	@ModelAttribute("NotificationTypeList")
	public Map getAllNotificationTypes()
	{
		Map<String,String> referenceData = new HashMap();
		List<NotificationTypes> ntr =ntrepo.getAll();
		if (ntr!=null){
			for(Iterator<NotificationTypes> i = ntr.iterator(); i.hasNext();){
				NotificationTypes nt = i.next();
				referenceData.put(nt.getCode(), nt.getName());
			}
		}
		return referenceData;
	}
	// REST end-points
	// curl
	// http://localhost:8081/NotificationDetailsAssignment/Notification/notificationtypes
	@RequestMapping(value = { "/notificationtypes/all", "/notificationtypes" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<NotificationTypes> getAllNotificationTypeItems() {
		return ntrepo.getAll();
	}

	private String getLocationForNotificationTypesResource(
			NotificationTypes notificationType, HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(notificationType.getNotificationTypeID(),
				template).toASCIIString();
	}

	// Exception handler for findById if "NotificationDetails" item does not
	// exist in repo
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}

}