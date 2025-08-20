<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>TPM Authentication</name>
   <tag></tag>
   <elementGuidId>63cc7470-3a47-4beb-9a5d-9f310b364889</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;application/xml&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/xml</value>
      <webElementGuid>4ecd1328-8b29-4f7c-bd8e-8be721e22864</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>User-Identity</name>
      <type>Main</type>
      <value>o0PM/RlJApuMNtgmJZCdpfvpwvwc7J68a7b06cMIzrubcsMuUECgCHaLGwaX6b/SCgBBNQu8zfRkWdIeaqP3iios9ZplrmGzdGZidT5WJTRO6JXreKI410ezE52VegQRf9dmaFfha5zGO07k/qKVC5LKN9/oP5Sv50YdBR4aQOqfuoYXmf3YbMvV56cTsmfv0XN1qFVK9+T3iFnLxU0JbB2/XoZ6OHCtc9bUbhq7bqmT+mI06KyQAPm8rZEiqBfZPKzkBjFKG8lqAiY1SzZskh83nSTnd1Iqwrz9mmsIcAhCQILuhjFCEzax+Ft/ESYRDnidS4yBqN6EgDFrUJIV5g==</value>
      <webElementGuid>1872f6aa-20ce-4e1e-8719-75b322533e7d</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Auth_Secret</name>
      <type>Main</type>
      <value>Y2F0Y2hlciUyMHdvbmclMjBsb3ZlJTIwLm5ldA==</value>
      <webElementGuid>09accabe-d9b1-4492-b875-dfed0f51b53b</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>https://tpmproduct01-qa.ivycpg.com/web/api/Authentication</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
