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
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory

Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.tap(findTestObject('Mobile/New Retailer/New Retailer Request'), 0, FailureHandling.OPTIONAL)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('null'), 'TestRetailer', 0)

device_width = Mobile.getDeviceWidth()

devic_x = device_width /2

channel_y_value = Mobile.getAttribute(findTestObject('null'), 'y', 0)

channel_x_value = Mobile.getAttribute(findTestObject('null'), 'x', 0)

channel_bounds = Mobile.getAttribute(findTestObject('null'), 'bounds', 0)

String[] bounds = channel_bounds.split(',')

println(bounds[2])

String bound = bounds[2]

Y_val = bound.replace(']', '')

println(Y_val)

Mobile.tap(findTestObject('null'), 0)

Mobile.setText(findTestObject('null'), 'channel ', 0)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

x_value = (Integer.parseInt(channel_x_value) + 50)

y_value = (Integer.parseInt(Y_val) + 45)

//y_value = (Integer.parseInt(Y_val) + 140)

Mobile.tapAtPosition(devic_x, y_value)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('null'), 0)

