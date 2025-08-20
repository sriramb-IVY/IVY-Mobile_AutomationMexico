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
import org.testng.Assert as Assert

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


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_sales_return_header where srh_Date = \'' + DB_Currentdate + '\'  and SRH_Amount = \'' +
	GlobalVariable.SR_Amt + '\' and SRH_UserId=1589 and SRH_ARTR_Id=411 order by 1 desc', ('columnNames') : ['SRH_ID','SRH_Amount','SRH_No']], FailureHandling.STOP_ON_FAILURE)

String SRH_ID=GlobalVariable.data[0]

KeywordUtil.logInfo('Header id : '+SRH_ID)


GlobalVariable.label2 = SRH_ID

println(GlobalVariable.label2)

String SRH_Amount=GlobalVariable.data[1]

GlobalVariable.SR_Amt = SRH_Amount

String SRH_No=GlobalVariable.data[1]

GlobalVariable.SR_No = SRH_No

println('Sales return Number  :  ' + GlobalVariable.SR_No)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.verifyElementVisible(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Filter_Icon'))

WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Sales_Return_No'))

WebUI.clearText(findTestObject('Web Part/Stock Allocation/Filter/Sales_Return_No'), FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Web Part/Stock Allocation/Filter/Sales_Return_No'), GlobalVariable.SR_No)

WebUI.scrollToElement(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'), 2, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/Stock Allocation/Filter/Appy_Btn'))

WebUI.verifyElementPresent(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), 0)

WebUI.verifyElementVisible(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesReturnView/Global_Td_Tag(grid Value)'), GlobalVariable.SR_No)

WebUI.takeScreenshot()

println('Sales Return Number Displayed Properly in both Database and SalesReturnView Screen details grid!')

DetailsGridReturnAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/DetailsGrid_ReturnAmt'))

DetailsGrid_ReturnAmt = DetailsGridReturnAmt.replace('$ ', '')

println(DetailsGrid_ReturnAmt)

WebUI.verifyEqual(Double.parseDouble(DetailsGrid_ReturnAmt), GlobalVariable.SR_Amt, FailureHandling.STOP_ON_FAILURE)

println('Return Amount Displayed Properly in both Database and SalesReturnView details grid Screen!')

WebUI.takeScreenshot()

WebUI.comment('Validation for sales return view screen')

WebUI.click(findTestObject('Web Part/SalesReturnView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

Qty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

WebUI.comment('Sales Return detail table for return')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_sales_return_detail where srd_srh_id = \'' + GlobalVariable.label2 + '\'  and SRD_Piece_Qty = \'' +
	Qty + '\'', ('columnNames') : ['SRD_SRH_No','SRD_Piece_Qty','SRD_Line_Value','SRD_APH_Id','SRD_Id']], FailureHandling.STOP_ON_FAILURE)

String SRD_SRH_No=GlobalVariable.data[0]

KeywordUtil.logInfo('Sales Return no : ' + SRD_SRH_No)

PopUpView_SR_No = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-SR_No'), 'value',
		FailureHandling.STOP_ON_FAILURE)

println(PopUpView_SR_No)

WebUI.verifyMatch(PopUpView_SR_No, SRD_SRH_No, false, FailureHandling.STOP_ON_FAILURE)

println('Sales Return number displayed properly in both database and salesreturn view popup screen')

WebUI.takeScreenshot()

String SRD_Piece_Qty=GlobalVariable.data[1]

KeywordUtil.logInfo('piece qty : ' + SRD_Piece_Qty)


GlobalVariable.DB_PieceQty = SRD_Piece_Qty

println(GlobalVariable.DB_PieceQty)

'Mobile enter piece validation'

'Product_1 Qty validation between Mobile and Database'
PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

WebUI.verifyEqual(GlobalVariable.DB_PieceQty, PieceQty, FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Entere Case and Piece Qty in the mobile apllication properly inserted into database!')

'Web Validation'

'Product_1 Qty validation between Web and Database'
WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'), 0)

GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'))

WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Integer.parseInt(GlobalVariable.PieceQty), FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Case and Piece Qty displayed properly in both database and SalesReturn popup view Screen')

WebUI.takeScreenshot()

'Web total amount'

'Product_1 Total validation between Web and Database'

String SRD_Line_Value=GlobalVariable.data[2]

KeywordUtil.logInfo('Line value  : ' + SRD_Line_Value)


GlobalVariable.Line_Value = SRD_Line_Value

GridTotalAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_TotalAmt'))

Grid_TotalAmt = GridTotalAmt.replace('$ ', '')

WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Total Amount displayed properly in both database and salesreturn view popup grid screen')

WebUI.takeScreenshot()

String SRD_APH_Id=GlobalVariable.data[3]

KeywordUtil.logInfo('Product id : ' + SRD_APH_Id)

GlobalVariable.label = SRD_APH_Id

println('Sku ID : ' + GlobalVariable.label)

String SRD_Id=GlobalVariable.data[3]

GlobalVariable.label2 = SRD_Id

println('Return detail id: ' + GlobalVariable.label2)


'Reason 1 : SKU Name validate between web and database'


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)

String Sku_Name=GlobalVariable.data[0]

KeywordUtil.logInfo('SKU Name : '+Sku_Name)


WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'), 0)

PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'))

WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name displayed properly in both database and salesreturn view popup screen')

WebUI.takeScreenshot()

'Validation for selected sku inserted in DB'
GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn').getValue('ProductName', 1)

WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name in the mobile application inserted into the database')


' Reason  validate between web and database'


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from appdata_sales_return_reasons where SRR_SRD_id=\'' + GlobalVariable.label2 + '\'', ('columnNames') : ['SRR_Reason_Lov_Id']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason_Lov_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason id : '+SRR_Reason_Lov_Id)

GlobalVariable.label1 = SRR_Reason_Lov_Id

println('Reason ID :' + GlobalVariable.label1)



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_lovs where alov_id=\'' + GlobalVariable.label1 + '\'', ('columnNames') : ['ALOV_Name']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason : '+SRR_Reason)


GlobalVariable.CaseQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-CaseQty'))

GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-PieceQty'))

PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_2', 1)

WebUI.verifyEqual(Integer.parseInt(GlobalVariable.PieceQty), Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

println('Case and Piece Qty displayed properly in both database and SalesReturn popup reason view Screen')

WebUI.takeScreenshot()

reason_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-ReasonType'))

WebUI.verifyMatch(SRR_Reason, reason_Name, false, FailureHandling.STOP_ON_FAILURE)

println('Reason Name displayed properly in both database and SalesReturn popup reason view Screen')

'Validation for selected reason from mobile inserted or not'
GlobalVariable.S_ReasonType = findTestData('Mobile Input Data/SalesReturn').getValue('Reason_2', 1)

WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

println('Mentioned Reason in the mobile application properly inserted into the database')


//----------------------------------------
'Salable reason'
GlobalVariable.CaseSize = findTestData('Mobile Input Data/Invoice').getValue('CaseSize', 2)

GlobalVariable.CaseQty = findTestData('Mobile Input Data/SalesReturn').getValue('Case_1', 1)

QtyCase = (Integer.parseInt(GlobalVariable.CaseSize) * Integer.parseInt(GlobalVariable.CaseQty))

Piece_1 = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_1', 1)

Qty = (Integer.parseInt(Piece_1) + QtyCase)

KeywordUtil.logInfo('First return qty :' + Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_sales_return_detail where srd_srh_id = \'' + GlobalVariable.label2 + '\'  and SRD_Piece_Qty = \'' +
	Qty + '\'', ('columnNames') : ['SRD_Piece_Qty','SRD_Line_Value','SRD_APH_Id','SRD_Id']], FailureHandling.STOP_ON_FAILURE)


String SRD_Piece_Qty=GlobalVariable.data[0]

KeywordUtil.logInfo('SRD_Piece_Qty : '+SRD_Piece_Qty)


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

String SRD_Line_Value=GlobalVariable.data[1]

KeywordUtil.logInfo('SRD_Line_Value : '+SRD_Line_Value)


GridTotalAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_TotalAmt_2'))

Grid_TotalAmt = GridTotalAmt.replace('$ ', '')

WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), SRD_Line_Value, FailureHandling.STOP_ON_FAILURE)

println('Total Amount displayed properly in both database and salesreturn view popup grid screen')

WebUI.takeScreenshot()

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

String SRD_APH_Id=GlobalVariable.data[2]

KeywordUtil.logInfo('SRD_APH_Id : '+SRD_APH_Id)


GlobalVariable.label1 = SRD_APH_Id

println('Sku ID : ' + GlobalVariable.label1)

String SRD_Id=GlobalVariable.data[3]

KeywordUtil.logInfo('SRD_Id : '+SRD_Id)


GlobalVariable.label2 = SRD_Id

println('Return detail id: ' + GlobalVariable.label2)


'Reason 1 : Name validate between web and database'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)

String Sku_Name=GlobalVariable.data[0]

KeywordUtil.logInfo('SKU Name : '+Sku_Name)


WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'), 0)

PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name'))

WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name displayed properly in both database and salesreturn view popup screen')

WebUI.takeScreenshot()

'Validation for selected reason from mobile inserted in DB or not'
GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn').getValue('ProductName', 1)

WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name in the mobile application inserted into the database')

' Reason  validate between web and database'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from appdata_sales_return_reasons where SRR_SRD_id=\'' + GlobalVariable.label2 + '\'', ('columnNames') : ['SRR_Reason_Lov_Id']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason_Lov_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason id : '+SRR_Reason_Lov_Id)

GlobalVariable.label1 = SRR_Reason_Lov_Id

println('Reason ID :' + GlobalVariable.label1)


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_lovs where alov_id=\'' + GlobalVariable.label1 + '\'', ('columnNames') : ['ALOV_Name']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason : '+SRR_Reason)



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

'Selected mobile reason correctly inserted in DB or not'
GlobalVariable.S_ReasonType = findTestData('Mobile Input Data/SalesReturn').getValue('Reason_1', 1)

WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

println('Mentioned Reason in the mobile application properly inserted into the database')


//-------------------------------------
'Validation for Expired return'
Qty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_3', 1)


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_sales_return_detail where srd_srh_id = \'' + GlobalVariable.label2 + '\'  and SRD_Piece_Qty = \'' +
	Qty + '\' ', ('columnNames') : ['SRD_SRH_No','SRD_Piece_Qty','SRD_Line_Value','SRD_APH_Id','SRD_Id']], FailureHandling.STOP_ON_FAILURE)

String SRD_SRH_No=GlobalVariable.data[0]

KeywordUtil.logInfo('SRD_SRH_No : '+SRD_SRH_No)


PopUpView_SR_No = WebUI.getAttribute(findTestObject('Web Part/SalesReturnView/PopUp-SR_No'), 'value',
		FailureHandling.STOP_ON_FAILURE)

println(PopUpView_SR_No)

WebUI.verifyMatch(PopUpView_SR_No, SRD_SRH_No, false, FailureHandling.STOP_ON_FAILURE)

println('Sales Return number displayed properly in both database and salesreturn view popup screen')

WebUI.takeScreenshot()

String SRD_Piece_Qty=GlobalVariable.data[1]

KeywordUtil.logInfo('SRD_Piece_Qty : '+SRD_Piece_Qty)


GlobalVariable.DB_PieceQty = SRD_Piece_Qty

println(GlobalVariable.DB_PieceQty)

'Mobile enter piece validation'

'Product_1 Qty validation between Mobile and Database'
PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_3', 1)

WebUI.verifyEqual(GlobalVariable.DB_PieceQty, PieceQty, FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Entere Case and Piece Qty in the mobile apllication properly inserted into database!')

'Web Validation'

'Product_1 Qty validation between Web and Database'
WebUI.scrollToElement(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'), 0)

GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_PieceQty'))

WebUI.verifyEqual(GlobalVariable.DB_PieceQty, Integer.parseInt(GlobalVariable.PieceQty), FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Case and Piece Qty displayed properly in both database and SalesReturn popup view Screen')

WebUI.takeScreenshot()

'Web total amount'

'Product_1 Total validation between Web and Database'

String SRD_Line_Value=GlobalVariable.data[2]

KeywordUtil.logInfo('SRD_Line_Value : '+SRD_Line_Value)


GlobalVariable.Line_Value = SRD_Line_Value

GridTotalAmt = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_TotalAmt'))

Grid_TotalAmt = GridTotalAmt.replace('$ ', '')

WebUI.verifyEqual(Double.parseDouble(Grid_TotalAmt), GlobalVariable.Line_Value, FailureHandling.STOP_ON_FAILURE)

println('Product 1 : Total Amount displayed properly in both database and salesreturn view popup grid screen')

WebUI.takeScreenshot()

String SRD_APH_Id=GlobalVariable.data[2]

KeywordUtil.logInfo('SRD_APH_Id : '+SRD_APH_Id)

GlobalVariable.label = SRD_APH_Id

println('Sku ID : ' + GlobalVariable.label)

String SRD_Id=GlobalVariable.data[2]

KeywordUtil.logInfo('SRD_Id : '+SRD_Id)

GlobalVariable.label2 = SRD_Id

println('Return detail id: ' + GlobalVariable.label2)

'Reason 3 : Name validate between web and database'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)

String Sku_Name=GlobalVariable.data[0]

KeywordUtil.logInfo('SKU Name : '+Sku_Name)



'Reason 3 : Name validate between web and database'
PopUpView_Grid_SKU_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/PopUp-Grid_SKU_Name_2'))

WebUI.verifyMatch(Sku_Name, PopUpView_Grid_SKU_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name displayed properly in both database and salesreturn view popup screen')

WebUI.takeScreenshot()

'Validation for Selected Product from Mobile correctly inserted or not in DB '
GlobalVariable.S_Sku_Name = findTestData('Mobile Input Data/SalesReturn').getValue('ProductName', 1)

WebUI.verifyMatch(GlobalVariable.S_Sku_Name, Sku_Name, false, FailureHandling.STOP_ON_FAILURE)

println('SKU name in the mobile application inserted into the database')


WebUI.click(findTestObject('Object Repository/Web Part/SalesReturnView/Reason popup window_close btn'))

WebUI.click(findTestObject('Web Part/SalesReturnView/PopUp-Grid_Reason'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

'Reason3  validate between web and database'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select  * from appdata_sales_return_reasons where SRR_SRD_id=\'' + GlobalVariable.label2 + '\'', ('columnNames') : ['SRR_Reason_Lov_Id']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason_Lov_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason id : '+SRR_Reason_Lov_Id)

GlobalVariable.label1 = SRR_Reason_Lov_Id

println('Reason ID :' + GlobalVariable.label1)


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_lovs where alov_id=\'' + GlobalVariable.label1 + '\'', ('columnNames') : ['ALOV_Name']], FailureHandling.STOP_ON_FAILURE)

String SRR_Reason=GlobalVariable.data[0]

KeywordUtil.logInfo('Reason : '+SRR_Reason)


GlobalVariable.CaseQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-CaseQty'))

GlobalVariable.PieceQty = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-PieceQty'))

PieceQty = findTestData('Mobile Input Data/SalesReturn').getValue('Piece_3', 1)

WebUI.verifyEqual(Integer.parseInt(GlobalVariable.PieceQty), Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

println('Case and Piece Qty displayed properly in both database and SalesReturn popup reason view Screen')

WebUI.takeScreenshot()

reason_Name = WebUI.getText(findTestObject('Web Part/SalesReturnView/ReasonGrid-ReasonType'))

WebUI.verifyMatch(SRR_Reason, reason_Name, false, FailureHandling.STOP_ON_FAILURE)

println('Reason Name displayed properly in both database and SalesReturn popup reason view Screen')

'Validation for selected reason from mobile inserted in DB or not'
GlobalVariable.S_ReasonType = findTestData('Mobile Input Data/SalesReturn').getValue('Reason_3', 1)

WebUI.verifyMatch(GlobalVariable.S_ReasonType, SRR_Reason, false, FailureHandling.STOP_ON_FAILURE)

println('Mentioned Reason in the mobile application properly inserted into the database')


WebUI.closeBrowser()

