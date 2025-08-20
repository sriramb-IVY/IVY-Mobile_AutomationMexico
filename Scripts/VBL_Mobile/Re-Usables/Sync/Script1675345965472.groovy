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
import org.apache.poi.hssf.record.PageBreakRecord.Break as Break
import org.openqa.selenium.Keys as Keys

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)
} else {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Menu'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)
}

for (int i = 0; i <= 3; i++) {
    colour = Mobile.getAttribute(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 'enabled', 2, FailureHandling.OPTIONAL)

    if (colour == 'true') {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 120, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 1, FailureHandling.OPTIONAL)
    } else {
        break
    }
}

