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

KeywordUtil.logInfo(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

'AppData_Stock_Proposal_Header'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_Stock_Proposal_Header where SPH_AUH_Id = 1589  and SPH_Date =\'' + DB_Currentdate + '\'  order by 1 desc ', ('columnNames') : ['SPH_Id']], FailureHandling.STOP_ON_FAILURE)

String SPH_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('SPH_Id : '+SPH_Id)

KeywordUtil.logInfo(SPH_Id.toString())

GlobalVariable.label = SPH_Id


'AppData_Stock_Proposal_Detail - Piece Qty'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label + '\'  ', ('columnNames') : ['SPD_Original_Qty']], FailureHandling.STOP_ON_FAILURE)

String SPD_Original_Qty=GlobalVariable.data[0]

KeywordUtil.logInfo('SPD_Original_Qty : '+SPD_Original_Qty)

KeywordUtil.logInfo(SPD_Original_Qty.toString())

PieceQty = findTestData('Mobile Input Data/StockProposal').getValue('PieceQty', 1)

Mobile.verifyEqual(SPD_Original_Qty, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)


//3nd DB

'AppData_Stock_Proposal_Detail - Case Qty'


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select Top 1 * from AppData_Stock_Proposal_Detail where SPD_SPH_Id=\'' + GlobalVariable.label + '\' order by 1 desc ', ('columnNames') : ['SPD_Original_Qty','SPD_APH_Id']], FailureHandling.STOP_ON_FAILURE)

String data=GlobalVariable.data[0]

KeywordUtil.logInfo('SPD_Original_Qty : '+data)

KeywordUtil.logInfo(data.toString())

CaseQty = findTestData('Mobile Input Data/StockProposal').getValue('CaseQty', 1)

Mobile.verifyEqual(data, Integer.parseInt(CaseQty), FailureHandling.STOP_ON_FAILURE)

Object data1 = GlobalVariable.data[1]

KeywordUtil.logInfo(data1.toString())

GlobalVariable.label = data1



//4th DB

'ADM_Product_Hierarchy'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from ADM_Product_Hierarchy where aph_id =\'' + GlobalVariable.label + '\'', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)

String SKUName=GlobalVariable.data[0]

KeywordUtil.logInfo(SKUName.toString())

Product_Name = findTestData('Mobile Input Data/StockProposal').getValue('Product_Name', 1)

Mobile.verifyMatch(SKUName, Product_Name, false, FailureHandling.STOP_ON_FAILURE)

