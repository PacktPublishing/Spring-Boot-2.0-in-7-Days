/**
 * 
 */
package com.ebookstore.app;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Petra Simonis
 *
 */
@Controller
@RequestMapping(path = "/person")
public class PersonController {

	@Autowired
	private PersonRepository personrepository;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String createNewAccount(Model model) {
		model.addAttribute("person", new Person());
		return "account";
	}

	@RequestMapping(path = "/new", method = RequestMethod.POST)
	public String createNewAccount(@Valid @ModelAttribute Person person, BindingResult result) {
		if (result.hasErrors())
			return "account";

		Long nextid = personrepository.getMaxId();
		long id = nextid + 1;
		System.out.println("NEXT ID" + id);
		person.setId(id);

		Person newperson = personrepository.save(person);

		if (newperson.getUserRole().equals("AUTHOR"))
			return "author";
		else
			return "customer";
	}

}
