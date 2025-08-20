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

//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/Getting Last HHT_Transaction Id'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APK_File, false)

//Mobile.startApplication('${GlobalVariable.appPackage}', false)

CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()



Mobile.delay(5)

//if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 2, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 0)
//}
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Allow IvyCpg'), 1, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Permission_Allow Button'), 3)
//}
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Allow IvyCpg'), 1, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Permission_Allow Button'), 3)
//}
//
//if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Allow IvyCpg'), 1, FailureHandling.OPTIONAL)) {
//    Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Permission_Allow Button'), 3)
//}

if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Google/Enable Network Provided time'), 10, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3)

    WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/TimeHandle'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.STOP_ON_FAILURE)
}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter Activation Key'), 15, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter Activation Key'), GlobalVariable.Activation_Key)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activate Button'), 3)

    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activation Successful PopUP_Ok button'), 
        100)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Activation Successful PopUP_Ok button'), 0)

    Mobile.delay(3)
}
if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), 5, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Username'), GlobalVariable.VanSeller_UserName)

    Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Enter_Password'), GlobalVariable.VanSeller_Password)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 3, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 3, FailureHandling.OPTIONAL)

    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this device_PopUp Label'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this devices_Yes button'), 10)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 3, FailureHandling.OPTIONAL)
    }
    
   // Mobile.delay(35)
	Mobile.delay(5)

    for (i = 0; i <= 35; i++) {
        
		if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Search Service Provider-Field'), 5, FailureHandling.OPTIONAL)) {
			
			Mobile.delay(3)
			
			Mobile.setText(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Search Service Provider-Field'), GlobalVariable.Route, 0, FailureHandling.STOP_ON_FAILURE)

			Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Select Route'), 5)
			
			Mobile.delay(2)

			Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Confirm - OK Button'), 5)
			
			if(Mobile.verifyElementNotExist(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Select Route'), 5, FailureHandling.OPTIONAL))
			{
				break
			}
			else {
				Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Select Route'), 5,  FailureHandling.OPTIONAL)
				
				Mobile.delay(2)
	
				Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Confirm - OK Button'), 5, FailureHandling.OPTIONAL)
			}

		   
		}
		
		
		if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Button- OK'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/Button- OK'), 10)
        }
        
        if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this device_PopUp Label'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this devices_Yes button'), 10)

            Mobile.delay(20)
        }
        
        if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Login Button'), 10)

            if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this device_PopUp Label'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Continue in this devices_Yes button'), 10)
            }
            
            Mobile.delay(20)
        }
        
       
		
		if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 5, FailureHandling.OPTIONAL)) {
			'Successfully logged in.'
			break
		}
    }
    
    for (i = 0; i <= 35; i++) {
//        if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 1, FailureHandling.OPTIONAL)) {
//            Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 0)
//        }
        
        if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 5, FailureHandling.OPTIONAL)) {
            'Successfully logged in.'
            break
        }
    } // Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0, FailureHandling.STOP_ON_FAILURE)

