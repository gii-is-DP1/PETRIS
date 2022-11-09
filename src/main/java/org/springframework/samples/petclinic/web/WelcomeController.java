package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	

		List<Person> persons = new ArrayList<Person>();
		Person person1 =new Person();
		Person person2 =new Person();
		Person person3 =new Person();
		Person person4 =new Person();
		Person person5 =new Person();
		Person person6 =new Person();

		person1.setFirstName("Gonzalo ");
		person2.setFirstName("Fernando ");
		person3.setFirstName("Lucas ");
		person4.setFirstName("Raúl-Hernán ");
		person5.setFirstName("Daniel ");
		person6.setFirstName("Jaime ");

		person1.setLastName("Ribas");
		person2.setLastName("Baquero");
		person3.setLastName("Antoñanzas");
		person4.setLastName("Mérida");
		person5.setLastName("Cortes");
		person6.setLastName("García");

		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		persons.add(person6);

		model.put("persons", persons);
		model.put("title", "My proyect");
		model.put("group", "L1-5");

	    return "welcome";
	  }

	  
}
