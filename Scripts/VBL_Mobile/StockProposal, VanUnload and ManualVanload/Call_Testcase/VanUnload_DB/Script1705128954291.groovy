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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

//GlobalVariable.SR_Amt = '550.000000'
queryString = (('select top 1 * from AppData_Van_UnLoad_header where vuh_auh_id=\'' + GlobalVariable.vanseller_user_id + '\' and vuh_av_id=15 and vuh_date=\'' +
DB_Currentdate) + '\'  order by 1 desc ')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	Object vuh_ID = recordSet.getObject('vuh_ID')

	GlobalVariable.label = vuh_ID

	println('vuh_ID : ' + GlobalVariable.label)
}

//2nd DB
queryString1 = (('select  top 1 * from AppData_Van_UnLoad_Detail where vud_vuh_id=\'' + GlobalVariable.label) + '\' order by 1 desc')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	Object CaseQty = recordSet1.getObject('VUD_Qty')

	Mobile.verifyEqual(CaseQty, findTestData('VBL_Mobile Input Data/VanUnload').getValue('CaseQty', 1), FailureHandling.STOP_ON_FAILURE)

	println('Entere Case Qty in the mobile Van Unload screen should be properly inserted into database')
	
	}

//3nd DB
queryString2 = (('select  top 1 * from AppData_Van_UnLoad_Detail where vud_vuh_id=\'' + GlobalVariable.label) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object PieceQty = recordSet2.getObject('VUD_Qty')

	Mobile.verifyEqual(PieceQty, findTestData('VBL_Mobile Input Data/VanUnload').getValue('PieceQty', 1), FailureHandling.STOP_ON_FAILURE)

	println('Entere Piece Qty in the mobile Van Unload screen should be properly inserted into database')
	
	Object VUD_APH_Id = recordSet2.getObject('VUD_APH_Id')

	println('Product ID (VUD_APH_Id):' + VUD_APH_Id)

	GlobalVariable.label1 = VUD_APH_Id
}

queryString4 = (('select top 1 * from ADM_Product_Hierarchy where aph_id =\'' + GlobalVariable.label1) + '\' order by 1 desc')

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
	Object APH_Name = recordSet4.getObject('APH_Name')

	println('DB Product Name : ' + APH_Name)

	Mobile.verifyMatch(APH_Name, findTestData('VBL_Mobile Input Data/VanUnload').getValue('ProductName', 1), false, FailureHandling.STOP_ON_FAILURE)

	println('Entere product in the mobile Van Unload screen should be properly inserted into database')
	
	}

