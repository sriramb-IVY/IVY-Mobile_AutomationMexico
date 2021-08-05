import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

String screen_name=findTestData('PNG Product/Master/Master').getValue('Sheet Name', 1)
String configuration=findTestData('PNG Product/Master/Master').getValue('Configuration', 1)
String path = 'PNG Product/Modules/' +screen_name+'/'+configuration
String filepath = 'DDF//PNG Product//' + screen_name + '//' + screen_name + '.xlsx'
List<Integer> rowlist = new ArrayList<Integer>()

File file= new File(filepath)
FileInputStream stream=new FileInputStream(file)
XSSFWorkbook book=new XSSFWorkbook(stream)
XSSFSheet sheet=book.getSheet(configuration)
Row row=sheet.getRow(0)
s = sheet
r = row

int Columnid = -1 ,rowid=-1
for (int cn=1; cn<r.getLastCellNum(); cn++) 
	{
		Cell c = r.getCell(cn)
   
		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) 
			{
				// Can't be this cell - it's empty
				continue;
			}
    
		if (c.getCellType() == Cell.CELL_TYPE_STRING||c.getCellType() == Cell.CELL_TYPE_NUMERIC||c.getCellType() == Cell.CELL_TYPE_BOOLEAN) 
			{
				String text = c.getStringCellValue()
				if ("Data to be used".equals(text)) 
					{
						Columnid = cn
						println Columnid+1
						break
					}
			}
	}
if (Columnid == -1) 
	{
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
								rowlist.add(findTestData(path).getValue(column_name,rowlist.get(index)))
								//add(row1.getRowNum())
							}
					}
			}
	}
return rowlist
	








