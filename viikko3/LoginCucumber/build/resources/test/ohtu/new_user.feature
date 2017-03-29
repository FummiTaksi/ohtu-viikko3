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
