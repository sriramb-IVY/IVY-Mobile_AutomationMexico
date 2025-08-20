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
import com.sun.net.httpserver.Authenticator.Failure as Failure
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.junit.Assert as Assert
import static org.junit.Assert.fail

if(Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 2))
{

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)
	
GlobalVariable.Scheme_Free_SKU = Scheme_Free_SKU

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 0)

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

GlobalVariable.Scheme_Free_SKU = Scheme_Free_SKU02

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 0)

FreeSKU02_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

if ((Integer.parseInt(FreeSKU02_Qty_Slab1) == 0) && (Integer.parseInt(FreeSKU_Qty_Slab1) != 0)) {
    
	Mobile.verifyEqual(Integer.parseInt(FreeSKU_Qty_Slab1), Integer.parseInt(FreeSKU_MinQty), FailureHandling.STOP_ON_FAILURE)
	
	GlobalVariable.Scheme_Free_SKU = Scheme_Free_SKU
	
	FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)
	
	FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)
	
	Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + FreeSKU_MinQty)
	
	Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

	Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + FreeSKU_MaxQty)

	Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')
	
	FreeProduct_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)
	
	GlobalVariable.Scheme_Free_SKU = FreeProduct_Slab1
	
	GlobalVariable.FreeProductQty = FreeSKU_MinQty
	
} else if ((Integer.parseInt(FreeSKU_Qty_Slab1)  == 0) && (Integer.parseInt(FreeSKU02_Qty_Slab1) != 0)) {
	
	Mobile.verifyEqual(Integer.parseInt(FreeSKU02_Qty_Slab1), Integer.parseInt(FreeSKU_MinQty), FailureHandling.STOP_ON_FAILURE)
	
	GlobalVariable.Scheme_Free_SKU = Scheme_Free_SKU02
	
	FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)
	
	FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)
	
	Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + FreeSKU_MinQty)
	
	Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

	Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + FreeSKU_MaxQty)

	Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')
	
	FreeProduct_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)
	
	GlobalVariable.Scheme_Free_SKU = FreeProduct_Slab1
	
	GlobalVariable.FreeProductQty = FreeSKU_MinQty
	

} else if (((a == 0) && (b == 0)) || ((a != 0) && (b != 0))) {
    Mobile.takeScreenshot()

    Assert.fail()
}
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)
}
