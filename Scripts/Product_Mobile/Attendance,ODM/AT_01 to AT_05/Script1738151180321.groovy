import org.junit.Assert as Assert
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.STOP_ON_FAILURE)

'"Please Mark Attendance To Proceed" toast message should be displayed while click other submenu without attendance.'


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3)
        , ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Attendance').getValue('Verify_Text', 2), false, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 2)
        , ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

'To verify whether the user can navigate correctly to the Attendance screen.'
Mobile.verifyElementText(findTestObject('Mobile/Attendance/Title_Attendance'), findTestData('Mobile Input Data/Attendance').getValue(
        'Verify_Text', 1))

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Plus'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/No Data Exists'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 0)

Mobile.delay(5)

'The Attendance Reason popup window should be displayed with Select reason Title and Ok button.'
 Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Popup_Title_SelectReason'), 
    0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

' Without selecting any option and tap OK button, "Please select an Item" toast should be displayed.'
Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Attendance').getValue('Verify_Text', 5), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

reason_list = findTestData('Mobile Input Data/Attendance').getValue('Reason', 1)

String[] reasons = reason_list.split(',')

for (int i = 0; i < reasons.size(); i++) {
    reason = (reasons[i])

    if (reason != 'WORKING') {
        Mobile.scrollToText(reason)

        KeywordUtil.logInfo(reason)

        GlobalVariable.label = reason

        //TestObject radioButton = findTestObject('Mobile/Attendance/Global_RadioButton', [('reason'): reason])  
        Mobile.tap(findTestObject('Mobile/Attendance/Global_RadioButton'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.delay(1)

        'The attendance should be added to the screen with an End Button, Status(Partial), Reason(selected Option)'
        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_StartTime'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_EndTime'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_Status'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_Reason'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        String starttime = Mobile.getText(findTestObject('Mobile/Attendance/Field_StartTime'), 3)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/CurrentDatePrint'), [('dateType') : 'dd/MM/yyyy'], 
            FailureHandling.STOP_ON_FAILURE)

        boolean StartDateVisible = starttime.contains(GlobalVariable.sDate)

        Assert.assertTrue(StartDateVisible)

        Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/Attendance/Field_Reason'), reason, FailureHandling.STOP_ON_FAILURE)

        status = Mobile.getText(findTestObject('Mobile/Attendance/Field_Status'), 0)

        String actualText1 = status

        String expectedText1 = findTestData('Mobile Input Data/Attendance').getValue('Status', 2)

        'The texts match (case insensitive)'
        Assert.assertTrue(actualText1.equalsIgnoreCase(expectedText1))

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

        Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

        KeywordUtil.logInfo(Actualtoastmessage)

        'The user cannot pocess the application and "Please Mark Attendance As Working To Proceed." alert should be displayed.'
        Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Attendance').getValue('Verify_Text', 3), 
            false, FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        'End time should be updated the respective field.'
        Mobile.tap(findTestObject('Mobile/Attendance/Btn_END'), 0)

        Mobile.takeScreenshot()

        String EndDate = Mobile.getText(findTestObject('Mobile/Attendance/Field_EndTime'), 3)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/CurrentDatePrint'), [('dateType') : 'dd/MM/yyyy'], 
            FailureHandling.STOP_ON_FAILURE)

        boolean EnddateVisible = EndDate.contains(GlobalVariable.sDate)

        Assert.assertTrue(EnddateVisible)

        ' Status field should be changed into as complete.'
        status = Mobile.getText(findTestObject('Mobile/Attendance/Field_Status'), 0)

        String actualText = status

        String expectedText = findTestData('Mobile Input Data/Attendance').getValue('Status', 1)

        Assert.assertTrue(actualText.equalsIgnoreCase(expectedText))

        Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 0)
    } else {
        Mobile.scrollToText(reason)

        KeywordUtil.logInfo(reason)

        GlobalVariable.label = reason

        GlobalVariable.ReasonField = reason

        Mobile.tap(findTestObject('Mobile/Attendance/Global_RadioButton'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.delay(1)

        'The attendance should be added to the screen with an End Button  an End Button, Status(Partial), Reason(selected Option)'
        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_StartTime'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_EndTime'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_Status'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Attendance/Label_Reason'), 0, 
            FailureHandling.STOP_ON_FAILURE)

        String starttime = Mobile.getText(findTestObject('Mobile/Attendance/Field_StartTime'), 3)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/CurrentDatePrint'), [('dateType') : 'dd/MM/yyyy'], 
            FailureHandling.STOP_ON_FAILURE)

        boolean StartDateVisible = starttime.contains(GlobalVariable.sDate)

        Assert.assertTrue(StartDateVisible)

        Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementText(findTestObject('Mobile/Attendance/Field_Reason'), reason, FailureHandling.STOP_ON_FAILURE)

        //Mobile.verifyElementText(findTestObject('Mobile/Attendance/Field_Status'), 'Partial', FailureHandling.STOP_ON_FAILURE)
		
		String Attendance_Status = Mobile.getText(findTestObject('Mobile/Attendance/Field_Status'), 2, FailureHandling.STOP_ON_FAILURE)

		Attendance_Status.equalsIgnoreCase('Partial')
		
      Mobile.takeScreenshot()

        str = Mobile.getText(findTestObject('Mobile/Attendance/Field_StartTime'), 0)

        start_time = str.replaceAll('\\s', '')

        starttime = start_time.substring(10)

        println('starttime : ' + starttime)

        status = Mobile.getText(findTestObject('Mobile/Attendance/Field_Status'), 0)

        String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

        String Sheetname = 'Attendance'

        workbook01 = ExcelKeywords.getWorkbook(exlpath)

        sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, starttime)

        ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, reason)

        ExcelKeywords.saveWorkbook(exlpath, workbook01)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 0)

        '"Please complete your previous Start/End time" popu window should be displayed with OK button. '
        Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Popup_Title_PleaseCompletedYourPreviousTime'), 
            0, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

        'The user can process the application'
        Mobile.verifyElementExist(findTestObject('Mobile/Store_Actvy/Trade Coverage(Title)'), 3, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/Store_Actvy/Trade Coverage(Title)'), 2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()
    }
}


