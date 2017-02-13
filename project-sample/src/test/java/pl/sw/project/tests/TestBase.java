package pl.sw.project.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pl.sw.project.appmanager.ApplicationManager;

/**
 * Created by nishi on 2017-02-13.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeClass
  public static void setUpClass() {
    app.init();
  }

  @AfterClass
  public static void tearDownClass() {
    app.stop();
  }
}
