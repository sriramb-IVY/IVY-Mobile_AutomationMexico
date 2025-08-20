import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//GlobalVariable.ordernumber = '017200533230520232233570017'
GlobalVariable.ordernumber = findTestData('Mobile Input Data/Todays Plan').getValue('SO_Number', 1)

KeywordUtil.logInfo(GlobalVariable.ordernumber)

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = (('select * from appdata_order_header where oh_order_id= \'' + GlobalVariable.ordernumber) + '\'')

KeywordUtil.logInfo(queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

if ((connection != null) && !(connection.isClosed())) {
    connection.close()
}

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    String data1 = recordSet.getObject('OH_Id')

    KeywordUtil.logInfo(data1)

    String OH_Order_Id = recordSet.getObject('OH_Order_Id')

    KeywordUtil.logInfo(OH_Order_Id)

    GlobalVariable.input = data1

    Mobile.verifyMatch(OH_Order_Id, GlobalVariable.ordernumber, false)
}

queryString01 = (('select * from appdata_order_detail where od_oh_id= \'' + GlobalVariable.input) + '\'')

KeywordUtil.logInfo(queryString01)

String conn01 = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + 
password

if ((connection != null) && !(connection.isClosed())) {
    connection.close()
}

connection01 = DriverManager.getConnection(conn01)

Statement stm01 = connection01.createStatement()

ResultSet rs1 = stm01.executeQuery(queryString01)

def recordSet1 = rs1

while (recordSet1.next()) {
    String data2 = recordSet1.getObject('OD_Line_Value')

    KeywordUtil.logInfo(data2)

    String Order_Id_2 = recordSet1.getObject('OD_OH_Order_Id')

    KeywordUtil.logInfo(Order_Id_2)

    double price = Double.parseDouble(data2)
	
	ExpectedPrice = findTestData('Mobile Input Data/Todays Plan').getValue('PriceFromMobile', 1)

	Mobile.verifyEqual(price, ExpectedPrice, FailureHandling.STOP_ON_FAILURE)
}

