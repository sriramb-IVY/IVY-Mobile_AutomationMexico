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
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

//2nd DB
Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

queryString = (('select * from adm_retailer_request where arr_name= \'' + GlobalVariable.StoreName) + '\'')

KeywordUtil.logInfo('query=' + queryString)

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object data2 = recordSet.getObject('ARR_Name')

    KeywordUtil.logInfo(data2)

    Mobile.verifyMatch(data2, findTestData('Mobile Input Data/NewRetailer').getValue('StoreName', 1), false)

}

//3rd_DB ARTR_NAME,AAA_id,Acpd_id
queryString2 = (('select * from adm_retailer where artr_name= \'' + GlobalVariable.StoreName) + '\'')

KeywordUtil.logInfo(queryString2)

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object ARTR_Name_DB = recordSet2.getObject('ARTR_Name')

    KeywordUtil.logInfo(ARTR_Name_DB)

    Object ARTR_AAD_ID_DB = recordSet2.getObject('ARTR_AAD_Id')

    KeywordUtil.logInfo('AAD_id =' + ARTR_AAD_ID_DB)

    GlobalVariable.AAD_Id = ARTR_AAD_ID_DB

    Object ARTR_ACPD_ID_DB = recordSet2.getObject('ARTR_ACPD_Id')

    KeywordUtil.logInfo('Acpd_id =' + ARTR_ACPD_ID_DB)

    GlobalVariable.ACPD_Id = ARTR_ACPD_ID_DB
}

//4th DB aad id
queryString3 = (('select * from adm_address_detail where aad_id= \'' + GlobalVariable.AAD_Id) + '\'')

KeywordUtil.logInfo(queryString3)

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object aad_id = recordSet3.getObject('AAD_Id')

    KeywordUtil.logInfo('aad_id =' + aad_id)

    Object aad_Address1 = recordSet3.getObject('AAD_Address1')

    KeywordUtil.logInfo('aad_Address1 =' + aad_Address1)

    Mobile.verifyMatch(aad_Address1, findTestData('Mobile Input Data/NewRetailer').getValue('Address', 1), false)


}

//5th DB acpd id
queryString4 = (('select * from adm_contact_person_detail where acpd_id= \'' + GlobalVariable.ACPD_Id) + '\'')

KeywordUtil.logInfo(queryString4)

ResultSet rs4 = stm.executeQuery(queryString4)

def recordSet4 = rs4

while (recordSet4.next()) {
    Object acpd_id = recordSet4.getObject('ACPD_Id')

    KeywordUtil.logInfo('acpd_id =' + acpd_id)

    Object acpd_First_Name = recordSet4.getObject('ACPD_First_Name')

    KeywordUtil.logInfo('acpd_First_Name =' + acpd_First_Name)

    Mobile.verifyMatch(acpd_First_Name, findTestData('Mobile Input Data/NewRetailer').getValue('Contactperson_name', 1), false)

    Object acpd_Phone = recordSet4.getObject('ACPD_Phone')

    KeywordUtil.logInfo('ACPD_Phone =' + acpd_Phone)

    Mobile.verifyMatch(acpd_Phone, findTestData('Mobile Input Data/NewRetailer').getValue('PhoneNo', 1), false)

}