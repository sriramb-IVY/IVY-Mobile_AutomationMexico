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


if(Mobile.verifyElementExist(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 2, FailureHandling.OPTIONAL))
{

SimpleDateFormat sdf = new SimpleDateFormat('dd-MMM')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, 0)

String Currentdate = sdf.format(c.getTime())

KeywordUtil.logInfo('Current Date and month : ' + Currentdate)

String[] sDate = Currentdate.split('-')

KeywordUtil.logInfo((((sDate[0]) + ';') + (sDate[1])) + ';')

String DateValue = sDate[0]

if (DateValue.startsWith('0') == true) {
    GlobalVariable.sDate = DateValue.charAt(1)
} else {
    GlobalVariable.sDate = DateValue
}

String Datee = GlobalVariable.sDate

KeywordUtil.logInfo(Datee)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 2)

Mobile.delay(1)

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

}