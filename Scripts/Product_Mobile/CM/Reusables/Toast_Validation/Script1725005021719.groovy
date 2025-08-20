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
import io.appium.java_client.AppiumDriver as AppiumDriver
import io.appium.java_client.MobileDriver as MobileDriver
import io.appium.java_client.MobileBy as MobileBy
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.junit.Assert as Assert

import org.openqa.selenium.NoSuchElementException;

'User Expectation: Warn Toast message should be displayed.'

GlobalVariable.Toast_Text = Expecting_Toast_Text

try {
	AppiumDriver driver = MobileDriverFactory.getDriver()
	Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()
	KeywordUtil.logInfo(Actualtoastmessage)
	Mobile.takeScreenshot()
	Mobile.verifyMatch(Actualtoastmessage, GlobalVariable.Toast_Text, false, FailureHandling.CONTINUE_ON_FAILURE)
} catch (NoSuchElementException e) {
	// If the toast message is not found, skip the step
	
	Mobile.takeScreenshot()
	
	System.out.println(e)
	
	Mobile.verifyElementVisible(findTestObject('Mobile/Common/Toast_Validation'), 1, FailureHandling.CONTINUE_ON_FAILURE)
	
	Mobile.verifyElementNotVisible(findTestObject('Mobile/Common/Toast_Validation'), 1, FailureHandling.CONTINUE_ON_FAILURE)
	
	System.out.println("Toast message not displayed. Skipping further steps.");
	
}



//if(Mobile.verifyElementNotVisible(findTestObject('Mobile/Common/Toast_Validation'), 1, FailureHandling.OPTIONAL))
//	{
//		'Validation failed: Expected toast message not displayed.'
//		
//		Mobile.takeScreenshot()
//		
//		Mobile.verifyElementVisible(findTestObject('Mobile/Common/Toast_Validation'), 1, FailureHandling.CONTINUE_ON_FAILURE)
//		
//	}
//	else
//	{
//		'Validation passed: Toast message displayed successfully.'
//		
//		AppiumDriver driver = MobileDriverFactory.getDriver()
//		Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()
//		KeywordUtil.logInfo(Actualtoastmessage)
//		Mobile.takeScreenshot()
//		Mobile.verifyMatch(Actualtoastmessage, GlobalVariable.Toast_Text, false, FailureHandling.CONTINUE_ON_FAILURE)
//	}


	