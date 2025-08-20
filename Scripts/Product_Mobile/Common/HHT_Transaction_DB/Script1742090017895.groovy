import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//Mobile.closeApplication(FailureHandling.OPTIONAL)
Mobile.delay(200, FailureHandling.STOP_ON_FAILURE)

queryString = (('select  top 2 * from hhttransactiondataqueuelist where UserId =  \'' + GlobalVariable.vanseller_user_id) + 
'\' order by 1 desc')

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + 
GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('FileProcessingMode')

    KeywordUtil.logInfo(data1)

    GlobalVariable.label = findTestData('Mobile Input Data/Attendance').getValue('DB_Verify', 1)

    Mobile.verifyMatch(data1, GlobalVariable.label, false)

    KeywordUtil.logInfo('HHT Transaction table have "FileProcessingMode" column and it shown as ' + data1)

    Object FileStatus = recordSet.getObject('FileStatus')

    KeywordUtil.logInfo(FileStatus)

    GlobalVariable.label = findTestData('Mobile Input Data/Attendance').getValue('DB_Verify', 2)

    Mobile.verifyMatch(FileStatus, GlobalVariable.label, false)

    KeywordUtil.logInfo('HHT Transaction table have "FileStatus" column and it shown as ' + FileStatus)

    Object TransactionId = recordSet.getObject('TransactionId')

    KeywordUtil.logInfo('TransactionId ' + TransactionId)
}

