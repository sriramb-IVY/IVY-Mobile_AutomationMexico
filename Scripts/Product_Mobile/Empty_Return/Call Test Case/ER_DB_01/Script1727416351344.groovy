import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import javax.management.Descriptor as Descriptor
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

GlobalVariable.button=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)

queryString = (('select * from ADM_Product_Hierarchy where APH_Name=\'' + GlobalVariable.button) + '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	Object Product_Id1 = recordSet.getObject('APH_Id')

	KeywordUtil.logInfo('Sku id = ' + Product_Id1)
	
	GlobalVariable.label = Product_Id1

}
else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
'Prodcut id get'

GlobalVariable.button=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 2)
queryString2 = (('select * from ADM_Product_Hierarchy where APH_Name=\'' + GlobalVariable.button) + '\'')

println(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
	Object Product_Id2 = recordSet2.getObject('APH_Id')
	
	KeywordUtil.logInfo('Product Id =' + Product_Id2)
	GlobalVariable.label1 = Product_Id2
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

'Salable SKU Id'

GlobalVariable.button=findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 1)
queryString3 = (('select * from ADM_Product_Hierarchy where APH_Name=\'' + GlobalVariable.button) + '\'')


println(queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

if (recordSet3.next()) {
	Object Product_Id3 = recordSet3.getObject('APH_Id')
	
	KeywordUtil.logInfo('Product Id3 =' + Product_Id3)
	GlobalVariable.label2 = Product_Id3
	
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}


GlobalVariable.button=findTestData('Mobile Input Data/Empty_Return').getValue('Salable_SKU_Name', 2)

queryString4 = (('select * from ADM_Product_Hierarchy where APH_Name=\'' + GlobalVariable.button) + '\'')

KeywordUtil.logInfo('Query 4: '+queryString4)

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

if (recordSet4.next()) {
	Object Product_Id4 = recordSet4.getObject('APH_Id')
	
	KeywordUtil.logInfo('Product Id4 =' + Product_Id4)
	
	GlobalVariable.input = Product_Id4
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}

String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

		String Sheetname = 'EmptyReturn_Validation'

		workbook01 = ExcelKeywords.getWorkbook(exlpath)

		sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

		ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, GlobalVariable.label)
		
		ExcelKeywords.setValueToCellByIndex(sheet1, 2, 3, GlobalVariable.label1)
	
		ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, GlobalVariable.label2)
		
		ExcelKeywords.setValueToCellByIndex(sheet1, 2, 1, GlobalVariable.input)
	
	
		ExcelKeywords.saveWorkbook(exlpath, workbook01)
