/**
 * 
 * 
 *
 * 
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */


import ApiClient from "../ApiClient";
import DossierView from '../model/DossierView';

/**
* Default service.
* @module api/DefaultApi
* @version 1.0.0
*/
export default class DefaultApi {

    /**
    * Constructs a new DefaultApi. 
    * @alias module:api/DefaultApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }


    /**
     * Callback function to receive the result of the getContents operation.
     * @callback module:api/DefaultApi~getContentsCallback
     * @param {String} error Error message, if any.
     * @param data This operation does not return a value.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {String} fileCode 
     * @param {String} dossierKey 
     * @param {String} dossierPackage 
     * @param {String} dossierCode 
     * @param {module:api/DefaultApi~getContentsCallback} callback The callback function, accepting three arguments: error, data, response
     */
    getContents(fileCode, dossierKey, dossierPackage, dossierCode, callback) {
      let postBody = null;
      // verify the required parameter 'fileCode' is set
      if (fileCode === undefined || fileCode === null) {
        throw new Error("Missing the required parameter 'fileCode' when calling getContents");
      }
      // verify the required parameter 'dossierKey' is set
      if (dossierKey === undefined || dossierKey === null) {
        throw new Error("Missing the required parameter 'dossierKey' when calling getContents");
      }
      // verify the required parameter 'dossierPackage' is set
      if (dossierPackage === undefined || dossierPackage === null) {
        throw new Error("Missing the required parameter 'dossierPackage' when calling getContents");
      }
      // verify the required parameter 'dossierCode' is set
      if (dossierCode === undefined || dossierCode === null) {
        throw new Error("Missing the required parameter 'dossierCode' when calling getContents");
      }

      let pathParams = {
        'fileCode': fileCode,
        'dossierKey': dossierKey,
        'dossierPackage': dossierPackage,
        'dossierCode': dossierCode
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = null;
      return this.apiClient.callApi(
        '/dossiers/{dossierKey}/{dossierPackage}/{dossierCode}/dossierfiles/{fileCode}', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the getDossier operation.
     * @callback module:api/DefaultApi~getDossierCallback
     * @param {String} error Error message, if any.
     * @param {module:model/DossierView} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {String} dossierKey 
     * @param {String} dossierPackage 
     * @param {String} dossierCode 
     * @param {module:api/DefaultApi~getDossierCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link module:model/DossierView}
     */
    getDossier(dossierKey, dossierPackage, dossierCode, callback) {
      let postBody = null;
      // verify the required parameter 'dossierKey' is set
      if (dossierKey === undefined || dossierKey === null) {
        throw new Error("Missing the required parameter 'dossierKey' when calling getDossier");
      }
      // verify the required parameter 'dossierPackage' is set
      if (dossierPackage === undefined || dossierPackage === null) {
        throw new Error("Missing the required parameter 'dossierPackage' when calling getDossier");
      }
      // verify the required parameter 'dossierCode' is set
      if (dossierCode === undefined || dossierCode === null) {
        throw new Error("Missing the required parameter 'dossierCode' when calling getDossier");
      }

      let pathParams = {
        'dossierKey': dossierKey,
        'dossierPackage': dossierPackage,
        'dossierCode': dossierCode
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['application/xml', 'application/json', 'application/xhtml+xml'];
      let returnType = DossierView;
      return this.apiClient.callApi(
        '/dossiers/{dossierKey}/{dossierPackage}/{dossierCode}', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the setContents operation.
     * @callback module:api/DefaultApi~setContentsCallback
     * @param {String} error Error message, if any.
     * @param data This operation does not return a value.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {String} fileCode 
     * @param {String} dossierKey 
     * @param {String} dossierPackage 
     * @param {String} dossierCode 
     * @param {Object} opts Optional parameters
     * @param {Object} opts.body 
     * @param {module:api/DefaultApi~setContentsCallback} callback The callback function, accepting three arguments: error, data, response
     */
    setContents(fileCode, dossierKey, dossierPackage, dossierCode, opts, callback) {
      opts = opts || {};
      let postBody = opts['body'];
      // verify the required parameter 'fileCode' is set
      if (fileCode === undefined || fileCode === null) {
        throw new Error("Missing the required parameter 'fileCode' when calling setContents");
      }
      // verify the required parameter 'dossierKey' is set
      if (dossierKey === undefined || dossierKey === null) {
        throw new Error("Missing the required parameter 'dossierKey' when calling setContents");
      }
      // verify the required parameter 'dossierPackage' is set
      if (dossierPackage === undefined || dossierPackage === null) {
        throw new Error("Missing the required parameter 'dossierPackage' when calling setContents");
      }
      // verify the required parameter 'dossierCode' is set
      if (dossierCode === undefined || dossierCode === null) {
        throw new Error("Missing the required parameter 'dossierCode' when calling setContents");
      }

      let pathParams = {
        'fileCode': fileCode,
        'dossierKey': dossierKey,
        'dossierPackage': dossierPackage,
        'dossierCode': dossierCode
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = null;
      return this.apiClient.callApi(
        '/dossiers/{dossierKey}/{dossierPackage}/{dossierCode}/dossierfiles/{fileCode}', 'PUT',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }


}