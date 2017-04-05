package pl.sw.project.appmanager;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

  public void addNewProduct(String name) {
    wd.findElement( By.cssSelector( "a[href$='_id=1']" ) ).click();
    int sizeBefore = wd.findElements( By.cssSelector( "tr.row" ) ).size() - 3;

    wd.findElements( By.cssSelector( "a.button" ) ).get( 1 ).click();
    List<WebElement> overlaps = wd.findElements( By.cssSelector( "ul.index li" ) );

    // GENERAL
    type(  By.name( "name[en]" ), name );
    type( By.name( "code" ), "testcode" );
    List<WebElement> tables = wd.findElements( By.cssSelector( "div#tab-general tbody" ) );
    List<WebElement> productGroups = tables.get( 2 ).findElements( By.cssSelector( "tr" ) );
    productGroups.get( 2 ).findElement( By.cssSelector( "td input" )).click();
    type( By.name( "quantity" ), "20" );
    new Select( wd.findElement( By.name( "sold_out_status_id" ) ) ).selectByValue( "2" );
    wd.findElement( By.name( "new_images[]" ) ).sendKeys( "D:\\myProject\\tomcat\\webapps\\selenium-webdriver\\java-example\\src\\test\\resources\\picture.png" );
    WebElement date_valid_from = wd.findElement( By.name( "date_valid_from" ));
    Actions dateFromActions = new Actions( wd );
    dateFromActions
            .moveToElement( date_valid_from)
            .click()
            .sendKeys( "2017" )
            .sendKeys( Keys.TAB )
            .sendKeys( "01" )
            .sendKeys( "01" )
            .release().perform();
    WebElement date_valid_to = wd.findElement( By.name( "date_valid_to" ));
    Actions dateToActions = new Actions( wd );
    dateToActions
            .moveToElement( date_valid_to )
            .click()
            .sendKeys( "2017" )
            .sendKeys( Keys.TAB )
            .sendKeys( "12" )
            .sendKeys( "31" )
            .sendKeys( Keys.TAB )
            .release().perform();

    // INFORMATION
    overlaps.get( 1 ).click();
    new Select( wd.findElement( By.name( "manufacturer_id" ) ) ).selectByValue( "1" );
    type( By.name( "keywords" ), "testkeywords" );
    type( By.name( "short_description[en]" ), "test short description" );
    wd.findElement( By.cssSelector( "div.trumbowyg-editor" ) ).sendKeys( "Test long long long long long description " );
    type( By.name( "head_title[en]" ), "test head title" );
    type( By.name( "meta_description[en]" ), "test meta description" );

    // PRICES
    overlaps.get( 3 ).click();
    type( By.name( "purchase_price" ), "20" );
    new Select( wd.findElement( By.name( "purchase_price_currency_code" ) ) ).selectByValue( "USD" );
    type( By.name( "prices[USD]" ), "20" );
    type( By.name( "prices[EUR]" ), "30" );
    wd.findElement( By.cssSelector( "a#add-campaign" ) ).click();

    WebElement startDate = wd.findElement( By.name( "campaigns[new_1][start_date]" ) );
    Actions startDateActions = new Actions(wd);
    startDateActions
            .moveToElement( startDate, 9, 12 )
            .click()
            .sendKeys( "2017" )
            .sendKeys( Keys.TAB )
            .sendKeys( "01" )
            .sendKeys( "01" )
            .sendKeys( "12" )
            .sendKeys( "20" )
            .release().perform();
    WebElement endDate = wd.findElement( By.name( "campaigns[new_1][end_date]" ) );
    Actions endDateActions = new Actions(wd);
    endDateActions
            .moveToElement( endDate, 9, 12)
            .click()
            .sendKeys( "2017" )
            .sendKeys( Keys.TAB )
            .sendKeys( "31" )
            .sendKeys( "12" )
            .sendKeys( "16" )
            .sendKeys( "30" )
            .sendKeys( Keys.TAB )
            .release().perform();
    type( By.name( "campaigns[new_1][percentage]" ), "40" );
    assertEquals( "12.00", wd.findElement( By.name( "campaigns[new_1][USD]" ) ).getAttribute( "placeholder" ) );
    String value = JavascriptExecutor.class.cast( wd ).executeScript( "return document.getElementsByName('campaigns[new_1][EUR]')[0].value;" ).toString();
    assertEquals( "18.00", value);

    wd.findElement( By.name( "save" ) ).click();
    int sizeAfter = wd.findElements( By.cssSelector( "tr.row" ) ).size() - 3;
    assertEquals( sizeBefore, sizeAfter -1 );
  }

}
