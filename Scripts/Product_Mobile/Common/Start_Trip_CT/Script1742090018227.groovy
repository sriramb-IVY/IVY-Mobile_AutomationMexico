import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import java.util.ArrayList as ArrayList
import java.util.Collections as Collections
import java.util.Comparator as Comparator
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//new start trip


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue(
            'Menu', 18), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementExist(findTestObject('Mobile/Trip/pushBtn_Start_Trip'), 1, FailureHandling.OPTIONAL)) {
    'Trip started Now'
    AppiumDriver driver = MobileDriverFactory.getDriver()

    Mobile.tap(findTestObject('Mobile/Trip/pushBtn_Start_Trip'), 0)

    Actualtoastmessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getText()

    Mobile.takeScreenshot()

    Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 7), false, FailureHandling.OPTIONAL)
} else {
    'Trip is alread started'
    Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Trip/pushBtn_Start_Trip_Enabled'), 0)
}

