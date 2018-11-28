package com.simonis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
 
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name",
			required=false, 
			defaultValue="World") String name, Model model)
	{
		model.addAttribute("name", name);
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}
	
	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting)
	{
		return "result";
	}

}
