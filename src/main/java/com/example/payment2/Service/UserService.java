package com.example.payment2.Service;

import java.util.List;

import com.example.payment2.entity.User;

public interface UserService {
	
	public List<User> getAllUser();
	
	public User getIdUser(int id);
}
