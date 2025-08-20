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

'AppData_Stock_Proposal_Header'

queryString = (('select top 1 * from AppData_Stock_Proposal_Header where SPH_AUH_Id = 297  and SPH_Date =\'' + DB_Currentdate) + '\'  order by 1 desc ')

KeywordUtil.logInfo(('select top 1 * from AppData_Stock_Proposal_Header where SPH_AUH_Id = 297  and SPH_Date =\'' + DB_Currentdate) + '\'  order by 1 desc ')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	Object data = recordSet.getObject('SPH_Id')

	KeywordUtil.logInfo(data.toString())
	
	GlobalVariable.label = data
}

'AppData_Stock_Proposal_Detail - Piece Qty'

queryString1 = (('select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label) + '\' ')

KeywordUtil.logInfo(('select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label) + '\'  ')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	Object data = recordSet1.getObject('SPD_Original_Qty')
	
	KeywordUtil.logInfo(data.toString())
	
	PieceQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('PieceQty', 1)
	
	Mobile.verifyEqual(data, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)
}

//3nd DB

'AppData_Stock_Proposal_Detail - Case Qty'

queryString2 = (('select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label) + '\' order by 1 desc ')

KeywordUtil.logInfo(('select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label) + '\' order by 1 desc ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object data = recordSet2.getObject('SPD_Original_Qty')
	
	KeywordUtil.logInfo(data.toString())
	
	
	CaseQty = findTestData('VBL_Mobile Input Data/StockProposal').getValue('CaseQty', 1)
	
	Mobile.verifyEqual(data, Integer.parseInt(CaseQty), FailureHandling.STOP_ON_FAILURE)
	
	Object data1 = recordSet2.getObject('SPD_APH_Id')
	
	KeywordUtil.logInfo(data1.toString())
	
	GlobalVariable.label = data1
	
}

//4th DB

'ADM_Product_Hierarchy'

queryString3 = (('select  * from ADM_Product_Hierarchy where aph_id =\'' + GlobalVariable.label) + '\' ')

KeywordUtil.logInfo(('select  * from ADM_Product_Hierarchy where aph_id =\'' + GlobalVariable.label) + '\' ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
	Object data = recordSet3.getObject('APH_Name')
	
	KeywordUtil.logInfo(data.toString())
	
	Product_Name = findTestData('VBL_Mobile Input Data/StockProposal').getValue('Product_Name', 1)
	
	Mobile.verifyMatch(data, Product_Name, false, FailureHandling.STOP_ON_FAILURE)
	
}