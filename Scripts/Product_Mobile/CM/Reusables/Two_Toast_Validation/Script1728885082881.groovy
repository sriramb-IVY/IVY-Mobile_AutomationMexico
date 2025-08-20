
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

ArrayList message = new ArrayList()

for(int i = 0 ; i<=10 ; i++)
{

try {
AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

message.add(Actualtoastmessage)

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.takeScreenshot()

}
catch(NoSuchElementException e)
{
	break
}
}

KeywordUtil.logInfo(message.size().toString())

GlobalVariable.label1 = message.get(0)

KeywordUtil.logInfo(GlobalVariable.label1)

GlobalVariable.label2 = message.get(message.size() - 1)

KeywordUtil.logInfo(GlobalVariable.label2)



