package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSessions;

import java.io.IOException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSessions session = app.newSession();
    assertFalse(session.login("administrator", "root"));
    assertFalse(session.isLoggedInAs("administrator"));
  }
}
