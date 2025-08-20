import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

driverpath = '.\\Drivers\\Chrome Driver\\chromedriver.exe'

System.setProperty('webdriver.chrome.driver', driverpath)

WebDriver driver = new ChromeDriver()

DriverFactory.changeWebDriver(driver)

driver.manage().window().maximize()

driver.get(GlobalVariable.ApplicationUrl)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Login/Username'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Login/Username'), GlobalVariable.DivisionUsername, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Login/Password'), GlobalVariable.DivisionPassword, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Login/Login Button'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

