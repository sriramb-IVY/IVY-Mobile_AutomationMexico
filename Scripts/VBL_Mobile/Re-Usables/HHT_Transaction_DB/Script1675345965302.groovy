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

Mobile.delay(180, FailureHandling.STOP_ON_FAILURE)

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

queryString = 'select  top 1 * from hhttransactiondataqueuelist where UserId = 297 order by 1 desc'

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data1 = recordSet.getObject('FileProcessingMode')

    KeywordUtil.logInfo(data1)

    GlobalVariable.label = findTestData('VBL_Mobile Input Data/Attendance').getValue('DB_Verify', 1)

    Mobile.verifyMatch(data1, GlobalVariable.label, false)

    KeywordUtil.logInfo('HHT Transaction table have "FileProcessingMode" column and it shown as ' + data1)

    Object FileStatus = recordSet.getObject('FileStatus')

    KeywordUtil.logInfo(FileStatus)

    GlobalVariable.label = findTestData('VBL_Mobile Input Data/Attendance').getValue('DB_Verify', 2)

    Mobile.verifyMatch(FileStatus, GlobalVariable.label, false)

    KeywordUtil.logInfo('HHT Transaction table have "FileStatus" column and it shown as ' + FileStatus)
}

