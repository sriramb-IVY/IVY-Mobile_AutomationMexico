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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.junit.Assert as Assert

SimpleDateFormat sdf = new SimpleDateFormat('dd-MMM')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, DateCount)

String Currentdate = sdf.format(c.getTime())

KeywordUtil.logInfo('Date and month : ' + Currentdate)

String[] sDate = Currentdate.split('-')

String DateValue = sDate[0]

String ExpectedMonth = sDate[1]

if (DateValue.startsWith('0') == true) {
    GlobalVariable.sDate = DateValue.charAt(1)
} else {
    GlobalVariable.sDate = DateValue
}

KeywordUtil.logInfo('Expected Date is: ' +DateValue)

KeywordUtil.logInfo('Expected Month is: ' +ExpectedMonth)

Mobile.takeScreenshot()

String Actual_calendar_Value = Mobile.getText(findTestObject('Mobile/Common/Calendar(day,date,month)'),
	0, FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Common/Calendar_Global_Date(Disabled)'), 2,FailureHandling.OPTIONAL))
{
	
	Mobile.takeScreenshot()
	
	Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Common/Calendar_Global_Date(Disabled)'), 2)	
}
else if((DateCount) < 0)
{
	if(Mobile.verifyElementExist(findTestObject('Mobile/Common/Calendar_PreMonth_Btn'), 2))
	{
		Mobile.tap(findTestObject('Mobile/Common/Calendar_PreMonth_Btn'), 0, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.takeScreenshot()
		
		Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Common/Calendar_Global_Date(Disabled)'), 2)
	}
	else
	{
		Mobile.takeScreenshot()
		
		Assert.assertFalse(Actual_calendar_Value.contains(ExpectedMonth))
	}
}
else((DateCount) > 0)
{
	if(Mobile.verifyElementExist(findTestObject('Mobile/Common/Calendar_NextMonth_Btn'), 2))
		{
			Mobile.tap(findTestObject('Mobile/Common/Calendar_NextMonth_Btn'), 0, FailureHandling.STOP_ON_FAILURE)
			
			Mobile.takeScreenshot()
			
			Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/Common/Calendar_Global_Date(Disabled)'), 2)
		}
		else
		{
			Mobile.takeScreenshot()
			
			Assert.assertFalse(Actual_calendar_Value.contains(ExpectedMonth))
		}
}











