import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//GlobalVariable.StoreName = 'RJRet36'
//
//GlobalVariable.ACPD_Id ='2216'
//
//GlobalVariable.AAD_Id ='3863'

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = (('Delete from adm_retailer_request where arr_name= \'' + GlobalVariable.StoreName) + '\' and ARR_User_Id = \'' + GlobalVariable.vanseller_user_id + '\'')

KeywordUtil.logInfo('query='+ queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

boolean rs = stm.execute(queryString)



//3rd_DB ARTR_NAME,AAA_id,Acpd_id
queryString2 = (('Delete from adm_retailer where artr_name= \'' + GlobalVariable.StoreName) + '\'')

KeywordUtil.logInfo(queryString2)

boolean rs2 = stm.execute(queryString2)



//4th DB aad id
queryString3 = (('Delete from adm_address_detail where aad_id= \'' + GlobalVariable.AAD_Id) + '\'')

KeywordUtil.logInfo(queryString3)

boolean rs3 = stm.execute(queryString3)


//5th DB acpd id
queryString4 = (('Delete from adm_contact_person_detail where acpd_id= \'' + GlobalVariable.ACPD_Id) + '\'')

KeywordUtil.logInfo(queryString4)

boolean rs4 = stm.execute(queryString4)




