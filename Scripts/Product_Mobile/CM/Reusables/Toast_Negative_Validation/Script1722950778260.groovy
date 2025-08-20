import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory

'User Expectation: Toast message should not be displayed.'

if(Mobile.verifyElementNotVisible(findTestObject('Mobile/Common/Toast_Validation'), 1, FailureHandling.OPTIONAL))
{
	'Validation passed: No toast message displayed, as expected.'
	
	Mobile.takeScreenshot()
}
else
{
	'Validation failed: Unexpected toast message displayed.'
	
	AppiumDriver driver = MobileDriverFactory.getDriver()
	Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()
	KeywordUtil.logInfo(Actualtoastmessage)
	Mobile.takeScreenshot()
	Mobile.verifyMatch(Actualtoastmessage, null, false, FailureHandling.STOP_ON_FAILURE)
}
