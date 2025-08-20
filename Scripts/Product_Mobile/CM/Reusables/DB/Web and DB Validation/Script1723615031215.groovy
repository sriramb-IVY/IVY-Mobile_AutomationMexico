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
import io.appium.java_client.android.AndroidDriver as AndroidDriver
//import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.nativekey.AndroidKey as AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent as KeyEvent
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver

String sheet_name = 'CreditManagement'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer_Name')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Retailer Attribute'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/RetailerAttribute/EnterDistributorFilterField'), 0)

WebUI.click(findTestObject('Web Part/RetailerAttribute/EnterDistributorFilterField'), FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('Mobile Input Data/CreditManagement').getValue('Branch_Name', 1)

WebUI.setText(findTestObject('Web Part/RetailerAttribute/EnterDistributorFilterField'), GlobalVariable.label)

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Web Part/RetailerAttribute/Span and b_tag (distributor)'), 50)

WebUI.click(findTestObject('Web Part/RetailerAttribute/Span and b_tag (distributor)'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Web Part/RetailerAttribute/Channel_Name_Filter_Field'), 100)

WebUI.click(findTestObject('Web Part/RetailerAttribute/Channel_Name_Filter_Field'), FailureHandling.OPTIONAL)

GlobalVariable.label = findTestData('Mobile Input Data/CreditManagement').getValue('Sub_Channel', 1)

WebUI.setText(findTestObject('Web Part/RetailerAttribute/Channel_Name_Filter_Field'), GlobalVariable.label)

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'), 0)

WebUI.click(findTestObject('Web Part/Re-usable/Global/td_Tag(GridSkuRow)'))

String str1 = null

Row_Index = Row_Index

for(int i : Row_Index)
{

//for (j = 0; j < Retailer_Name.size(); j++) {
   // KeywordUtil.logInfo(Retailer_Name.get(j) + ' ::: Retailer Name displayed in loop')

   // i = (j + 1)

    WebUI.waitForElementVisible(findTestObject('Web Part/RetailerAttribute/Retailer_Filter_Field'), 100)

    WebUI.click(findTestObject('Web Part/RetailerAttribute/Retailer_Filter_Field'), FailureHandling.OPTIONAL)

    GlobalVariable.label = findTestData('Mobile Input Data/CreditManagement').getValue('Retailer_Name', i)

    WebUI.setText(findTestObject('Web Part/RetailerAttribute/Retailer_Filter_Field'), GlobalVariable.label)

    WebUI.waitForElementVisible(findTestObject('Web Part/RetailerAttribute/Retailer_Edit_Icon'), 100)

    WebUI.click(findTestObject('Web Part/RetailerAttribute/Retailer_Edit_Icon'))

    WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100)
	
	WebUI.takeScreenshot()

    'Credit Limit Field Validation'
    Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Limit', i)
	
    if (Credit_Limit == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Limit'))
        }
        
    } else {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ){
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'))

            WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Credit_Limit, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Limit'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			
			WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Credit_Limit, FailureHandling.STOP_ON_FAILURE)

            //WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Limit'), Credit_Limit)
        }
    }
    
    'Warn Credit Limit Validation'
    Warn_Credit_Limit = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Limit', i)

	
    if (Warn_Credit_Limit == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Limit'))
        }
    } else {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'))
			  WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Warn_Credit_Limit, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Limit'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Keys.chord(Keys.BACK_SPACE))
            WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Limit'), Warn_Credit_Limit)
        }
    }
    
    'Credit Period Validation'
    Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Period', i)

    if (Credit_Period == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Period')) //WebUI.clearText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), FailureHandling.STOP_ON_FAILURE)
        }
        
    } else {
       property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
		
			//if(property33.equals(str1)) {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'))

           WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Keys.chord(Keys.BACK_SPACE))
		   
		   WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Credit_Period, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Period'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Keys.chord(Keys.BACK_SPACE))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Period'), Credit_Period, FailureHandling.STOP_ON_FAILURE)
        }
    }
    
    'Warn Credit Period Validation'
    Warn_Credit_Period = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Period', i)

    if (Warn_Credit_Period == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Period')) 
        }
       
    } else {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'))

            WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Keys.chord(Keys.BACK_SPACE))
			
            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Warn_Credit_Period, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Period'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Keys.chord(Keys.BACK_SPACE))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Period'), Warn_Credit_Period, FailureHandling.STOP_ON_FAILURE)
        }
    }
    
    'Credit Bills Validation'
    Credit_Bill = findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Bill', i)

    if (Credit_Bill == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Bill')) // WebUI.clearText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), FailureHandling.STOP_ON_FAILURE)
        }
        
    } else {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'))

           WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))
		   
		   WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))
		   
            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Credit_Bill, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Credit_Bill'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Credit_Bill'), Credit_Bill, FailureHandling.STOP_ON_FAILURE)
        }
    }
    
    'Warn Credit Bills Validation'
    Warn_Credit_Bill = findTestData('Mobile Input Data/CreditManagement').getValue('Warn_Credit_Bill', i)

    if (Warn_Credit_Bill == '') {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            'CheckBox should be enabled'
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Bill')) 
        }
        
    } else {
        property = WebUI.getAttribute(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), 'disabled', FailureHandling.STOP_ON_FAILURE)

        if (property == ("false") || property.equals(str1) ) {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'))

              WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))

			  WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))
			  
            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Warn_Credit_Bill, FailureHandling.STOP_ON_FAILURE)
        } else {
            WebUI.click(findTestObject('Web Part/RetailerAttribute/CheckBox_Warn_Credit_Bill'))

            WebUI.click(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))
			
			WebUI.sendKeys(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Web Part/RetailerAttribute/Field_Warn_Credit_Bill'), Warn_Credit_Bill, FailureHandling.STOP_ON_FAILURE)
        }
    }
    
	WebUI.takeScreenshot()
	
    WebUI.click(findTestObject('Web Part/SecondaryGroupCreation/Btn_Save'))
	
	'DB Validations'
	
	String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password
	
	connection = DriverManager.getConnection(conn)
	
	stm = connection.createStatement()
	
	Index = i - 1
	
	KeywordUtil.logInfo(Row_Index.toString())
	
	queryString = (('select * from adm_retailer where ARTR_Code = \'' + Retailer_Name.get(Index)) + '\' and ARTR_Isactive = 1')
	
	rs = stm.executeQuery(queryString)
	
	KeywordUtil.logInfo(queryString)
	
	recordSet = rs
	
	if (recordSet.next()) {
		String retailer_id = recordSet.getObject('ARTR_Id')
	
		KeywordUtil.logInfo('Retailer ID is : ' + retailer_id)
	
		'Validating Credit Limit Amount:'
		
		queryString = 'Select * from ADM_Retailer_Mapping where ARM_ARTR_Id = \'' + retailer_id + '\' and ARM_Isactive = 1'
		
		KeywordUtil.logInfo(queryString)

		Statement stm1 = connection.createStatement()
		
		ResultSet rs1 = stm1.executeQuery(queryString)
		
		def recordSet1 = rs1
	
		if (recordSet1.next()) {
			
		'Validating Credit Limit Amount:'
		
		double ARM_Credit_Limit = recordSet1.getObject('ARM_Credit_Limit')

		KeywordUtil.logInfo('ARM_Credit_Limit is : ' + ARM_Credit_Limit)
		
		if(Credit_Limit == '')
		{
			WebUI.verifyEqual(ARM_Credit_Limit, -1 , FailureHandling.STOP_ON_FAILURE)
		}
		else {
			WebUI.verifyEqual(ARM_Credit_Limit, Double.parseDouble(Credit_Limit) , FailureHandling.STOP_ON_FAILURE)
		}
		
		'Validating Warn Credit Limit Amount:'
		
		double ARM_Warn_Credit_Limit = recordSet1.getObject('ARM_Warn_Credit_Limit')
	
		KeywordUtil.logInfo('ARM_Warn_Credit_Limit is : ' + ARM_Warn_Credit_Limit)
		
		if(Warn_Credit_Limit == '')
			{
				WebUI.verifyEqual(ARM_Warn_Credit_Limit, -1 , FailureHandling.STOP_ON_FAILURE)
			}
			else {
				WebUI.verifyEqual(ARM_Warn_Credit_Limit, Double.parseDouble(Warn_Credit_Limit) , FailureHandling.STOP_ON_FAILURE)
			}
		
		'Validating ARM_Credit_Period :'
		
		double ARM_Credit_Period = recordSet1.getObject('ARM_Credit_Period')
	
		KeywordUtil.logInfo('ARM_Credit_Period is : ' + ARM_Credit_Period)
		
		if(Credit_Period == '')
			{
				WebUI.verifyEqual(ARM_Credit_Period, -1 , FailureHandling.STOP_ON_FAILURE)
			}
			else {
				WebUI.verifyEqual(ARM_Credit_Period, Double.parseDouble(Credit_Period) , FailureHandling.STOP_ON_FAILURE)
			}
		
		'Validating ARM_Warn_Credit_Period :'
		
		double ARM_Warn_Credit_Period = recordSet1.getObject('ARM_Warn_Credit_Period')
	
		KeywordUtil.logInfo('ARM_Warn_Credit_Period is : ' + ARM_Warn_Credit_Period)
		
		if(Warn_Credit_Period == '')
			{
				WebUI.verifyEqual(ARM_Warn_Credit_Period, -1 , FailureHandling.STOP_ON_FAILURE)
			}
			else {
				WebUI.verifyEqual(ARM_Warn_Credit_Period, Double.parseDouble(Warn_Credit_Period) , FailureHandling.STOP_ON_FAILURE)
			}
		
		'Validating ARM_Credit_Bills_Count :'
		
		double ARM_Credit_Bills_Count = recordSet1.getObject('ARM_Credit_Bills_Count')
	
		KeywordUtil.logInfo('ARM_Credit_Bills_Count is : ' + ARM_Credit_Bills_Count)
		
		if(Credit_Bill == '')
			{
				WebUI.verifyEqual(ARM_Credit_Bills_Count, -1 , FailureHandling.STOP_ON_FAILURE)
			}
			else {
				WebUI.verifyEqual(ARM_Credit_Bills_Count, Double.parseDouble(Credit_Bill) , FailureHandling.STOP_ON_FAILURE)
			}
		
		'Validating ARM_Warn_Credit_Bills :'
		
		double ARM_Warn_Credit_Bills = recordSet1.getObject('ARM_Warn_Credit_Bills')
	
		KeywordUtil.logInfo('ARM_Warn_Credit_Bills is : ' + ARM_Warn_Credit_Bills)
		
		if(Warn_Credit_Bill == '')
			{
				WebUI.verifyEqual(ARM_Warn_Credit_Bills, -1 , FailureHandling.STOP_ON_FAILURE)
			}
			else {
				WebUI.verifyEqual(ARM_Warn_Credit_Bills, Double.parseDouble(Warn_Credit_Bill) , FailureHandling.STOP_ON_FAILURE)
			}
		
		
		}
	
	}
}

WebUI.closeBrowser()
