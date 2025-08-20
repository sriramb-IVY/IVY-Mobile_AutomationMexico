import java.text.SimpleDateFormat
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil



String pattern = dateType

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

GlobalVariable.sDate =  currentdate

KeywordUtil.logInfo(currentdate)