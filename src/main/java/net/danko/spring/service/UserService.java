package net.danko.spring.service;

import java.util.List;
import net.danko.spring.domain.User;

public interface UserService {

    public void addUser(User user);

    public List<User> listUser();

    public void removeUser(String username);
    
    public User getUserByName(String name);
    
    public List<User> getAllUsers(int jtStartIndex, int jtPageSize);
    
    public int getUserCount();
}
