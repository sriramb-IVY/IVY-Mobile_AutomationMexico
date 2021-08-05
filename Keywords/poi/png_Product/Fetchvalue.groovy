package poi.png_Product



import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.keyword.excel.ExcelKeywords

import internal.GlobalVariable

public class Fetchvalue {
	@Keyword
	public ArrayList<String> getValue(String column_name, String Sheet_name) {

		String Masterfile = 'DDF//PNG Product//Master//Master.xlsx'

		Workbook WorkBook =ExcelKeywords.getWorkbook(Masterfile)

		Sheet configSheet = ExcelKeywords.getExcelSheet(WorkBook,"Master" )

		int rowindex=ExcelKeywords.getRowIndexByCellContent(configSheet,Sheet_name, 3)
		GlobalVariable.dataset=Integer.parseInt(findTestData('PNG Product/Master/Master').getValue('Data set', rowindex))
		int sheetrow=rowindex
		//above Script is used to Fetch the SheetName Row and data set to be Used for Test data

		String screen_name=findTestData('PNG Product/Master/Master').getValue('Sheet Name', sheetrow)
		String configuration=findTestData('PNG Product/Master/Master').getValue('Configuration', sheetrow)
		String path = 'PNG Product/Modules/' +screen_name+'/'+configuration
		String filepath = 'DDF//PNG Product//' + screen_name + '//' + screen_name + '.xlsx'
		List<Integer> rowlist = new ArrayList<Integer>()
		List<String> testData = new ArrayList<String>()
		//above Script is used to build file Path for Config Sheet
		File file= new File(filepath)
		FileInputStream stream=new FileInputStream(file)
		XSSFWorkbook book=new XSSFWorkbook(stream)
		XSSFSheet sheet=book.getSheet(configuration)
		Row row=sheet.getRow(0)
		XSSFSheet s = sheet
		Row r = row

		int Columnid = -1 ,rowid=-1,sizeofRow
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
					//collumn no for Data to be Used
					break
				}
			}
		}
		if (Columnid == -1) {
			throw new Exception("Data to be used - column not found")
		}

		for (Row row1 : sheet) {
			for (Cell cell : row1) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals('Y')) {
						rowlist.add(row1.getRowNum())
						//to get row no which has the Value "Y" in Data to be used column
					}
				}
			}
		}
		for(int index=0;index<rowlist.size();index++) {

			testData.add(findTestData(path).getValue(column_name,rowlist.get(index)))
			//gets the data from the row which has row value "Y"
		}

		sizeofRow = testData.size()

		//return testData.get(GlobalVariable.dataset)
		return testData
	}
}


