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
//
//if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/PIECE_Bar'), 1, FailureHandling.OPTIONAL)) {
//
//	GlobalVariable.PieceQty = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)
//	
//	KeywordUtil.logInfo('PieceQty : ' +GlobalVariable.PieceQty)
//	
//	Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/PIECE_Bar'), 0)
//	
//	Mobile.delay(1)
//	
//	GlobalVariable.CaseQty = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)
//	
//    KeywordUtil.logInfo('CaseQty : ' +GlobalVariable.CaseQty)
//	
//	}
//else (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/CASE_Bar'), 1, FailureHandling.OPTIONAL)) {
//
//	GlobalVariable.CaseQty = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)
//	
//	KeywordUtil.logInfo('CaseQty : ' +GlobalVariable.CaseQty)
//	
//	Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/CASE_Bar'), 0)
//	
//	Mobile.delay(1)
//	
//	GlobalVariable.PieceQty = Mobile.getText(findTestObject('Mobile/OrderInvoice/Enter_Qty'), 0)
//	
//	KeywordUtil.logInfo('PieceQty : ' +GlobalVariable.PieceQty)
//}

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Total_PieceCase_Oty = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScreen-TotalQty(CasePiece)'),    0)

//Total_PieceCase_Oty = '2C/3P'

println(Total_PieceCase_Oty)

String[] arr = Total_PieceCase_Oty.split('/')

GlobalVariable.CaseQty = (arr[0]).replace(' C', '')

GlobalVariable.PieceQty = (arr[1]).replace(' P', '')

KeywordUtil.logInfo('CaseQty : ' +GlobalVariable.CaseQty)

KeywordUtil.logInfo('PieceQty : ' +GlobalVariable.PieceQty)





