package net.danko.spring.dao;

import java.util.List;
import net.danko.spring.domain.User;

public interface UserDAO {

    public void addUser(User user);
    
    public void updateUser(User user);

    public List<User> listUser();

    public void removeUser(String username);
    
    public User getUserByName(String name);
    
    public List<User> getAllUsers(int jtStartIndex, int jtPageSize);
    
    public int getUserCount();
}
