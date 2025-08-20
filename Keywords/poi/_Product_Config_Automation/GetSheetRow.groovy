package poi._Product_Config_Automation

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.keyword.excel.ExcelKeywords

public class GetSheetRow {
	@Keyword
	public int getSheet(String Sheet_name){
		String Masterfile = 'DDF//Product Config Automation//Master//Master.xlsx'

		Workbook WorkBook =ExcelKeywords.getWorkbook(Masterfile)

		Sheet configSheet = ExcelKeywords.getExcelSheet(WorkBook,"Master" )

		int rowindex=ExcelKeywords.getRowIndexByCellContent(configSheet,Sheet_name, 3)

		return rowindex+1
	}
}
