import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.StartApplicationKeyword as StartApplicationKeyword
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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//Mobile.startApplication(GlobalVariable.APK_File, false)
not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 2, FailureHandling.OPTIONAL)) {
    'End of the Day Menu Visible'
} else if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_End of the Day'), 0)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/Manual Van Load_SubMenu'), 0)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Search Icon'), 0)

not_run: String sheet_name = 'Scheme_Stock_Allocation'

not_run: String file_name = 'Web Input Data'

not_run: ArrayList<String> case_Quantity = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'case_Quantity')

not_run: ArrayList<String> SKU_Name = CustomKeywords.'poi.VBL.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

not_run: for (i = Starting_Row_No; i <= Ending_Row_No; i++) {
    GlobalVariable.ProductName = SKU_Name.get(i)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), 0, FailureHandling.STOP_ON_FAILURE)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/ManualVanload/CaseField'), case_Quantity.get(i), 0, FailureHandling.STOP_ON_FAILURE)
}

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Button-Save'), 0)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/End of the Day/StockProposal/Alert-OK Btn'), 0)

not_run: Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 15, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(1)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 15, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu-Van Load'), 0)

not_run: String pattern = 'MM-dd-yyyy'

not_run: SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

not_run: String currentdate = simpleDateFormat.format(new Date())

not_run: println(currentdate)

not_run: current_date = currentdate.replaceAll('\\D+', '')

not_run: KeywordUtil.logInfo(current_date)

not_run: UserID = GlobalVariable.vanseller_user_id

not_run: String Vanload_no = UserID + current_date

not_run: KeywordUtil.logInfo(Vanload_no)

not_run: GlobalVariable.VanLoad_No = Vanload_no

not_run: Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 10, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/Vanload/OK Btn'), 10, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(2)

