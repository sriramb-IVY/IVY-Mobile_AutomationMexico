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

Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()

Mobile.delay(10)

if (Mobile.verifyElementNotExist(findTestObject('Mobile/Common/Btn_Menu'), 5, FailureHandling.OPTIONAL)) {
    WebUI.callTestCase(findTestCase('Product_Mobile/Common/PermissionAllow'), [:], FailureHandling.OPTIONAL)

    if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Popup_Title_DateTimeChanged'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_GO TO SETTINGS'), 1, FailureHandling.OPTIONAL)

        Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Mobile_Login/TimeZone Handle_TC'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 5)

        Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_Allow'), 10, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_Allow'), 10, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_Allow'), 2, FailureHandling.OPTIONAL)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('Mobile/Login/Field_EnterActivationKey'), 1, FailureHandling.OPTIONAL)) {
        Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterActivationKey'), GlobalVariable.Activation_Key)

        Mobile.tap(findTestObject('Mobile/Login/Btn_Activate'), 3)

        Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_OK'), 100)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
    }
    
    if (Mobile.waitForElementPresent(findTestObject('Mobile/Login/Field_EnterUsername'), 10, FailureHandling.OPTIONAL)) {
        Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterUsername'), GlobalVariable.VanSeller_UserName)

        Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterPassword'), GlobalVariable.VanSeller_Password)

        Mobile.tap(findTestObject('Mobile/Login/Btn_Login'), 0)

        if (Mobile.waitForElementPresent(findTestObject('Mobile/Common/Popup_Title_ContinueInThisDevice'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 10)
        }
        
        if (Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_Retry'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Mobile/Common/Btn_Retry'), 10, FailureHandling.OPTIONAL)
        }
        
        for (int i = 0; i <= 15; i++) {
            if (Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_Retry'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Mobile/Common/Btn_Retry'), 10, FailureHandling.OPTIONAL)
            }
            
            if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Menu'), 5, FailureHandling.OPTIONAL)) {
                'Successfully logged in.'
                break
            }
            
            if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
            }
            
            if (Mobile.verifyElementVisible(findTestObject('Mobile/Login/Btn_Login'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Mobile/Login/Btn_Login'), 0, FailureHandling.OPTIONAL)

                Mobile.delay(15)
            }
            
            if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 5, 
                FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 0)
            }
            
            if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_IGNORE'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Mobile/Common/Btn_IGNORE'), 0)

                Mobile.tap(findTestObject('Mobile/Common/Popup_Option_IgnoreForWholeSession'), 0)
            }
        }
    }
}

