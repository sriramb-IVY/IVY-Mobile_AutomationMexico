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

'Mark the attendance as Working.'



WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 2)], 
    FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'),
	5, FailureHandling.OPTIONAL)) {

		Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 5)
}

if (Mobile.verifyElementExist(findTestObject('Mobile/Attendance/Btn_END'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
} else {
    WebUI.callTestCase(findTestCase('Product_Mobile/Common/PermissionAllow'), [:], FailureHandling.OPTIONAL)
	
	Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 2,FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 5)

    Mobile.tap(findTestObject('Mobile/Attendance/RadioButton_Working'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

