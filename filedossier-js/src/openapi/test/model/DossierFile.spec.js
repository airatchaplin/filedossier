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

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD.
    define(['expect.js', process.cwd()+'/src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require(process.cwd()+'/src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.FiledossierApi);
  }
}(this, function(expect, FiledossierApi) {
  'use strict';

  var instance;

  beforeEach(function() {
    instance = new FiledossierApi.DossierFile();
  });

  var getProperty = function(object, getter, property) {
    // Use getter method if present; otherwise, get the property directly.
    if (typeof object[getter] === 'function')
      return object[getter]();
    else
      return object[property];
  }

  var setProperty = function(object, setter, property, value) {
    // Use setter method if present; otherwise, set the property directly.
    if (typeof object[setter] === 'function')
      object[setter](value);
    else
      object[property] = value;
  }

  describe('DossierFile', function() {
    it('should create an instance of DossierFile', function() {
      // uncomment below and update the code to test DossierFile
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be.a(FiledossierApi.DossierFile);
    });

    it('should have the property code (base name: "code")', function() {
      // uncomment below and update the code to test the property code
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be();
    });

    it('should have the property name (base name: "name")', function() {
      // uncomment below and update the code to test the property name
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be();
    });

    it('should have the property exists (base name: "exists")', function() {
      // uncomment below and update the code to test the property exists
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be();
    });

    it('should have the property readonly (base name: "readonly")', function() {
      // uncomment below and update the code to test the property readonly
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be();
    });

    it('should have the property required (base name: "required")', function() {
      // uncomment below and update the code to test the property required
      //var instane = new FiledossierApi.DossierFile();
      //expect(instance).to.be();
    });

  });

}));
