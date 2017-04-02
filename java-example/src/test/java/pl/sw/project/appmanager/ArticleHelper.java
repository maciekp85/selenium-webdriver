package pl.sw.project.appmanager;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by nishi on 2017-03-07.
 */
public class ArticleHelper extends HelperBase {

  public ArticleHelper(WebDriver wd) {
    super(wd);
  }

  public void checkNumberOfStickers() {
    List<WebElement> articleSets = wd.findElements( By.cssSelector("ul.listing-wrapper.products"));
    for (int i = 0; i < articleSets.size(); i++) {
      List<WebElement> articlesListOnSet = articleSets.get( i ).findElements( By.cssSelector( "li" ) );
      for (int j = 0; j < articlesListOnSet.size(); j++) {
        WebElement article = articlesListOnSet.get( j );
        int stickersNumberInArticle = article.findElements( By.cssSelector( "div.image-wrapper > div.sticker" ) ).size();
        assertTrue(stickersNumberInArticle == 1 );
      }
    }
  }

  public void checkArticleFromCampaignsSection(String articleName, String regularPrice, String campaignPrice, String expectedStyleForFirstPrice, String expectedStyleForSecondPrice ) {
    WebElement article = wd.findElement( By.cssSelector( "div#box-campaigns ul.listing-wrapper.products li" ) );
    WebElement firstPriceOnMainPage = article.findElement( By.cssSelector( "s.regular-price" ) );
    WebElement secondPriceOnMainPage = article.findElement( By.cssSelector( "strong.campaign-price" ) );
    String actualStyleForFirstPriceOnMainPage = firstPriceOnMainPage.getCssValue( "color" ) + " " + firstPriceOnMainPage.getCssValue( "text-decoration" );
    String actualStyleForSecondPriceOnMainPage = secondPriceOnMainPage.getCssValue( "color" ) + " " + secondPriceOnMainPage.getCssValue( "font-weight" );
    assertEquals( expectedStyleForFirstPrice, actualStyleForFirstPriceOnMainPage );
    assertEquals( expectedStyleForSecondPrice, actualStyleForSecondPriceOnMainPage );
    article.click();
    new WebDriverWait( wd, 1000).until( ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "h1.title" ))).get( 0 ).getText().equals( "Yellow Duck" );
    assertEquals( articleName, wd.findElement( By.cssSelector( "h1.title" ) ).getText() );
    WebElement firstPriceOnArticlePage = wd.findElement( By.cssSelector( "s.regular-price" ) );
    WebElement secondPriceOnArticlePage = wd.findElement( By.cssSelector( "strong.campaign-price" ) );
    String actualStyleForFirstPriceOnArticlePage = firstPriceOnArticlePage.getCssValue( "color" ) + " " + firstPriceOnArticlePage.getCssValue( "text-decoration" );
    String actualStyleForSecondPriceOnArticlePage = secondPriceOnArticlePage.getCssValue( "color" ) + " " + secondPriceOnArticlePage.getCssValue( "font-weight" );
    assertNotEquals( expectedStyleForFirstPrice, actualStyleForFirstPriceOnArticlePage );
    assertEquals( expectedStyleForSecondPrice, actualStyleForSecondPriceOnArticlePage );

    assertEquals( regularPrice, firstPriceOnArticlePage.getText() );
    assertEquals( campaignPrice, secondPriceOnArticlePage.getText() );
  }
}
