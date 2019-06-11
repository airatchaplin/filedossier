# OpenapiJsClient.DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getContents**](DefaultApi.md#getContents) | **GET** /dossiers/{dossierKey}/{dossierPackage}/{dossierCode}/dossierfiles/{fileCode} | 
[**getDossier**](DefaultApi.md#getDossier) | **GET** /dossiers/{dossierKey}/{dossierPackage}/{dossierCode} | 
[**setContents**](DefaultApi.md#setContents) | **PUT** /dossiers/{dossierKey}/{dossierPackage}/{dossierCode}/dossierfiles/{fileCode} | 



## getContents

> getContents(fileCode, dossierKey, dossierPackage, dossierCode)



### Example

```javascript
import OpenapiJsClient from 'openapi-js-client';

let apiInstance = new OpenapiJsClient.DefaultApi();
let fileCode = "fileCode_example"; // String | 
let dossierKey = "dossierKey_example"; // String | 
let dossierPackage = "dossierPackage_example"; // String | 
let dossierCode = "dossierCode_example"; // String | 
apiInstance.getContents(fileCode, dossierKey, dossierPackage, dossierCode, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fileCode** | **String**|  | 
 **dossierKey** | **String**|  | 
 **dossierPackage** | **String**|  | 
 **dossierCode** | **String**|  | 

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


## getDossier

> DossierView getDossier(dossierKey, dossierPackage, dossierCode)



### Example

```javascript
import OpenapiJsClient from 'openapi-js-client';

let apiInstance = new OpenapiJsClient.DefaultApi();
let dossierKey = "dossierKey_example"; // String | 
let dossierPackage = "dossierPackage_example"; // String | 
let dossierCode = "dossierCode_example"; // String | 
apiInstance.getDossier(dossierKey, dossierPackage, dossierCode, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dossierKey** | **String**|  | 
 **dossierPackage** | **String**|  | 
 **dossierCode** | **String**|  | 

### Return type

[**DossierView**](DossierView.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json, application/xhtml+xml


## setContents

> setContents(fileCode, dossierKey, dossierPackage, dossierCode, opts)



### Example

```javascript
import OpenapiJsClient from 'openapi-js-client';

let apiInstance = new OpenapiJsClient.DefaultApi();
let fileCode = "fileCode_example"; // String | 
let dossierKey = "dossierKey_example"; // String | 
let dossierPackage = "dossierPackage_example"; // String | 
let dossierCode = "dossierCode_example"; // String | 
let opts = {
  'body': null // Object | 
};
apiInstance.setContents(fileCode, dossierKey, dossierPackage, dossierCode, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fileCode** | **String**|  | 
 **dossierKey** | **String**|  | 
 **dossierPackage** | **String**|  | 
 **dossierCode** | **String**|  | 
 **body** | **Object**|  | [optional] 

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*
