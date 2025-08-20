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


GlobalVariable.label1='413'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1 * from AppData_Asset_Tracking_Header where ath_user_id=\'' + GlobalVariable.vanseller_user_id +
	'\'  and ath_artr_id =\'' + GlobalVariable.label1 + '\' order by 1 desc', ('columnNames') : ['ATH_Id']], FailureHandling.STOP_ON_FAILURE)

String Headerid=GlobalVariable.data[0]

KeywordUtil.logInfo('Header : '+Headerid)

GlobalVariable.ATH_Id = Headerid


println(GlobalVariable.ATH_Id)


Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from AppData_Asset_Tracking_details where atd_ath_id=\'' + GlobalVariable.ATH_Id + '\'', ('columnNames') : ['ATD_Reason_Lov_Id']], FailureHandling.STOP_ON_FAILURE)

String Asset_Reason_id=GlobalVariable.data[0]

KeywordUtil.logInfo('Asset Reason : '+Asset_Reason_id)

GlobalVariable.Reason_id = Asset_Reason_id



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_lovs where alov_id= \'' + GlobalVariable.Reason_id + '\'', ('columnNames') : ['ALOV_Code']], FailureHandling.STOP_ON_FAILURE)

String ALOV_Code=GlobalVariable.data[0]

KeywordUtil.logInfo('LOV Reason : '+ALOV_Code)



//----------------------------------------------------
//4TH
GlobalVariable.label1='413'



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select top 1* from ADM_Asset_Transfer_Request where ATR_ARTR_Id= \'' + GlobalVariable.label1 + '\' order by  1 desc', ('columnNames') : ['ATR_Serial_No','ATR_AP_Description','ATR_Rent']], FailureHandling.STOP_ON_FAILURE)

String ATR_Serialno=GlobalVariable.data[0]

KeywordUtil.logInfo('Serial No : '+ATR_Serialno)


Mobile.verifyMatch(ATR_Serialno, GlobalVariable.Asset_SR_No, false)

String Description=GlobalVariable.data[1]

KeywordUtil.logInfo(Description)

Desc_Asset = findTestData('Mobile Input Data/NewRetailer').getValue('Description', 1)

Mobile.verifyMatch(Description, Desc_Asset, false)

String Rent=GlobalVariable.data[2]

KeywordUtil.logInfo('Rent =' + Rent)

Rent_Tb = findTestData('Mobile Input Data/NewRetailer').getValue('Rental', 1)


GlobalVariable.label = '812'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from adm_posm where ap_id= \'' + GlobalVariable.label + '\'', ('columnNames') : ['AP_Description']], FailureHandling.STOP_ON_FAILURE)

String POSM_Description=GlobalVariable.data[0]

KeywordUtil.logInfo(' Desc : '+POSM_Description)



