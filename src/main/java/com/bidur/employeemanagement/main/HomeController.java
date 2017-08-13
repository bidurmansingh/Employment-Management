package com.bidur.employeemanagement.main;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bidur.employeemanagement.domain.Employee;
import com.bidur.employeemanagement.service.IEmployeeService;

@Controller
public class HomeController {

	private IEmployeeService employeeService;

	@Autowired(required = true)
	@Qualifier(value = "employeeService")
	public void setPersonService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "authFailed", required = false) String authFailed,
			@RequestParam(value = "unauthorized", required = false) boolean unauthorized,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (authFailed != null) {
			model.addObject("authFailed", "Invalid employeename and password!");
		}

		if (unauthorized) {
			model.addObject("authFailed",
					"Sorry, you are not allowed to view this page. You are automatically logged out.");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}

	@RequestMapping(value = "/auth/admin", method = RequestMethod.GET)
	public String afterAdminAuth(Model model) {
		model.addAttribute("employee", employeeService.findAll());
		return "home";
	}

	@RequestMapping(value = "/auth/admin/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "add-employee";
	}

	@RequestMapping(value = "/auth/admin/add", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute Employee employee, Model model) {
		employeeService.addEmployee(employee);
		return "redirect:/auth/admin";
	}

	@RequestMapping(value = "/auth/admin/edit", params = { "id" }, method = RequestMethod.GET)
	public String editEmployee(@RequestParam("id") long id, Model model) {

		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "add-employee";
	}

	@RequestMapping(value = "/auth/admin/edit", method = RequestMethod.POST)
	public String saveEditedEmployee(@RequestParam(value = "_submit", required = false) String submit,
			@RequestParam(value = "_cancel", required = false) String cancel, @ModelAttribute Employee employee) {
		if (cancel != null) {
			return "redirect:/auth/admin";
		} else {
			employeeService.updateEmployee(employee);
		}
		return "redirect:/auth/admin";
	}

	@RequestMapping(value = "/auth/admin/remove", params = { "id" }, method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam("id") long id, Model model) {
		employeeService.removeEmployee(id);
		return "redirect:/auth/admin";
	}
}
