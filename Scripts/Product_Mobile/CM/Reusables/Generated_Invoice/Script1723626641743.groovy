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
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint



Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/EnterPieceQtyForSingleProduct'), [('ProductName') :  findTestData('Mobile Input Data/CreditManagement').getValue('CM_SKU_Name', 1), ('PieceQty') : '60'], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Invoice_Submitted_No_Toast and Alert'), [('Expecting_Alert_Text') : findTestData('Mobile Input Data/CreditManagement').getValue('Alert_Popup01', Credit_Index)], FailureHandling.STOP_ON_FAILURE)

//RetailerIndex= Credit_Index
//
//Mobile.delay(2)
//
//Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
//
//Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)
//
//String pattern = 'dd-MM-yyyy'
//
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)
//
//String currentdate = simpleDateFormat.format(new Date())
//
//ScrollDate = (currentdate + '(')
//
//GlobalVariable.label = currentdate
//
////Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)
//
//Mobile.takeScreenshot()
//
//KeywordUtil.logInfo('The user can view the list of Pending Invoices details')
//
//TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)
//
//GlobalVariable.Transaction_No = TransactionNo
//
//String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'
//
//String Sheetname = 'CreditManagement'
//
//workbook01 = ExcelKeywords.getWorkbook(exlpath)
//
//sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)
//
//ExcelKeywords.setValueToCellByIndex(sheet1, RetailerIndex, 8, TransactionNo)
//
//ExcelKeywords.saveWorkbook(exlpath, workbook01)
//
//Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)
//

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

