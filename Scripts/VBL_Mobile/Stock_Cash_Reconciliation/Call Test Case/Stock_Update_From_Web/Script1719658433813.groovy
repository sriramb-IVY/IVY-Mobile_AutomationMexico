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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import org.openqa.selenium.WebElement as WebElement
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

'Stock Allocation'
WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to StockAllocation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Ware house'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Warehouse', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Store'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Store', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/DropdownSelect_Li Tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Field values/User Details_Title'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Seller', 1)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/UserSelect_Span b Tag'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Manual_btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1)

GlobalVariable.label = GlobalVariable.ProductName

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

WebUI.clearText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Sku_Name'), GlobalVariable.ProductName, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Filter/Appy_Btn'), FailureHandling.OPTIONAL)

//	WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'),
//		2, FailureHandling.OPTIONAL)
//
//	GlobalVariable.CaseQty = case_Quantity.get(j)
//
//	WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterCaseValue_Td Tag'),
//		GlobalVariable.CaseQty, FailureHandling.OPTIONAL)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)

GlobalVariable.PieceQty = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Vanload_Qty', 1)

WebUI.sendKeys(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/EnterPieceValue_Td Tag'), GlobalVariable.PieceQty, FailureHandling.OPTIONAL)

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Allocate_btn'))

WebUI.delay(2)

if (WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'), 1, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Drop down values/Route'))

	WebUI.delay(1)

	GlobalVariable.Route = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Route', 1)

	WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Global Variables/Option_tag_Dropdown(global)'))

	WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Yes,continue'))
}

WebUI.verifyElementPresent(findTestObject('XXXXXXXXXXXX/Web Part/Stock Allocation/Button/Manual_btn'), 10)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

'Execute Query 01'
queryString = (('Select Top 1 * from AppData_Van_Load_Header where VLH_AUH_Id=\'' + GlobalVariable.vanseller_user_id) + '\' order by 1 desc ')

KeywordUtil.logInfo(queryString)

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	Object data1 = recordSet.getObject('VLH_Reference_No')

	GlobalVariable.VanLoad_No = data1

	KeywordUtil.logInfo(GlobalVariable.VanLoad_No)
}

