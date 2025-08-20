import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.sun.net.httpserver.Authenticator.Failure as Failure
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

String sheet_name = 'Scheme_Stock_Validation'

String file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

ArrayList<String> SKU_Stock_Before_Invoice = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Stock_Before_Invoice')

ArrayList<String> SKU_Invoice_Generated_Qty = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Invoice_Generated_Qty')

ArrayList<String> SKU_Stock_After_Invoice = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Stock_After_Invoice')

for (int i = 0; i < SKU_Stock_Before_Invoice.size(); i++) {
    KeywordUtil.logInfo(SKU_Name.get(i))

    String SKU_Stock_Before_Invoice1 = SKU_Stock_Before_Invoice.get(i)

    KeywordUtil.logInfo(SKU_Stock_Before_Invoice1)

    String SKU_Stock_After_Invoice1 = SKU_Stock_After_Invoice.get(i)

    KeywordUtil.logInfo(SKU_Stock_After_Invoice1)

    Stock_difference = (Integer.parseInt(SKU_Stock_Before_Invoice1) - Integer.parseInt(SKU_Stock_After_Invoice1))

    KeywordUtil.logInfo(SKU_Name.get(i) + ' : ' +Stock_difference.toString())

    String SKU_Invoice_Generated_Qty1 = SKU_Invoice_Generated_Qty.get(i)

    KeywordUtil.logInfo(SKU_Invoice_Generated_Qty1)

    WebUI.verifyEqual(Integer.parseInt(SKU_Invoice_Generated_Qty1), Stock_difference, FailureHandling.CONTINUE_ON_FAILURE)
}

