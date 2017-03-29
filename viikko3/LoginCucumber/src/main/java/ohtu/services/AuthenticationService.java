package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }



    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        return !usernameIsLegit(username) || !passwordIsLegit(password);
    }

    public boolean passwordIsLegit(String password) {
      if (password.length() >= 8) {
        for (int i = 0; i < password.length(); i++) {
          char letter = password.charAt(i);
          if (!Character.isLetter(letter)) {
            return true;
          }
        }
      }
      return false;
    }

    public boolean usernameIsLegit(String username) {
      if (username.length() >= 3) {
        for (int i = 0; i < username.length(); i++) {
          char letter = username.charAt(i);
          if (!Character.isLetter(letter)) {
            return false;
          }
        }
        return true;
      }
      return false;
    }
}
