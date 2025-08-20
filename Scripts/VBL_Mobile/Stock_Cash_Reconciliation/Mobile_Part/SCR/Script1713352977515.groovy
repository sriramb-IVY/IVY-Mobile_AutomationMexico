import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import java.text.SimpleDateFormat as SimpleDateFormat
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat as DateFormat
import java.util.Date as Date

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Stock_Update_From_Web'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Admin_Config_Enable'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Login to Mobile'
Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.STOP_ON_FAILURE)

'Start Trip'
Mobile.callTestCase(findTestCase('VBL_Mobile/Trip/Trip_01 to 05'), [:], FailureHandling.STOP_ON_FAILURE)

'Mark Attendence'
Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

'Start Odometer'
Mobile.callTestCase(findTestCase('VBL_Mobile/Attendance and Odometer/OD_01_02(Start Odometer)'), [:], FailureHandling.STOP_ON_FAILURE)

'Accept the vanload stock'
Mobile.callTestCase(findTestCase('VBL_Mobile/Van Load/Reusable/VanloadAccept'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Generate_Invoice'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Mobile_Sales_Return'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/Mobile_Collection'), [:], FailureHandling.STOP_ON_FAILURE)

'Completed the Close call'
Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Call Test Case/VanUnload'), [:], FailureHandling.STOP_ON_FAILURE)

'End Trip'
Mobile.callTestCase(findTestCase('VBL_Mobile/Trip/Trip_06 to 10'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/Admin_Config_Disable'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Web_Part/STRC_001_to_STRC_030'), [:], FailureHandling.CONTINUE_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Stock_Cash_Reconciliation/Web_Part/CRC_001_to_CRC_027'), [:], FailureHandling.CONTINUE_ON_FAILURE)

