package ie.cit;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.cit.afd.dao.*;
import ie.cit.afd.models.*;

public class NotificationDetailsAssignment {

	public static void main(String[] args) {
		System.out.println("Testing with Main static method");
		System.out.println("Inserting rows into Notification Types");
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		NotificationTypesRepository ntr= ctx.getBean(NotificationTypesRepository.class);
		
		
		NotificationTypes ntr1 = new NotificationTypes();
		NotificationTypes ntr2 = new NotificationTypes();
		NotificationTypes ntr3 = new NotificationTypes();
		
		//Insert Test 1
		ntr1.setCode("NF01");
		ntr1.setName("The unexpected death of any resident");
		ntr1.setStatus(true);
		ntr.insert(ntr1);
		
		//Update Test 2
		ntr2.setCode("NF002");  //Insert a wrong code
		ntr2.setName("Outbreak of any notifiable disease");
		ntr2.setStatus(true);
		ntr.insert(ntr2);
	
	
		ntr3=ntr.findBycode("NF02");
		if (ntr3!=null){
			ntr3.setCode("NF02");
			ntr.update(ntr3);
		}
		
		
		List<NotificationTypes> all= ntr.getAll();
		System.out.println(all);
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
	}

}
