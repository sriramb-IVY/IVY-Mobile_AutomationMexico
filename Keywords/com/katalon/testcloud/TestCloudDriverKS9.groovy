package com.katalon.testcloud
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.util.WebDriverPropertyUtil
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable

class TestCloudDriverKS9 {

	@Keyword
	static void startTestCloudEnvironment(String browserVersion, String orgId) {
		// Send API to get the token
		def tokenResponse = WS.sendRequest(findTestObject('Cloud Video/Get Token'))
		WS.verifyResponseStatusCode(tokenResponse, 200)
		def token = WS.getElementPropertyValue(tokenResponse, 'data.jwt')
		KeywordUtil.logInfo(token)
		GlobalVariable.G_token_Global = token

		// Start TestCloud environment
		String url = "https://token:${token}@testcloud.katalon.com/hub"
		Map<String, Object> props = [
			"browserName": 'chrome',
			"browserVersion": browserVersion,
			"platformName": 'windows',
			"katalon:options": [
				"metadata": [
					"organizationId": orgId
				],
				"executionMode": "queue",
				"usingTunnel": false
			]
		]
		DesiredCapabilities caps = WebDriverPropertyUtil.toDesireCapabilities(props, WebUIDriverType.REMOTE_WEB_DRIVER)
		WebDriver driver = new RemoteWebDriver(new URL(url), caps)

		DriverFactory.changeWebDriver(driver)
	}
}