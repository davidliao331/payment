package com.example.payment2.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.payment2.Service.TransactionService;
import com.example.payment2.Service.UserService;
import com.example.payment2.entity.Transaction;
import com.example.payment2.entity.User;

@Controller
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/")
	public String showHome(Model theModel) {
		
		theModel.addAttribute("getAllUsers", "user");
		return "index";
	}
	
	@GetMapping("/user")
	public String getAllUser(Model theModel) {
		
		List<User> userList= userService.getAllUser();
		theModel.addAttribute("users", userList);
		
		return "list-users";
	}
	
	@GetMapping(value = "/user", params = "userId")
	public String getUser(Model theModel, @RequestParam(value = "userId", required = true) int userId) {
		User theUser = userService.getIdUser(userId);
		theModel.addAttribute("users", theUser);
		return "list-users";
	}
	
	
	
	@GetMapping("/transaction")
	public String getAllTransaction(Model theModel) {
		
		List<Transaction> transactionList= transactionService.getAllTransaction();
		theModel.addAttribute("transactions", transactionList);
		
		return "list-transactions";
	}
	
	@GetMapping(value = "/transaction", params = "userId")
	public String getTransaction(Model theModel, 
							@RequestParam(value = "userId", required = true) int userId) {

		List<Transaction> transactionList = transactionService.getIdTransaction(userId);
		theModel.addAttribute("transactions", transactionList);
		
		return "list-transactions";
	}
	
	@RequestMapping(value = "/transaction", params = {"userId", "date"})
	public String getUserDateTransaction(Model theModel, 
									 @RequestParam(value = "userId", required = true) int userId,
									 @RequestParam(value = "date") String strDate) {
		
		LocalDate date = LocalDate.parse(strDate);
		LocalDateTime dateTime = date.atStartOfDay();
		
		List<Transaction> transactionList= transactionService.getUserDateTransaction(userId, dateTime, 1);
		theModel.addAttribute("transactions", transactionList);
		
		return "list-transactions";
	}
	
	@RequestMapping(value = "/transaction", params = "date")
	public String getDateTransaction(Model theModel, 
									 @RequestParam(value = "date") String strDate) {

		LocalDate date = LocalDate.parse(strDate);
		LocalDateTime dateTime = date.atStartOfDay();

		List<Transaction> transactionList= transactionService.getDateTransaction(dateTime, 1);
		theModel.addAttribute("transactions", transactionList);
		
		return "list-transactions";
	}
	
}
