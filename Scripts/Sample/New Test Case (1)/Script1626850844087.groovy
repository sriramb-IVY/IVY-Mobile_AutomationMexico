import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.util.ArrayList.ListItr

import com.kms.katalon.keyword.excel.ExcelKeywords

String screen_name=findTestData('PNG Product/Master/Master').getValue('Sheet Name', 1)
String configuration=findTestData('PNG Product/Master/Master').getValue('Configuration', 1)
String path = 'PNG Product/Modules/' +screen_name+'/'+configuration
String filepath = 'DDF//PNG Product//' + screen_name + '//' + screen_name + '.xlsx'
int i=1,column_no
int collumn=findTestData(path).getColumnNumbers(),rowsize=findTestData(path).getRowNumbers()
List<Integer> rowlist = new ArrayList<Integer>()

for(i=1;i<=collumn;i++) {
	if(findTestData(path).getValue(i, 1).equalsIgnoreCase("Data to be used")) {
		column_no=i
	}
}
int index=0
for(row=1;row<=rowsize;row++) {

	if((findTestData(path).getValue(column_no, row).equalsIgnoreCase("Y"))) {
		
		rowlist.add(row)
		//println rowlist
		
		println findTestData(path).getValue(column_no,rowlist.get(index))
		index++
	}
		
//int rowno=ExcelKeywords.getRowIndexByCellContent(ExcelKeywords.getExcelSheet(ExcelKeywords.getWorkbook(filepath),configuration ),"Y", column_no-1)

}
println rowlist

//return findTestData(path).getValue(column_name,row_id)

