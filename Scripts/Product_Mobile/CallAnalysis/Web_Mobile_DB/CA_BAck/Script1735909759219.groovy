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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Return View'), [:],
	FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.click(findTestObject('Web Part/Re-usable/Navigation Hide Icon'))

WebUI.waitForElementVisible(findTestObject('Web Part/SalesReturnView/SalesPerson'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/SalesReturnView/SalesPerson'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('Mobile Input Data/SalesReturn').getValue('SalesPerson', 1)

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.click(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/SalesReturnView/Search_Btn'))

String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

GlobalVariable.SR_Amt = findTestData('Mobile Input Data/SalesReturn').getValue('SalesReturn_Amt', 1)

queryString = (((('select top 1 * from appdata_sales_return_header where srh_Date = \'' + DB_Currentdate) + '\'  and SRH_Amount = \'') +
GlobalVariable.SR_Amt) + '\' and SRH_UserId=1589 and SRH_ARTR_Id=411 order by 1 desc ')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
	Object SRH_ID = recordSet.getObject('SRH_ID')

	GlobalVariable.label2 = SRH_ID

	println(GlobalVariable.label2)

	Object SRH_Amount = recordSet.getObject('SRH_Amount')

	Object SRH_No = recordSet.getObject('SRH_No')

	GlobalVariable.SR_No = SRH_No

	println('Sales return Number  :  ' + GlobalVariable.SR_No)

	WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

	WebUI.verifyElementPresent(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), 0)

	WebUI.verifyElementVisible(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'))

	WebUI.verifyElementText(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), GlobalVariable.SR_No)

	WebUI.takeScreenshot()

	println('Sales Return Number Displayed Properly in both Database and SalesReturnView Screen details grid!')

	DetailsGridReturnAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/DetailsGrid_ReturnAmt'))

	DetailsGrid_ReturnAmt = DetailsGridReturnAmt.replace('$ ', '')

	println(DetailsGrid_ReturnAmt)

	WebUI.verifyEqual(Double.parseDouble(DetailsGrid_ReturnAmt), SRH_Amount, FailureHandling.STOP_ON_FAILURE)

	println('Return Amount Displayed Properly in both Database and SalesReturnView details grid Screen!')

	WebUI.takeScreenshot()
}

WebUI.click(findTestObject('Web Part/SalesReturnView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

//2nd DB
queryString1 = (('select Top 1 * from appdata_sales_return_detail where srd_srh_id=\'' + GlobalVariable.label2) + '\'')

KeywordUtil.logInfo(queryString1)

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
	Object SRD_SRH_No = recordSet1.getObject('SRD_SRH_No')

	println('Sales Return no : ' + SRD_SRH_No)

	PopUpView_SR_No = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-SR_No'), 'value',
		FailureHandling.STOP_ON_FAILURE)

	println(PopUpView_SR_No)

	WebUI.verifyMatch(PopUpView_SR_No, SRD_SRH_No, false, FailureHandling.STOP_ON_FAILURE)

	println('Sales Return number displayed properly in both database and salesreturn view popup screen')

	WebUI.takeScreenshot()

	Object SRD_Piece_Qty = recordSet1.getObject('SRD_Piece_Qty')

	GlobalVariable.DB_PieceQty = SRD_Piece_Qty

	println(GlobalVariable.DB_PieceQty)

	/////////////////////mobile validation
	'Product_1 Qty validation between Mobile and Database'
	PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

	WebUI.verifyEqual(GlobalVariable.DB_PieceQty, PieceQty, FailureHandling.STOP_ON_FAILURE)

	println('Product 1 : Entere Case and Piece Qty in the mobile apllication properly inserted into database!')

	//////////////web Qty
	'Product_1 Qty validation between Web and Database'
	WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'), 0)

	GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'))

	WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Integer.parseInt(GlobalVariable.PieceQty), FailureHandling.STOP_ON_FAILURE)

	println('Product 1 : Case and Piece Qty displayed properly in both database and SalesReturn popup view Screen')

	WebUI.takeScreenshot()

	///////////////////Web total
	'Product_1 Total validation between Web and Database'
	Object SRD_Line_Value = recordSet1.getObject('SRD_Line_Value')

	GlobalVariable.Line_Value = SRD_Line_Value

	GridTotalAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_TotalAmt'))

	Grid_TotalAmt = GridTotalAmt.replace('$ ', '')

	WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

	println('Product 1 : Total Amount displayed properly in both database and salesreturn view popup grid screen')

	WebUI.takeScreenshot()

	Object SRD_APH_Id = recordSet1.getObject('SRD_APH_Id')

	GlobalVariable.label = SRD_APH_Id

	println('Sku ID : ' + GlobalVariable.label)
}

queryString5 = (('select Top 1 * from appdata_sales_return_detail where srd_srh_id=\'' + GlobalVariable.label2) + '\'order by 1 desc ')

KeywordUtil.logInfo(queryString5)

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

while (recordSet5.next()) {
	Object SRD_Piece_Qty = recordSet5.getObject('SRD_Piece_Qty')

	GlobalVariable.DB_PieceQty = SRD_Piece_Qty

	println(GlobalVariable.DB_PieceQty)

	'Product_2 Qty validation between Web and Database'
	GlobalVariable.CaseQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_CaseQty_2'))

	GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty_2'))

	GlobalVariable.CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', 2)

	QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

	Case_Piece_Qty = (QtyCase + Integer.parseInt(GlobalVariable.PieceQty))

	WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Case_Piece_Qty, FailureHandling.STOP_ON_FAILURE)

	println('Case and Piece Qty displayed properly in both database and SalesReturn popup view Screen')

	WebUI.takeScreenshot()

	'Product_2 Total validation between Web and Database'
	Object SRD_Line_Value = recordSet5.getObject('SRD_Line_Value')

	//GlobalVariable.Line_Value = SRD_Line_Value
	GridTotalAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_TotalAmt_2'))

	Grid_TotalAmt = GridTotalAmt.replace('$ ', '')

	WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), SRD_Line_Value, FailureHandling.STOP_ON_FAILURE)

	println('Total Amount displayed properly in both database and salesreturn view popup grid screen')

	WebUI.takeScreenshot()

	/////////////////////// out of grid fields
	Total_salesreturn_Amt = (GlobalVariable.Line_Value + SRD_Line_Value)

	WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Total_Amt'), 0)

	PopUpView_TotalAmt = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-Total_Amt'), 'value',
		FailureHandling.STOP_ON_FAILURE)

	PopUpView_Total_Amt = PopUpView_TotalAmt.replace('$ ', '')

	WebUI.verifyEqual(Double.parseDouble(PopUpView_Total_Amt), Total_salesreturn_Amt, FailureHandling.STOP_ON_FAILURE)

	println('Total Amount displayed properly in both database and salesreturn view popup screen')

	WebUI.takeScreenshot()

	PopUpViewReturnAmt = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-SR_Amt'), 'value',
		FailureHandling.STOP_ON_FAILURE)

	PopUpView_ReturnAmt = PopUpViewReturnAmt.replace('$ ', '')

	WebUI.verifyEqual(Double.parseDouble(PopUpView_ReturnAmt), Total_salesreturn_Amt, FailureHandling.STOP_ON_FAILURE)

	println('Return Amount displayed properly in both database and salesreturn view popup screen')

	WebUI.takeScreenshot()

	PopUpView_CreditAmt = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-Credit_Amt'),
		'value', FailureHandling.STOP_ON_FAILURE)

	PopUpView_Credit_Amt = PopUpView_CreditAmt.replace('$ ', '')

	WebUI.verifyEqual(Double.parseDouble(PopUpView_Credit_Amt), Double.parseDouble(PopUpView_ReturnAmt), FailureHandling.STOP_ON_FAILURE)

	println('Credit Amount Generated properly based on return amount in salesreturn view popup screen')

	WebUI.takeScreenshot()

	Object SRD_APH_Id = recordSet5.getObject('SRD_APH_Id')

	GlobalVariable.label1 = SRD_APH_Id

	println('Sku ID : ' + GlobalVariable.label1)
}

'Product 2 : Name validate between web and database'
queryString2 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
	Object Sku_Name = recordSet2.getObject('APH_Name')

	WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'), 0)

	PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'))

	WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('SKU name displayed properly in both database and salesreturn view popup screen')

	WebUI.takeScreenshot()

	////////////////////////Mobile Validation
	GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn').getValue('ProductName', 1)

	WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('SKU name in the mobile application inserted into the database')
}

'Product 1 : Name validate between web and database'
queryString6 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\'')

KeywordUtil.logInfo(queryString6)

ResultSet rs6 = stm.executeQuery(queryString6)

def recordSet6 = rs6

while (recordSet6.next()) {
	Object Sku_Name = recordSet6.getObject('APH_Name')

	'Product 1 : Name validate between web and database'
	PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name_2'))

	WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('SKU name displayed properly in both database and salesreturn view popup screen')

	WebUI.takeScreenshot()

	////////////////////////Mobile Validation
	GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn').getValue('ProductName', 1)

	WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('SKU name in the mobile application inserted into the database')
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

	WebUI.click(findTestObject('Web Part/SalesReturnView/PopUp-Grid_Reason_2'))

	WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

	GlobalVariable.CaseQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-CaseQty'))

	GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-PieceQty'))

	CaseQty = findTestData('Mobile Input Data/SalesReturn').getValue('Case_1', 1)

	PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_1', 1)

	WebUI.verifyEqual(Integer.parseInt(GlobalVariable.CaseQty), Integer.parseInt(CaseQty), FailureHandling.STOP_ON_FAILURE)

	WebUI.verifyEqual(Integer.parseInt(GlobalVariable.PieceQty), Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

	println('Case and Piece Qty displayed properly in both database and SalesReturn popup reason view Screen')

	WebUI.takeScreenshot()

	reason_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-ReasonType'))

	WebUI.verifyMatch(SRR_Reason, reason_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('Reason Name displayed properly in both database and SalesReturn popup reason view Screen')

	///////////////// mobile validation
	GlobalVariable.S_ReasonType = findTestData('Mobile Input Data/SalesReturn').getValue('Reason_1', 1)

	WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

	println('Mentioned Reason in the mobile application properly inserted into the database' ///////////
		)
}

//////////////
WebUI.click(findTestObject('Object Repository/Web Part/SalesReturnView/Reason popup window_close btn'))

WebUI.click(findTestObject('Web Part/SalesReturnView/PopUp-Grid_Reason'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

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

	GlobalVariable.CaseQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-CaseQty'))

	GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-PieceQty'))

	//CaseQty =  findTestData('Mobile Input Data/SalesReturn').getValue('Case_1', 1)
	PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

	//WebUI.verifyEqual(Integer.parseInt(GlobalVariable.CaseQty), Integer.parseInt(CaseQty), FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyEqual(Integer.parseInt(GlobalVariable.PieceQty), Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

	println('Case and Piece Qty displayed properly in both database and SalesReturn popup reason view Screen')

	WebUI.takeScreenshot()

	reason_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-ReasonType'))

	WebUI.verifyMatch(SRR_Reason, reason_Name, false, FailureHandling.STOP_ON_FAILURE)

	println('Reason Name displayed properly in both database and SalesReturn popup reason view Screen')

	///////////////// mobile validation
	GlobalVariable.S_ReasonType = findTestData('Mobile Input Data/SalesReturn').getValue('Reason_2', 1)

	WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

	println('Mentioned Reason in the mobile application properly inserted into the database' ///////////
		)
}

WebUI.closeBrowser()