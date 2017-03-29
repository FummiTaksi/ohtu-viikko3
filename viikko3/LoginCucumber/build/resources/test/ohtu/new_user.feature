Feature: A new user account can be created if a proper unused username and password are given

    Scenario: user can register with proper unuserd username and password
       Given command new is selected
       When   username "uusi" and password "salasana1!" are entered
       Then   system will respond with "new user registered"

    Scenario: creation fails with correct username and too short password
       Given command new is selected
       When  username "olomuoto" and password "o" are entered
       Then  system will respond with "new user not registered"


    Scenario: creation fails with correct username and password consisting of letters
        Given command new is selected
        When  username "mluukkai" and password "fullstack" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
       Given command new is selected
       When  username "ok" and password "fullstack!" are entered
       Then  system will respond with "new user not registered"



      Scenario: creation fails with already taken username and valid pasword
         Given command new is selected
         Given  user "pekka" with password "salainen1" is created
         And   command new is selected
         And   username "pekka" and password "salainen1" are entered
         Then  system will respond with "new user not registered"

       Scenario: can login with succesfully generated account
          Given user "eero" with password "salainen1" is created
          And   command login is selected
          When  username "eero" and password "salainen1" are entered
          Then  system will respond with "logged in"

       Scenario: can not login with account that is not succesfully created
          Given user "aa" with password "aa" is created
          And   command login is selected
          When  username "aa" and password "aa" are entered
          Then  system will respond with "wrong username or password"
