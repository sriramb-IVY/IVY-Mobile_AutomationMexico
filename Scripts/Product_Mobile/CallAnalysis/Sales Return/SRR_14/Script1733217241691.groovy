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
import org.testng.Assert;
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable



String pattern = 'yyyy-MM-dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

println(currentdate)

DB_Currentdate = (currentdate + ' 00:00:00.000')

Connection connection = null


GlobalVariable.SR_Amt = findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Sales_Return_Amount', 1)

queryString = (((('select* from appdata_sales_return_header where srh_Date = \'' + DB_Currentdate) + '\'  and SRH_Amount = \'') +
GlobalVariable.SR_Amt) + '\' and SRH_UserId=1589  order by 1 desc ')

KeywordUtil.logInfo('Query : '+queryString)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	Object SRH_ID = recordSet.getObject('SRH_ID')

	GlobalVariable.label2 = SRH_ID

	KeywordUtil.logInfo('Header Id: '+GlobalVariable.label2)

	Object SRH_Amount = recordSet.getObject('SRH_Amount')

	Object SRH_No = recordSet.getObject('SRH_No')

	GlobalVariable.SR_No = SRH_No

	KeywordUtil.logInfo('Sales return Number : ' + GlobalVariable.SR_No)
	
}
else {
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()
}


//Sales Return detail table
queryString1 = (('select  * from appdata_sales_return_detail where srd_srh_id=\'' + GlobalVariable.label2) + '\'')

KeywordUtil.logInfo('Query 2: '+queryString1)

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

if (recordSet1.next()) {
	Object SRD_SRH_No = recordSet1.getObject('SRD_SRH_No')

	KeywordUtil.logInfo('Sales Return no : ' + SRD_SRH_No)

	Object SRD_Piece_Qty = recordSet1.getObject('SRD_Piece_Qty')

	GlobalVariable.DB_PieceQty = SRD_Piece_Qty

	KeywordUtil.logInfo('Piece qty : '+GlobalVariable.DB_PieceQty)

	Object SRD_APH_Id = recordSet1.getObject('SRD_APH_Id')
	
	String product_id=SRD_APH_Id

	 GlobalVariable.ProductName = product_id
	
		KeywordUtil.logInfo('Sku ID : ' + GlobalVariable.ProductName)
}


else {
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()
}

Mobile.comment('Validation for replacement details table')


GlobalVariable.label=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Qty', 1)

queryString2 = (('Select * from  AppData_Sales_Return_Replacement_Detail where SRRD_SRH_Id=\'' + GlobalVariable.label2) + '\' and SRRD_Qty = \'' + GlobalVariable.label + '\'')

KeywordUtil.logInfo(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
	
	Object ReturnSku_id = recordSet2.getObject('SRRD_Return_APH_Id')
	
	String SKU_ID=ReturnSku_id

	Object Replace_price = recordSet2.getObject('SRRD_Price')
	
	String Rep_price=Replace_price
	
	Object Replace_Value = recordSet2.getObject('SRRD_Value')
	
	String Rep_value=Replace_Value
	
	Object Replace_Sku_id = recordSet2.getObject('SRRD_APH_Id')
	
	
	 GlobalVariable.label = Replace_Sku_id

	
			KeywordUtil.logInfo('Sku ID : ' + GlobalVariable.label)
	
	
	Mobile.verifyMatch(GlobalVariable.ProductName, SKU_ID, false, FailureHandling.STOP_ON_FAILURE)
	
	KeywordUtil.logInfo('Retuurn SKU name  inserted into the database')
	
	price=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Price', 1)
	
	Mobile.verifyEqual(Double.parseDouble(Rep_price), Double.parseDouble(price), FailureHandling.STOP_ON_FAILURE)

	value=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Value', 1)
	
	Mobile.verifyEqual(Double.parseDouble(Rep_value), Double.parseDouble(value), FailureHandling.STOP_ON_FAILURE)
	
}

else {
	
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()

	
}

Mobile.comment('validate replacement sku ')

queryString3 = (('Select  * from ADM_Product_hierarchy where APH_Id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo('Query3 : '+queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

if (recordSet3.next()) {
	Object APH_Name = recordSet3.getObject('APH_Name')

	KeywordUtil.logInfo('Product Name :' + APH_Name)
	
	Mobile.verifyMatch(APH_Name, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 1), false, FailureHandling.STOP_ON_FAILURE)
}

else {
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()

}

'2nd replacement sku'

Mobile.comment('Validation for replacement details table for second product')


GlobalVariable.label=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Qty', 2)

queryString4 = (('Select * from  AppData_Sales_Return_Replacement_Detail where SRRD_SRH_Id=\'' + GlobalVariable.label2) + '\' and SRRD_Qty = \'' + GlobalVariable.label + '\'')

KeywordUtil.logInfo(queryString4)

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

if (recordSet4.next()) {
	
	Object ReturnSku_id = recordSet4.getObject('SRRD_Return_APH_Id')
	
	String SKU_ID=ReturnSku_id

	Object Replace_price = recordSet4.getObject('SRRD_Price')
	
	String Rep_price=Replace_price
	
	Object Replace_Value = recordSet4.getObject('SRRD_Value')
	
	String Rep_value=Replace_Value
	
	Object Replace_Sku_id = recordSet4.getObject('SRRD_APH_Id')
	
	GlobalVariable.label = Replace_Sku_id
	
			KeywordUtil.logInfo('Sku ID : ' + GlobalVariable.label)
	
	
	Mobile.verifyMatch(GlobalVariable.ProductName, SKU_ID, false, FailureHandling.STOP_ON_FAILURE)
	
	KeywordUtil.logInfo('Retuurn SKU name  inserted into the database')
	
	price=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Price', 2)
	
	Mobile.verifyEqual(Double.parseDouble(Rep_price), Double.parseDouble(price), FailureHandling.STOP_ON_FAILURE)

	value=findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_Value', 2)
	
	Mobile.verifyEqual(Double.parseDouble(Rep_value), Double.parseDouble(value), FailureHandling.STOP_ON_FAILURE)
	
}

else {
	
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()

	
}

Mobile.comment('validate replacement sku ')

queryString5 = (('Select  * from ADM_Product_hierarchy where APH_Id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo('Query5 : '+queryString5)

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

if (recordSet5.next()) {
	Object APH_Name = recordSet5.getObject('APH_Name')

	KeywordUtil.logInfo('Product Name :' + APH_Name)
	
	Mobile.verifyMatch(APH_Name, findTestData('Mobile Input Data/SalesReturn_Replacement').getValue('Replacement_SKU_Name', 2), false, FailureHandling.STOP_ON_FAILURE)
}

else {
	
	KeywordUtil.logInfo('Data not inserted in DB table')
	
	Assert.fail()


}

exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

		Sheetname = 'SalesReturn_Replacement'

		workbook01 = ExcelKeywords.getWorkbook(exlpath)

		sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

		ExcelKeywords.setValueToCellByIndex(sheet1, 2, 5, GlobalVariable.SR_No)


		ExcelKeywords.saveWorkbook(exlpath, workbook01)
