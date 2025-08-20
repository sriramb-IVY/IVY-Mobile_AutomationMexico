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


Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)



queryString = (((('select * from Appdata_Van_Stock_Reconciliation_Header where VSRH_User_Id=\'' + GlobalVariable.vanseller_user_id) +
'\'  and VSRH_Date =\'') + GlobalVariable.label1) + '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
	Object Van_Stock_id = recordSet.getObject('VSRH_Id')

	KeywordUtil.logInfo('Van_Stock_id = ' + Van_Stock_id)

	GlobalVariable.button = Van_Stock_id
}
else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
'Prodcut id get'

GlobalVariable.label=findTestData('Mobile Input Data/Empty_Return').getValue('SKU_Name', 1)
queryString2 = (('select * from ADM_Product_Hierarchy where APH_Name=\'' + GlobalVariable.label) + '\'')

println(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
	Object Product_Id = recordSet2.getObject('APH_Id')
	KeywordUtil.logInfo('Product Id =' + Product_Id)
	GlobalVariable.label = Product_Id
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
'Van stock allocatiom detail DB'

queryString2 = (('select * from Appdata_Van_Stock_Reconciliation_Detail where VSRD_VSRH_Id=\'' + GlobalVariable.button) + '\'')

println(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
	Object Stock_detail = recordSet2.getObject('VSRD_Unloaded_Qty')

	KeywordUtil.logInfo('Stock_detail =' + Stock_detail)

	GlobalVariable.label = Stock_detail
}

else {
	'If data is empty in the DB, then the flow must fail'
	Assert.fail()
}
