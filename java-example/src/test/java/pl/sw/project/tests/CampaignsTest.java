package pl.sw.project.tests;

import org.junit.Test;

/**
 * Created by nishi on 2017-03-07.
 */
public class CampaignsTest extends TestBase {

  @Test
  public void testCampaigns() {
    app.goTo().mainPage();
    String articleName = "Yellow Duck";
    String regularPrice = "$20";
    String campaignPrice = "$18";
    String expectedStyleForFirstPrice = "rgba(119, 119, 119, 1) line-through";
    String expectedStyleForSecondPrice = "rgba(204, 0, 0, 1) bold";
    app.article().checkArticleFromCampaignsSection(articleName, regularPrice, campaignPrice, expectedStyleForFirstPrice, expectedStyleForSecondPrice);
  }
}
