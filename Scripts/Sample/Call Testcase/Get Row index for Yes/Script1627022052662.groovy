import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.keyword.excel.ExcelKeywords


String screen_name=findTestData('PNG Product/Master/Master').getValue('Sheet Name', 1)
String configuration=findTestData('PNG Product/Master/Master').getValue('Configuration', 1)
String path = 'PNG Product/Modules/' +screen_name+'/'+configuration
println RunConfiguration.getProjectDir()
String filepath = RunConfiguration.getProjectDir() +'/DDF/PNG Product/' + screen_name + '/' + screen_name + '.xlsx'
println(filepath)

int i=1
int collumn=findTestData(path).getColumnNumbers()
int column_no
for(i=1;i<=collumn;i++)
{
	if(findTestData(path).getValue(i, 1).equalsIgnoreCase("Data to be used"))
	{
		column_no=i
		//println column_no
	}
}

//println column_no

WorkBook =ExcelKeywords.getWorkbook(filepath)
//println WorkBook
//println configuration
sheet = ExcelKeywords.getExcelSheet(WorkBook,configuration )
//println sheet
rowindex=ExcelKeywords.getRowIndexByCellContent(sheet,"Y", column_no-1)

int row_id=rowindex+1


println findTestData(path).getValue("UserName",row_id)
println findTestData(path).getValue("Password",row_id)
