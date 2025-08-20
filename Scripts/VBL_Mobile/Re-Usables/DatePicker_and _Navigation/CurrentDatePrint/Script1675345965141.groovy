import java.text.SimpleDateFormat
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

String pattern = 'dd/MMM/yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String CurrentDate = simpleDateFormat.format(new Date())

GlobalVariable.CurrentDate = CurrentDate