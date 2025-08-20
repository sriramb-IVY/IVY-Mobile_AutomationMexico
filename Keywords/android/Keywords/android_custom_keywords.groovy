package android.Keywords

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import io.appium.java_client.AppiumDriver

public class android_custom_keywords {
	@Keyword
	public static void Resetapk() {
		AppiumDriver addriver = MobileDriverFactory.getDriver()
		addriver.resetApp()
	}
}

