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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


label=findTestData('Mobile Input Data/SO_Batch').getValue('Order_No', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_sales_invoice_header where SIH_Sales_Order_Id = \'' + label + '\'', ('columnNames') : ['SIH_Id']], FailureHandling.STOP_ON_FAILURE)

String SalesID=GlobalVariable.data[0]

println(SalesID)

GlobalVariable.label=SalesID

Product_code=  findTestData('Mobile Input Data/SO_Batch').getValue('Free_SKU2', 5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select  * from ADM_Product_Hierarchy where APH_Code = \'' + Product_code + '\'  ', ('columnNames') : ['APH_Id']], FailureHandling.STOP_ON_FAILURE)

String Product_id=GlobalVariable.data[0]

println(Product_id)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select  * from appdata_sales_invoice_scheme_detail where SISD_SIH_Id = \'' + GlobalVariable.label + '\'  and SISD_APH_Id = \'' +
	
	Product_id + '\'  order by 1 desc ', ('columnNames') : ['SISD_Qty','SISD_AS_Id']], FailureHandling.STOP_ON_FAILURE)

String Freeqty=GlobalVariable.data[0]

println(Freeqty)

ST_Qty=findTestData('Mobile Input Data/SO_Batch').getValue('S1_Free_Qty', 5)

Mobile.verifyMatch(Freeqty, ST_Qty, false, FailureHandling.STOP_ON_FAILURE)


String Schemeid=GlobalVariable.data[1]

println(Schemeid)

Active='1'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select  * from ADM_Scheme where AS_Id = \'' + Schemeid + '\'  and AS_Isactive = \'' +
	
	Active + '\'  order by 1 desc ', ('columnNames') : ['AS_Description']], FailureHandling.STOP_ON_FAILURE)


String SchemeName=GlobalVariable.data[0]

println(SchemeName)

ST_Scheme=findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 5)

Mobile.verifyMatch(SchemeName, ST_Scheme, false, FailureHandling.STOP_ON_FAILURE)
