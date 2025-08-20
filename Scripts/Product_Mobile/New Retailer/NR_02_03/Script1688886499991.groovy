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

//GlobalVariable.StoreName = 'VANSE69'
GlobalVariable.StoreName = findTestData('Mobile Input data/NewRetailer').getValue('StoreName', 1)

Store_Name = GlobalVariable.StoreName

println(Store_Name)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Add'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Store_Name_Input'))

WebUI.setText(findTestObject('Web Part/New Retailer/Retailer_Add/Store_Name_Input'), Store_Name)

WebUI.waitForElementVisible(findTestObject('Web Part/New Retailer/Retailer_Add/Store_Value_Click'), 10)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Store_Value_Click'))

WebUI.delay(2)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/View_btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)

WebUI.delay(2)

S_Name = WebUI.getAttribute(findTestObject('Web Part/New Retailer/Retailer_Add/Name_Input_in_view_scrn'), 
    'value')

KeywordUtil.logInfo(S_Name)

WebUI.verifyMatch(S_Name, Store_Name, false)

WebUI.scrollToElement(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Sub Channel'), 
    20)

WebUI.click(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Sub Channel'))

GlobalVariable.label = findTestData('Mobile Input Data/NewRetailer').getValue('SubChannel', 1)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Location_Val'))

Sub_Channel_Name = WebUI.getAttribute(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Sub Channel'), 
    'value')

KeywordUtil.logInfo(Sub_Channel_Name)

WebUI.click(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Route'))

GlobalVariable.label = findTestData('Mobile Input Data/NewRetailer').getValue('Route', 1)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Location_Val'))

Route_Name = WebUI.getAttribute(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Route'), 
    'value')

KeywordUtil.logInfo(Route_Name)

WebUI.click(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Location'))

GlobalVariable.label = findTestData('Mobile Input Data/NewRetailer').getValue('Location_val', 1)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Location_Val'))

WebUI.delay(2)

WebUI.takeScreenshot()

Distributor_Name = WebUI.getText(findTestObject('Web Part/New Retailer/Retailer_Add/Distributor_Field'))

KeywordUtil.logInfo(Distributor_Name)

WebUI.verifyMatch(Distributor_Name, findTestData('Mobile Input Data/NewRetailer').getValue('Distributor', 1), false)

//WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Flex39_Inp'))
//
//WebUI.setText(findTestObject('Web Part/New Retailer/Retailer_Add/Flex39_Inp'), findTestData('Mobile Input Data/NewRetailer').getValue(
//        'Flex', 1))
//
//WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Flex40_Inp'))
//
//WebUI.setText(findTestObject('Web Part/New Retailer/Retailer_Add/Flex40_Inp'), findTestData('Mobile Input Data/NewRetailer').getValue(
//        'Flex', 1))
//
//WebUI.delay(2)
WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Address_1_Input'))

WebUI.setText(findTestObject('Web Part/New Retailer/Retailer_Add/Address_1_Input'), findTestData('Mobile Input Data/NewRetailer').getValue(
        'Address', 1))

WebUI.delay(2)

First_Name = WebUI.getAttribute(findTestObject('Web Part/New Retailer/Retailer_Add/First_name_Input'), 'value')

KeywordUtil.logInfo(First_Name)

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Phone_No_input'))

WebUI.setText(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Phone_Input'), findTestData(
        'Mobile Input Data/NewRetailer').getValue('PhoneNo', 1))

Phone_Number = WebUI.getAttribute(findTestObject('Object Repository/Web Part/New Retailer/Retailer_Add/Phone_Input'), 
    'value')

KeywordUtil.logInfo(Phone_Number)

Con_Name_Mobile = findTestData('Mobile Input Data/NewRetailer').getValue('Contactperson_name', 1)

Route_Mobile = findTestData('Mobile Input Data/NewRetailer').getValue('Route', 1)

Channel_Mobile = findTestData('Mobile Input Data/NewRetailer').getValue('Channel', 1)

Subchannel_Mobile = findTestData('Mobile Input Data/NewRetailer').getValue('SubChannel', 1)

Phone_no_Mobile = findTestData('Mobile Input Data/NewRetailer').getValue('PhoneNo', 1)

WebUI.verifyMatch(GlobalVariable.StoreName, S_Name, false)

WebUI.verifyMatch(Con_Name_Mobile, First_Name, false)

WebUI.verifyMatch(Route_Mobile, Route_Name, false)

WebUI.verifyMatch(Subchannel_Mobile, Sub_Channel_Name, false)

WebUI.verifyMatch(Phone_no_Mobile, Phone_Number, false)

WebUI.delay(2)

WebUI.takeScreenshot()

WebUI.click(findTestObject('Web Part/New Retailer/Retailer_Add/Approve_btn'))

WebUI.acceptAlert()

WebUI.takeScreenshot()

WebUI.delay(30)

//Connection connection = null
//
//url = findTestData('DB Credentials/Config1').getValue('URL', 1)
//
//dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)
//
//username = findTestData('DB Credentials/Config1').getValue('username', 1)
//
//password = findTestData('DB Credentials/Config1').getValue('password', 1)
//
//queryString = (('select * from adm_retailer_request where arr_name= \'' + GlobalVariable.StoreName) + '\'')
//
//KeywordUtil.logInfo('query=' + queryString)
//
//String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password
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
//    Object data2 = recordSet.getObject('ARR_Name')
//
//    KeywordUtil.logInfo(data2)
//}
//
////3rd_DB ARTR_NAME,AAA_id,Acpd_id
//queryString2 = (('select * from adm_retailer where artr_name= \'' + GlobalVariable.StoreName) + '\'')
//
//KeywordUtil.logInfo(queryString2)
//
//ResultSet rs2 = stm.executeQuery(queryString2)
//
//def recordSet2 = rs2
//
//while (recordSet2.next()) {
//    Object ARTR_Name_DB = recordSet2.getObject('ARTR_Name')
//
//    KeywordUtil.logInfo(ARTR_Name_DB)
//
//    Object ARTR_AAD_ID_DB = recordSet2.getObject('ARTR_AAD_Id')
//
//    KeywordUtil.logInfo('AAD_id =' + ARTR_AAD_ID_DB)
//
//    GlobalVariable.AAD_Id = ARTR_AAD_ID_DB
//
//    Object ARTR_ACPD_ID_DB = recordSet2.getObject('ARTR_ACPD_Id')
//
//    KeywordUtil.logInfo('Acpd_id =' + ARTR_ACPD_ID_DB)
//
//    GlobalVariable.ACPD_Id = ARTR_ACPD_ID_DB
//}
//
////4th DB aad id
//queryString3 = (('select * from adm_address_detail where aad_id= \'' + GlobalVariable.AAD_Id) + '\'')
//
//KeywordUtil.logInfo(queryString3)
//
//ResultSet rs3 = stm.executeQuery(queryString3)
//
//def recordSet3 = rs3
//
//while (recordSet3.next()) {
//    Object aad_id = recordSet3.getObject('AAD_Id')
//
//    KeywordUtil.logInfo('aad_id =' + aad_id)
//}
//
////5th DB acpd id
//queryString4 = (('select * from adm_contact_person_detail where acpd_id= \'' + GlobalVariable.ACPD_Id) + '\'')
//
//KeywordUtil.logInfo(queryString4)
//
//ResultSet rs4 = stm.executeQuery(queryString4)
//
//def recordSet4 = rs4
//
//while (recordSet4.next()) {
//    Object acpd_id = recordSet4.getObject('ACPD_Id')
//
//    KeywordUtil.logInfo('acpd_id =' + acpd_id)
//}
//
//Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

//Mobile.callTestCase(findTestCase('Product_Mobile/New Retailer/NR_04'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Test Cases/Product_Mobile/DB/New_Retailer_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

