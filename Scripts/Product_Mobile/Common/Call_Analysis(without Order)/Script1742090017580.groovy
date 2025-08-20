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

Mobile.delay(2)

Mobile.swipe(50, 50, 50, 600, FailureHandling.OPTIONAL)

Mobile.delay(2)

if (Mobile.verifyElementVisible(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 5, FailureHandling.OPTIONAL)) {
    
	Mobile.tap(findTestObject('Mobile/CallAnalysis/StoreActy_Close Call Btn'), 0)

    Mobile.tap(findTestObject('Mobile/CallAnalysis/Select Reason for no order'), 0)

    Mobile.tap(findTestObject('Mobile/CallAnalysis/Others'), 0)

    Mobile.tap(findTestObject('Mobile/CallAnalysis/Remarks'), 0)

    Mobile.setText(findTestObject('Mobile/CallAnalysis/Remarks'), 'other work', 0)

    Mobile.tap(findTestObject('Mobile/CallAnalysis/Close Call'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
}

