package ohtu.data_access;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUserDAO implements UserDao {
  private Scanner reader;
  private List<User> users;

  public FileUserDAO(String filename) {
    users = new ArrayList();
    File file = new File(filename);
    add(new User("jaakko","salainen1"));
    try {
      reader = new Scanner(file);
      readFile();
      reader.close();
    }
    catch(Exception e){
      System.out.println("file not found, fail:  " + e.getMessage());
    }

  }


  public void readFile() {
    while (reader.hasNextLine()) {
      String line = reader.nextLine();
      String[] parts = line.split(" ");
      User user = new User(parts[0],parts[1]);
      add(user);
    }
  }

  @Override
  public List<User> listAll() {
      return users;
  }

  @Override
  public User findByName(String name) {
      for (User user : users) {
          if (user.getUsername().equals(name)) {
              return user;
          }
      }

      return null;
  }

  @Override
  public void add(User user) {
      users.add(user);
  }


}
