package com.characterBuilder.srvc.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.repo.UserRepo;
import com.characterBuilder.srvc.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegisteredException;

@Service
public class UserSrvcImpl implements UserSrvc {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getById(long id) {
        return userRepo.getOne(id);
    }

    @Override
    @Transactional
    public void add(User user) throws EmailAlreadyRegisteredException {
    	User dne = userRepo.getByEmail(user.getEmail());
    	if(dne != null)
    		throw new EmailAlreadyRegisteredException();

    	userRepo.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void update(User user) {
        userRepo.saveAndFlush(user);
    }

    @Override
    public Set<User> getAll() {
    	List<User> repo = userRepo.findAll();
    	HashSet<User> set = new HashSet<>(repo);
        return set;
    }

	@Override
	public User getByEmail(String email) {
		return userRepo.getByEmail(email);
	}
}
