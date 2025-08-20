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
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat


 String ss = '[56,601][976,697]'
 
 String[] arr = ss.split(']')
 
Start_Y_value = (arr[0])
 
 End_Y_value = (arr[1])

 String[] ar = Start_Y_value.split(',')
 
 Start_Y_value = (ar[1])
 
 println( Start_Y_value)
 
 String[] ar1 = End_Y_value.split(',')
 
 End_Y_value = (ar1[1])
 
 println( End_Y_value)
 
 
 
 ss = ss.replaceAll('/[', ',')

 ss = ss.replaceAll('/]', ',')

 xx = ss.split(',')
 println(xx)