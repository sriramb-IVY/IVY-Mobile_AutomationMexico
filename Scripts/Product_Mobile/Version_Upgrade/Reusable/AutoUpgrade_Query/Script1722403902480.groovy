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

//Connection connection4 = null
//
//url4 = findTestData('DB Credentials/Config1').getValue('URL', 1)
//
//dbname4 = findTestData('DB Credentials/Config1').getValue('dbname', 1)
//
//username4 = findTestData('DB Credentials/Config1').getValue('username', 1)
//
//password4 = findTestData('DB Credentials/Config1').getValue('password', 1)

//Version = '11554686'

queryString4 = (('Select * from ADM_HHT_Version where  AHV_Version_Code =\'' + GlobalVariable.VersionCode) + '\'order by 1 desc')

KeywordUtil.logInfo(queryString4)

//String conn4 = (((((('jdbc:sqlserver://' + url4) + ';databaseName=') + dbname4) + ';user=') + username4) + ';password=') + password4

String conn4 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection4 = DriverManager.getConnection(conn4)

Statement stm4 = connection4.createStatement()

ResultSet rs4 = stm4.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
    Object OMH_Meter_Start = recordSet4.getObject('AHV_Id')

    println(GlobalVariable.label = OMH_Meter_Start)
}

//Connection connection1 = null
//
//url1 = findTestData('DB Credentials/Config1').getValue('URL', 1)
//
//dbname1 = findTestData('DB Credentials/Config1').getValue('dbname', 1)
//
//username1 = findTestData('DB Credentials/Config1').getValue('username', 1)
//
//password1 = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString1 = (('Update  ADM_HHT_Version_Upgrade set AHVU_Isactive=0 where AHVU_AHV_Id=\'' + GlobalVariable.label) + '\'')

KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

//ResultSet rs1 = stm1.executeUpdate(queryString1)
result = stm1.executeUpdate(queryString1)

