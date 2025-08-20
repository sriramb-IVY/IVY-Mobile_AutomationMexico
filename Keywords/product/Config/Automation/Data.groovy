package product.Config.Automation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.keyword.excel.ExcelKeywords
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

public class Data {
	@Keyword
	public static String readExcelData(String Column, int row) {
		String screen_name=findTestData('Product Config Automation/Master/Master').getValue('Sheet Name', 1)
		//println screen_name
		String configuration=findTestData('Product Config Automation/Master/Master').getValue('Configuration', 1)
		//println configuration
		String path = 'Product Config Automation/Modules/' +screen_name+'/'+configuration
		//println(path)

		String data = findTestData(path).getValue(Column, row)
		return data
	}
	@Keyword
	public static String readExcelData(int column1, int row1) {
		String screen_name=findTestData('Product Config Automation/Master/Master').getValue('Sheet Name', 1)
		//println screen_name
		String configuration=findTestData('Product Config Automation/Master/Master').getValue('Configuration', 1)
		//println configuration
		String path = 'Product Config Automation/Modules/' +screen_name+'/'+configuration
		//println(path)

		String data = findTestData(path).getValue(column1, row1)
		return data
	}

	@Keyword
	public String readData(String column_name) {
		String screen_name=findTestData('Product Config Automation/Master/Master').getValue('Sheet Name', 1)
		String configuration=findTestData('Product Config Automation/Master/Master').getValue('Configuration', 1)
		String path = 'Product Config Automation/Modules/' +screen_name+'/'+configuration
		String filepath = 'DDF//Product Config Automation//' + screen_name + '//' + screen_name + '.xlsx'
		int i=1
		int collumn=findTestData(path).getColumnNumbers()
		int column_no
		for(i=1;i<=collumn;i++) {
			if(findTestData(path).getValue(i, 1).equalsIgnoreCase("Data to be used")) {
				column_no=i
			}
		}

		int row_id=ExcelKeywords.getRowIndexByCellContent(ExcelKeywords.getExcelSheet(ExcelKeywords.getWorkbook(filepath),configuration ),"Y", column_no-1)

		return findTestData(path).getValue(column_name,row_id)
	}
}
