import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.google.common.collect.FilteredEntryMultimap.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert


String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

GlobalVariable.label = '1585'

queryString = (((('Select  * from appdata_sales_return_header where SRH_Date = \'' + DB_Currentdate) + '\'  and SRH_UserId = \'') +
GlobalVariable.label) + '\' order by 1 desc ')

KeywordUtil.logInfo('Query 1 : '+queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	Object SRH_ID = recordSet.getObject('SRH_ID')

	GlobalVariable.label2 = SRH_ID

	println(GlobalVariable.label2)

	Object SRH_Amount = recordSet.getObject('SRH_Amount')

	Object SRH_No = recordSet.getObject('SRH_No')

	GlobalVariable.SR_No = SRH_No

	println('Sales return Number  :  ' + GlobalVariable.SR_No)

	
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
//2nd DB
queryString1 = (('select  * from appdata_sales_return_detail where SRD_SRH_Id=\'' + GlobalVariable.label2) + '\'')

KeywordUtil.logInfo(queryString1)

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

if (recordSet1.next()) {
	Object SRD_SRH_No = recordSet1.getObject('SRD_SRH_No')

	println('Sales Return no : ' + SRD_SRH_No)

	
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

queryString5 = (('select  * from AppData_Sales_Return_Empty_Detail where SRED_SRH_id=\'' + GlobalVariable.label2) + '\'order by 1 desc ')

KeywordUtil.logInfo('Query : '+queryString5)

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

if (recordSet5.next()) {
	Object SRD_Piece_Qty = recordSet5.getObject('SRD_Piece_Qty')

	GlobalVariable.DB_PieceQty = SRD_Piece_Qty

	println(GlobalVariable.DB_PieceQty)
	
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

'Product 2 : Name validate between web and database'
queryString2 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object Sku_Name = recordSet2.getObject('APH_Name')

	
}

'Product 1 : Name validate between web and database'
queryString6 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\'')

KeywordUtil.logInfo(queryString6)

ResultSet rs6 = stm.executeQuery(queryString6)

def recordSet6 = rs6

while (recordSet6.next()) {
	Object Sku_Name = recordSet6.getObject('APH_Name')

	
}

'Product 2 : Reason  validate between web and database'
queryString3 = (('select Top 1  * from appdata_sales_return_reasons where srr_srh_id=\'' + GlobalVariable.label2) + '\'')

KeywordUtil.logInfo(queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
	Object SRR_Reason_Lov_Id = recordSet3.getObject('SRR_Reason_Lov_Id')

	GlobalVariable.label1 = SRR_Reason_Lov_Id

	println('Reason ID :' + GlobalVariable.label1)
}

queryString4 = (('select * from adm_lovs where alov_id=\'' + GlobalVariable.label1) + '\'')

KeywordUtil.logInfo(queryString4)

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
	Object SRR_Reason = recordSet4.getObject('ALOV_Name')

	
}


'Product 1 : Reason  validate between web and database'
queryString7 = (('select Top 1  * from appdata_sales_return_reasons where srr_srh_id=\'' + GlobalVariable.label2) + '\' order by 1 desc')
KeywordUtil.logInfo(queryString7)
ResultSet rs7 = stm.executeQuery(queryString7)

def recordSet7 = rs7

while (recordSet7.next()) {
	Object SRR_Reason_Lov_Id = recordSet7.getObject('SRR_Reason_Lov_Id')

	GlobalVariable.label1 = SRR_Reason_Lov_Id

	println('Reason ID :' + GlobalVariable.label1)
}

queryString8 = (('select * from adm_lovs where alov_id=\'' + GlobalVariable.label1) + '\'')
KeywordUtil.logInfo(queryString8)
ResultSet rs8 = stm.executeQuery(queryString8)

def recordSet8 = rs8

while (recordSet8.next()) {
	Object SRR_Reason = recordSet8.getObject('ALOV_Name')

	
}

