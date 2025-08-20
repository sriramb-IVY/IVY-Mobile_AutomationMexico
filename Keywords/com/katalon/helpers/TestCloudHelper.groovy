package com.katalon.helpers

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.Capabilities
import com.katalon.services.LogInService
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.util.WebDriverPropertyUtil

import internal.GlobalVariable

public class TestCloudHelper {
	@Keyword
	public WebDriver createTestCloudWindowsChromeDriver(String apiKey, String orgId, String browserVersion) {
		String token = new LogInService().getToken(apiKey)
		String url = "https://token:${token}@testcloud.katalon.com/hub"
		Map<String, Object> props = [
			"browserName": 'chrome',
			"browserVersion": browserVersion,
			"platformName": 'windows',
			"katalon:options": [
				"metadata": [
					"organizationId": orgId,
					"accountId": orgId
				],
				"executionMode": "queue",
				"tunnel": true,
				"tunnelId": GlobalVariable.tunnelId,
				"usingTunnel": true
			]
		]
		//DesiredCapabilities caps = WebDriverPropertyUtil.toDesireCapabilities(props, WebUIDriverType.REMOTE_WEB_DRIVER)
		Capabilities capss = WebDriverPropertyUtil.toDesireCapabilities(props, WebUIDriverType.REMOTE_WEB_DRIVER)


		WebDriver driver = new  RemoteWebDriver(new  URL(url), capss)
		DriverFactory.changeWebDriver(driver)
		return driver
	}
}
