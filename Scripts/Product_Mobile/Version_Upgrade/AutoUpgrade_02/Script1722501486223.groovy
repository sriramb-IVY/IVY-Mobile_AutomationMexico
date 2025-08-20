import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Version_Upgrade/Reusable/AutoUpgrade_Query'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Version_Upgrade/Reusable/AutoUpgrade_Query_Enable'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.scrollToText('Sync', FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Auto_Upgrade/version_Upgrade'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Login/Activation Successful PopUP_Ok button'), 10)

Mobile.delay(20)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Auto_Upgrade/Install_Button'), 5, FailureHandling.STOP_ON_FAILURE)) {
    Mobile.tap(findTestObject('Object Repository/Mobile/Auto_Upgrade/Install_Button'), 10)

    Mobile.delay(15)

    Mobile.tap(findTestObject('Object Repository/Mobile/Auto_Upgrade/Button - Open'), 10)
}

Mobile.delay(15)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Auto_Upgrade/App scan recommended'), 5, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Object Repository/Mobile/Auto_Upgrade/More details'), 10)

    Mobile.delay(2)

    Mobile.tap(findTestObject('Object Repository/Mobile/Auto_Upgrade/Install without scanning'), 10)

    Mobile.delay(15)

    Mobile.tap(findTestObject('Object Repository/Mobile/Auto_Upgrade/Button - Open'), 10)

    Mobile.delay(20)
}

if (Mobile.verifyElementVisible(findTestObject('Mobile/Login/Field_EnterUsername'), 5, FailureHandling.OPTIONAL)) {
    Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterUsername'), GlobalVariable.VanSeller_UserName)

    Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterPassword'), GlobalVariable.VanSeller_Password)

    Mobile.tap(findTestObject('Mobile/Login/Btn_Login'), 0)

    if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Popup_Title_ContinueInThisDevice'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 10)
    }
    
    Mobile.delay(35)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common_1/Login/Info_Icon'), 10)

Mobile.takeScreenshot()

if (Mobile.verifyElementNotExist(findTestObject('Mobile/Auto_Upgrade/Get_VersionNumber_InfoScreen'), 10, FailureHandling.OPTIONAL)) {
	
	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10, FailureHandling.OPTIONAL)
	
	Mobile.tap(findTestObject('Mobile/Common_1/Login/Info_Icon'), 10,FailureHandling.OPTIONAL)

}


Mobile.takeScreenshot()

Scrn_Version = Mobile.getText(findTestObject('Mobile/Common_1/Login/Version get from InfoScreen'), 0)

String[] parts = Scrn_Version.split('\\.')

String desiredNumber = parts[2]

String versionc = GlobalVariable.VersionCode

String lastFourDigitsVersionCode = versionc.substring(versionc.length() - 4)

Mobile.verifyMatch(desiredNumber, lastFourDigitsVersionCode, false, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Start_Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

Scheme_Index = 1

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/OTPR').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)


Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

//slab_1
'Slab 1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Done'), 1, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
}

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/Version_Upgrade/Reusable/GenerateInvoice_Order'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Sync with End Attendence'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('Object Repository/Mobile/Auto_Upgrade/version_Upgrade'), 0)

Mobile.takeScreenshot()

Mobile.callTestCase(findTestCase('Product_Mobile/Version_Upgrade/Reusable/HHT_Validation'), [:], FailureHandling.STOP_ON_FAILURE)

