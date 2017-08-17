package com.characterBuilder.services.interfaces;

import java.util.Set;

import com.characterBuilder.entities.User;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegistered;

public interface UserSrvc {
    public User getById(long id);
    public void add(User user) throws EmailAlreadyRegistered;
    public void delete(User user);
    public void update(User user);
    public Set<User> getAll();
	public User getByEmail(String string);
}
