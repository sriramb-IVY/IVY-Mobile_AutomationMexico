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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = GlobalVariable.DistributorName

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Ditributor_Dropdown_option'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

String sheet_name = 'Scheme_Stock_Allocation'

String file_name = 'Web Input Data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

ArrayList<String> case_Quantity = CustomKeywords.'poi.Automation.GetAllWebDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'case_Quantity')

for (i = Starting_Row_No; i <= Ending_Row_No; i++) {
    GlobalVariable.ProductName = SKU_Name.get(i)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 0, FailureHandling.OPTIONAL)

    Mobile.delay(1)

    Mobile.hideKeyboard(FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 0, FailureHandling.OPTIONAL)

    Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), case_Quantity.get(i), 0, FailureHandling.OPTIONAL)
}

Mobile.delay(1)

Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(3)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

//String pattern = 'MM-dd-yyyy'
String pattern = 'MM'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

current_date = currentdate.replaceAll('\\D+', '')

KeywordUtil.logInfo(current_date)

UserID = findTestData('Mobile Input Data/ManualVanLoad').getValue('User_ID', 1)

String Vanload_no = UserID + current_date

KeywordUtil.logInfo(Vanload_no)

GlobalVariable.VanLoad_No = Vanload_no

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

Mobile.delay(3)

