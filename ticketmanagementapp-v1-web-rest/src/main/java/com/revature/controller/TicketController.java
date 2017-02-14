package com.revature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.model.TicketList;
import com.revature.model.User;
import com.revature.service.EmployeeLoginService;
import com.revature.service.EmployeeTicketService;
import com.revature.service.RegisterService;
import com.revature.service.UserLoginService;
import com.revature.service.UserTicketService;

@RestController
@RequestMapping("/home")

public class TicketController {
	UserLoginService userLoginService = new UserLoginService();
	RegisterService userRegisterService = new RegisterService();
//	UserService userService = new UserService();
	UserTicketService userService=new UserTicketService();
	EmployeeLoginService employeeLoginService = new EmployeeLoginService();
//	EmployeeService employeeService = new EmployeeService();
	EmployeeTicketService employeeService = new EmployeeTicketService();
	User user = new User();
	Employee employee = new Employee();
	
	@PostMapping("/register")
	public String register(@RequestBody User user) throws ServiceException {
		return userRegisterService.registerUser(user.getName(),user.getEmailId(),user.getPassword());
	}

	@PostMapping("/create")
	public String create(@RequestBody Ticket ticket) throws ServiceException {
		return userService.create(ticket.getUserId().getId(),ticket.getSubject(),ticket.getDescription(),ticket.getDepartmentId().getId(),ticket.getPriorityId().getId());
	}

	@PutMapping("/update")
	public String update(@RequestBody Ticket ticket) throws ServiceException {
			return userService.update(ticket.getId(), ticket.getUserId().getId(), ticket.getDescription());
	}

	@PutMapping("/close")
	public String close(@RequestBody Ticket ticket) throws ServiceException {
		return userService.close(ticket.getId(), ticket.getUserId().getId());
	}

	@GetMapping("/userViewTickets/{id}")
	public List<TicketList> userView(@PathVariable("id") int id) throws ServiceException {
		return userService.view(id);
	}

	@PutMapping("/assign")
	public String assign(@RequestParam("employeeId") int employeeId, @RequestParam("ticketId") int ticketId) throws ServiceException {
		return employeeService.assign(employeeId, ticketId);
	}

	@PutMapping("/reAssign")
	public String reAssign(@RequestParam("callingEmployeeId") int callingEmployeeId, @RequestParam("ticketId") int ticketId, @RequestParam("employeeId") int employeeId) throws ServiceException {
		return employeeService.reAssign(callingEmployeeId, ticketId, employeeId);
	}

	@PutMapping("/reply")
	public String reply(@RequestParam("callingEmployeeId") int callingEmployeeId, @RequestParam("ticketId") int ticketId, @RequestParam("solution") String solution) throws ServiceException {
		return employeeService.reply(callingEmployeeId, ticketId, solution);
	}

	@PutMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, @RequestParam("ticketId") int ticketId) throws ServiceException {
		return employeeService.delete(id, ticketId);
	}

	/*@GetMapping("/employeeViewTickets/{id}")
	public List<Ticket> employeeView(@PathVariable("id") int id) throws ServiceException {
			return employeeService.view(id);
	}
	*/

	@GetMapping("/employeeViewTickets/{emailId}")
	public List<Ticket> employeeView(@PathVariable("emailId") String emailId) throws ServiceException {
			return employeeService.view(emailId);
	}

}