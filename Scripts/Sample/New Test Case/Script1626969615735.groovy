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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariableimport 
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

String screen_name=findTestData('PNG Product/Master/Master').getValue('Sheet Name', 1)
String configuration=findTestData('PNG Product/Master/Master').getValue('Configuration', 1)
String path = 'PNG Product/Modules/' +screen_name+'/'+configuration
String filepath = 'DDF//PNG Product//' + screen_name + '//' + screen_name + '.xlsx'
List<Integer> rowlist = new ArrayList<Integer>()
List<String> data = new ArrayList<Integer>()

File file= new File(filepath)
FileInputStream stream=new FileInputStream(file)
XSSFWorkbook book=new XSSFWorkbook(stream)
XSSFSheet sheet=book.getSheet(configuration)
Row row=sheet.getRow(0)
XSSFSheet s = sheet
Row r = row

int Columnid = -1 ,rowid=-1
for (int cn=1; cn<r.getLastCellNum(); cn++) {
	Cell c = r.getCell(cn)

	if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
		// Can't be this cell - it's empty
		continue;
	}

	if (c.getCellType() == Cell.CELL_TYPE_STRING||c.getCellType() == Cell.CELL_TYPE_NUMERIC||c.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
		String text = c.getStringCellValue()
		if ("Data to be used".equals(text)) {
			Columnid = cn
			println Columnid+1
			break
		}
	}
}
if (Columnid == -1) {
	throw new Exception("Data to be used - column not found")
}

for (Row row1 : sheet) 
{
	for (Cell cell : row1) 
	{
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) 
		{
			if (cell.getRichStringCellValue().getString().trim().equals('Y')) 
			{
				
				rowlist.add(row1.getRowNum())
				data.add(findTestData(path).getValue('UserName',rowlist.get(rw)))
			}
		}
	}
}
println  rowlist

//


