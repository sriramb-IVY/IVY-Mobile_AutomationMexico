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


 
Connection connection = null

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/DB_Currentdate'), [:], FailureHandling.STOP_ON_FAILURE)

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

//queryString = (('Select * from appdata_sales_invoice_header where SIH_UserId = \'' + GlobalVariable.vanseller_user_id + '\' and CONVERT(varchar, SIH_Date, 23) like \'' +  GlobalVariable.First_Date ) + '\' order by 1 desc ')
//
//KeywordUtil.logInfo(queryString)
//
//if ((connection != null) && !(connection.isClosed())) {
//	connection.close()
//}
//
//connection = DriverManager.getConnection(conn)
//
//Statement stm = connection.createStatement()
//
//ResultSet rs = stm.executeQuery(queryString)
//
//def recordSet = rs
//
//while (recordSet.next()) {
//	String data1 = recordSet.getObject('sih_id')
//
//	KeywordUtil.logInfo(data1)
//
//	GlobalVariable.label = data1


queryString01 = ('delete from appdata_sales_invoice_detail where  SID_Created_By = \'' + GlobalVariable.vanseller_user_id + '\'')

KeywordUtil.logInfo(queryString01)

String conn01 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection01 = DriverManager.getConnection(conn01)

Statement stm01 = connection01.createStatement()

rs01 = stm01.executeUpdate(queryString01)

println(rs01)

queryString0 = ('delete from appdata_sales_invoice_scheme_detail  where  SISD_Created_By = \'' + GlobalVariable.vanseller_user_id + '\'')

KeywordUtil.logInfo(queryString0)

String conn0 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password



connection0 = DriverManager.getConnection(conn0)

Statement stm0 = connection0.createStatement()

rs0 = stm0.executeUpdate(queryString0)

println(rs0)

queryString1 = ('delete from appdata_sales_invoice_tax_detail where  SITD_Created_By = \'' + GlobalVariable.vanseller_user_id + '\' ')
KeywordUtil.logInfo(queryString1)

String conn1 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password


connection1 = DriverManager.getConnection(conn1)

Statement stm1 = connection1.createStatement()

rs1 = stm1.executeUpdate(queryString1)

println(rs1)

//queryString2 = (('delete from appdata_sales_invoice_discount_detail  where sidd_sih_id=\'' + GlobalVariable.label) + '\' ')
//
//String conn2 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password
//
//
//connection2 = DriverManager.getConnection(conn2)
//
//Statement stm2 = connection2.createStatement()
//
//int rs2 = stm2.execute(queryString2)
//
//println(rs2)

//queryString3 = (('delete from AppData_Sales_Invoice_Scheme_Detail_Credit  where sisdc_sih_id=\'' + GlobalVariable.label) + '\' ')
//KeywordUtil.logInfo(queryString3)
//
//String conn3 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password
//
//connection3 = DriverManager.getConnection(conn3)
//
//Statement stm3 = connection3.createStatement()
//
//rs3 = stm3.executeUpdate(queryString3)
//
//println(rs3)

String SIH_Source = 'Mobile'

queryString4 = ('delete from appdata_sales_invoice_header where SIH_UserId = \'' + GlobalVariable.vanseller_user_id + '\' and SIH_Source = \'' + SIH_Source + '\' ')

KeywordUtil.logInfo(queryString4)

String conn4 = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password


connection4 = DriverManager.getConnection(conn4)

Statement stm4 = connection4.createStatement()

 rs4 = stm4.executeUpdate(queryString4)

println(rs4)



