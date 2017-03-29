package ohtu;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;

public class App {

    private IO io;
    private AuthenticationService auth;

    public App(IO io, AuthenticationService auth) {
        this.io = io;
        this.auth = auth;
    }

    public String[] ask() {
        String[] userPwd = new String[2];
        userPwd[0] = io.readLine("username:");
        userPwd[1] = io.readLine("password:");
        return userPwd;
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

    public void run() {

        while (true) {
            String command = io.readLine("komento (new tai login):");

            if (command.isEmpty()) {
                break;
            }

            if (command.equals("new")) {
                String[] usernameAndPassword = ask();
                String username = usernameAndPassword[0];
                String password = usernameAndPassword[1];
                if (passwordIsLegit(password) && usernameIsLegit(username)) {
                  if (auth.createUser(username, password)) {
                      io.print("new user registered");
                  }
                }
                else {
                    io.print("new user not registered");
                }

            } else if (command.equals("login")) {
                String[] usernameAndPassword = ask();
                if (auth.logIn(usernameAndPassword[0], usernameAndPassword[1])) {
                    io.print("logged in");
                } else {
                    io.print("wrong username or password");
                }
            }

        }
    }

    public static void main(String[] args) {
        UserDao dao = new InMemoryUserDao();
        IO io = new ConsoleIO();
        AuthenticationService auth = new AuthenticationService(dao);
        new App(io, auth).run();
    }





    // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
    // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
    //
    // UserDao dao = new InMemoryUserDao();
    // StubIO io = new StubIO("new", "eero", "sala1nen" );
    //  AuthenticationService auth = new AuthenticationService(dao);
    // new App(io, auth).run();
    // System.out.println(io.getPrints());
}
