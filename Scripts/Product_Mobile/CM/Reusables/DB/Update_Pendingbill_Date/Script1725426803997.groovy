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
import org.junit.Assert as Assert

Retailer_Name = findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', Credit_Index)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

queryString = (('select * from adm_retailer where ARTR_Name = \'' + Retailer_Name) + '\' and ARTR_Isactive = 1')

rs = stm.executeQuery(queryString)

KeywordUtil.logInfo(queryString)

def recordSet = rs

if (recordSet.next()) {
    String retailer_id = recordSet.getObject('ARTR_Id')

    KeywordUtil.logInfo('Retailer ID is : ' + retailer_id)

    GlobalVariable.label = retailer_id
}


connection = DriverManager.getConnection(conn)

stm = connection.createStatement()

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)

CurrentDate = GlobalVariable.sDate

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_OtherDate(past,future)'), [('Other_Date') : Datee ], FailureHandling.STOP_ON_FAILURE)

YesterdayDate = GlobalVariable.sDate

queryString = (('Update AppData_Pending_Bills set PB_SIH_Date = \'' + YesterdayDate + '\' where PB_ARTR_Id = \'' + GlobalVariable.label) + '\' and PB_Isactive = 1 ')

//and PB_SIH_Date  = \'' + CurrentDate + '\'

KeywordUtil.logInfo(queryString)

rs = stm.execute(queryString)





