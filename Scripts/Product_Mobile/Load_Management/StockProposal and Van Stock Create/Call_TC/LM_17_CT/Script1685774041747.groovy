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
import org.junit.Assert as Assert
not_run: Mobile.closeApplication()

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_Van_UnLoad_header where vuh_auh_id=1589 and vuh_av_id=206 and vuh_date=\'' + DB_Currentdate + '\'  order by 1 desc', ('columnNames') : ['vuh_ID']], FailureHandling.STOP_ON_FAILURE)

String vuh_ID=GlobalVariable.data[0]

KeywordUtil.logInfo('Unload header id : '+vuh_ID)

    GlobalVariable.label = vuh_ID

    KeywordUtil.logInfo('vuh_ID : ' + GlobalVariable.label)


//2nd DB
	
	
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  top 1 * from AppData_Van_UnLoad_Detail where vud_vuh_id=\'' + GlobalVariable.label + '\' order by 1 desc', ('columnNames') : ['VUD_Qty']], FailureHandling.STOP_ON_FAILURE)
	
	String CaseQty=GlobalVariable.data[0]
	
	KeywordUtil.logInfo('Case qty : '+CaseQty)

    Mobile.verifyEqual(CaseQty, findTestData('Mobile Input Data/VanUnload').getValue('CaseQty', 1), FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('Entere Case Qty in the mobile Van Unload screen should be properly inserted into database')


//3nd DB
	
	
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  top 1 * from AppData_Van_UnLoad_Detail where vud_vuh_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['VUD_Qty','VUD_APH_Id']], FailureHandling.STOP_ON_FAILURE)
	
	String PieceQty=GlobalVariable.data[0]
	
	KeywordUtil.logInfo('Piece qty : '+PieceQty)
	
    Mobile.verifyEqual(PieceQty, findTestData('Mobile Input Data/VanUnload').getValue('PieceQty', 1), FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('Enter Piece Qty in the mobile Van Unload screen should be properly inserted into database')
	
	String VUD_APH_Id=GlobalVariable.data[0]
	
    KeywordUtil.logInfo('Product ID (VUD_APH_Id):' + VUD_APH_Id)

    GlobalVariable.label1 = VUD_APH_Id
	
	
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from ADM_Product_Hierarchy where aph_id =\'' + GlobalVariable.label1 + '\' order by 1 desc', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)
	
	String APH_Name=GlobalVariable.data[0]
	
    KeywordUtil.logInfo('DB Product Name : ' + APH_Name)

    Mobile.verifyMatch(APH_Name, findTestData('Mobile Input Data/VanUnload').getValue('Product_Name', 1), false, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('Entere product in the mobile Van Unload screen should be properly inserted into database')


