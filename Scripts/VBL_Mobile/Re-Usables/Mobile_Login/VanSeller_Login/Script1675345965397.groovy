import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.junit.After as After
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

Mobile.startApplication(GlobalVariable.APK_File, false)

//CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()

Mobile.delay(3)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/PhoneCall_PopUp Label'), 4, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Permission_Allow Button'), 3)
}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/MediaFile_PopUp Label'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/MediaFile_Allow Button'), 3)
}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter Activation Key'), 4, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter Activation Key'), GlobalVariable.Activation_Key)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activate Button'), 3)

    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activation Successful PopUP_Ok button'), 100)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activation Successful PopUP_Ok button'), 0)

    Mobile.delay(3)

    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), GlobalVariable.VanSeller_UserName)

    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Password'), GlobalVariable.VanSeller_Password)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 0)
	
	Mobile.delay(20)
	
} else {
    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), 5, FailureHandling.OPTIONAL)) {
        UserName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), 0)

        println(UserName)

        if (UserName.trim().equals('Username')) {
            Mobile.delay(15)

            Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), GlobalVariable.VanSeller_UserName)

            Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Password'), GlobalVariable.VanSeller_Password)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 5)
        } else {
            Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Password'), GlobalVariable.VanSeller_Password)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 5)
        }
    }
}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this device_PopUp Label'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this devices_Yes button'), 10)
}



if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Search Service Provider-Field'), 
    5, FailureHandling.OPTIONAL)) {
    Mobile.setText(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Search Service Provider-Field'), GlobalVariable.Route, 0, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Select Route'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Confirm - OK Button'), 5)

    Mobile.delay(20)

    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 50)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/LocationAccess_PopUp Label'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Location_Allow button'), 5)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Mobile_locationAccess-OK button'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Mobile_locationAccess-OK button'), 5)
    }
    
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Plus Icon'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-Working'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-PopUp-OK'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
} else {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
} else {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Plus Icon'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-Working'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-PopUp-OK'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

