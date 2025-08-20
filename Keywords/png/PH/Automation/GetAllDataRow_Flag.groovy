package png.PH.Automation

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.util.ArrayList

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.keyword.excel.ExcelKeywords

import internal.GlobalVariable

public class GetAllDataRow_Flag {
	@Keyword
	public ArrayList<String> getAllDataRow(String file_name, String sheet_name,String column_name) {

		String path = 'PnG PH - VBL//' +file_name+'/'+sheet_name
		String filepath = 'DDF//PnG PH - VBL//' + file_name + '.xlsx'


		List<Integer> rowlist = new ArrayList<Integer>()
		List<String> testData = new ArrayList<String>()
		//above Script is used to build file Path for Config Sheet
		File file= new File(filepath)
		FileInputStream stream=new FileInputStream(file)
		XSSFWorkbook book=new XSSFWorkbook(stream)
		XSSFSheet sheet=book.getSheet(sheet_name)
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
				if ("Flag".equals(text)) {
					Columnid = cn
					//collumn no for Data to be Used
					break
				}
			}
		}
		if (Columnid == -1) {
			throw new Exception("Flag - column not found")
		}

		for (Row row1 : sheet) {
			for (Cell cell : row1) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals('Y')) {
						rowlist.add(row1.getRowNum())
						//to get row no which has the Value "Y" in Flag column
					}
				}
			}
		}
		for(int index=0;index<rowlist.size();index++) {

			testData.add(findTestData(path).getValue(column_name,rowlist.get(index)))
			//gets the data from the row which has row value "Y"
		}

		sizeofRow = testData.size()


		return testData
	}
}
