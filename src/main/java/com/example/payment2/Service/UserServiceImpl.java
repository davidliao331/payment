package com.example.payment2.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.payment2.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    ApplicationContext appContext;
	
	private List<User> userList = new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();
	private User[] users;
	public void init() {
		userList = new ArrayList<>();
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		
		try {
			File userFile = appContext.getResource("classpath:data/user.json").getFile();
			users = mapper.readValue( userFile, User[].class);
			userList = Arrays.asList(users);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<User> getAllUser() {
		init();
		return userList;
	}

	@Override
	public User getIdUser(int id) {
		init();
		User theUser = userList.stream()
							   .filter(u-> u.getId() == id)
							   .findAny()
							   .orElse(null);
		return theUser;
	}


}
