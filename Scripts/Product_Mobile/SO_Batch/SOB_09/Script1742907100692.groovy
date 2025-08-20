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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from appdata_sales_invoice_header where SIH_Sales_Order_Id = \'' + label + '\'', ('columnNames') : ['SIH_No','SIH_Discount','SIH_Tax','SIH_Id']], FailureHandling.STOP_ON_FAILURE)

String SIH_no=GlobalVariable.data[0]

KeywordUtil.logInfo(SIH_no)

String Discount=GlobalVariable.data[1]

KeywordUtil.logInfo(Discount)

String Tax=GlobalVariable.data[2]

KeywordUtil.logInfo(Tax)

String SalesID=GlobalVariable.data[3]

KeywordUtil.logInfo(SalesID)

GlobalVariable.label=SalesID


String sheet_name = 'SO_Batch'

String file_name = 'Mobile Input data'

ArrayList<String> Batch_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Batch_Name1')

for (int i = 0; i < 5; i++) {
	Scheme_Index = (i + 1)

	KeywordUtil.logInfo('index' + Scheme_Index)

	Batch_code= findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Batch_Name1', Scheme_Index)

	Active='1'

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select  * from ADM_Product_Batch where APB_Code = \'' + Batch_code + '\'  and APB_IsActive = \'' +Active + '\'  order by 1 desc', ('columnNames') : ['APB_Id']], FailureHandling.STOP_ON_FAILURE)

	String Batch_id=GlobalVariable.data[0]

	KeywordUtil.logInfo(Batch_id)

	GlobalVariable.label1=Batch_id

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select  * from appdata_sales_invoice_detail where SID_SIH_Id = \'' + GlobalVariable.label + '\'  and SID_APB_Id = \'' +

	GlobalVariable.label1 + '\'  order by 1 desc ', ('columnNames') : ['SID_Piece_Qty','SID_APH_Id']], FailureHandling.STOP_ON_FAILURE)

	String QTY=GlobalVariable.data[0]

	KeywordUtil.logInfo(QTY)

	ST_Qty= findTestData('Mobile Input Data/SO_Batch').getValue('Buy_Quantity1', Scheme_Index)

	Mobile.verifyMatch(QTY, ST_Qty, false, FailureHandling.STOP_ON_FAILURE)

	String Productid=GlobalVariable.data[1]

	KeywordUtil.logInfo(Productid)

	DBProductID=Productid

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'Select * from ADM_Product_Hierarchy where APH_id= \'' + DBProductID + '\'', ('columnNames') : ['APH_Name']], FailureHandling.STOP_ON_FAILURE)

	String Productname=GlobalVariable.data[0]

	KeywordUtil.logInfo(Productname)

	ST_PName= findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', Scheme_Index)

	Mobile.verifyMatch(Productname, ST_PName, false, FailureHandling.STOP_ON_FAILURE)

}




