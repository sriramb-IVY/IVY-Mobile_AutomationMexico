import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

KeywordUtil.logInfo(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from AppData_Digital_Content_Tracking_Header where dcth_user_id=1589  and DCTH_Date =\'' + DB_Currentdate +
	'\'  order by 1 desc', ('columnNames') : ['DCTH_Id']], FailureHandling.STOP_ON_FAILURE)

String DCTH_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Digital data header id : '+DCTH_Id)


GlobalVariable.label = DCTH_Id

KeywordUtil.logInfo(('DCTH_Id : ' + GlobalVariable.label) + 'File is present')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from AppData_Digital_Content_Tracking_Detail where dctd_dcth_id=\'' + GlobalVariable.label +
	'\'  order by 1 desc', ('columnNames') : ['DCTD_Id']], FailureHandling.STOP_ON_FAILURE)

String DCTD_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Digital data  : '+DCTD_Id)

Mobile.verifyNotEqual(DCTD_Id, 0, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('File is present')
