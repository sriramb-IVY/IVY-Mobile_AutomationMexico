import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import org.openqa.selenium.Keys as Keys
import com.katalon.testcloud.TestCloudDriverUtility as TestCloudDriverUtility
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import org.openqa.selenium.Capabilities as Capabilities
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver

//////////////////////////////////////////////////////////--------------Local Mobile execution
//driverpath = '.\\Drivers\\Chrome Driver\\chromedriver.exe'
//
//System.setProperty('webdriver.chrome.driver', driverpath)
//
//WebDriver driver = new ChromeDriver()
//
//DriverFactory.changeWebDriver(driver)
//
//driver.manage().window().maximize()
//
//driver.get(GlobalVariable.ApplicationUrl)
//////////////////////////////////////////////////////////--------------TestopsExecution
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Get_Token_ID'), [:], FailureHandling.STOP_ON_FAILURE)

//String orgId = '795142'

String orgId = '795142'

String chromeverison = '137'

println(GlobalVariable.G_token_Global)

WebDriver webDriver = TestCloudDriverUtility.openTestCloudBrowser(GlobalVariable.G_token_Global, orgId, chromeverison)

Capabilities actualCapabilities2 = webDriver.getCapabilities()

// Logging the value for debugging purposes
KeywordUtil.logInfo('Web App Execution Session ID: ' + actualCapabilities2.getCapability('kt:requestId'))

GlobalVariable.G_executionSessionID = actualCapabilities2.getCapability('kt:requestId')

DriverFactory.changeWebDriver(webDriver)

webDriver.get(GlobalVariable.ApplicationUrl)

//////////////////////////////////////////////////////////--------------Common steps
WebUI.waitForElementVisible(findTestObject('Web Part/Re-usable/Login/Username'), 5)

WebUI.setText(findTestObject('Web Part/Re-usable/Login/Username'), GlobalVariable.DistributorUsername)

WebUI.setText(findTestObject('Web Part/Re-usable/Login/Password'), GlobalVariable.DistributorPassword)

WebUI.click(findTestObject('Web Part/Re-usable/Login/Login Button'))

