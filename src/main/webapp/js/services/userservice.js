
var myApp = angular.module('RoutingApp');
myApp.factory('userservice', ['appConfig','ajaxService', function(appConfig,ajaxService) {

	var userservice = {};
		userservice.userexist = function(username,successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/private/api/search/name?username='+username,'',function(data){
				console.log("user service:" + data);
				successHandler(data.username);
			});
		}

		userservice.verifyUniqueness = function(fieldName, value,successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/private/api/search/unique?' + fieldName + "=" + value,'',function(data){
				console.log("user service:" + data);
				successHandler(data.username);
			});
		}

		userservice.getUsers = function(successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/admin/api/user/list','',function(data){
				console.log("user service:" + data);
				successHandler(data);
			});
		}
		userservice.getCurrentUser = function(successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/private/api/user','',function(data){
				console.log("user service:" + data);
				successHandler(data);
			});
		}
		userservice.saveClientFields = function(selectedClientFields,successHandler,errorHandler){
			ajaxService.post(appConfig.baseUrl + '/private/api/client/fields',selectedClientFields,function(data){
				console.log("user service:" + data);
				successHandler(data);
			});
		}
		userservice.saveTicketFields = function(selectedTicketFields,successHandler,errorHandler){
			ajaxService.post(appConfig.baseUrl + '/private/api/ticket/fields',selectedTicketFields,function(data){
				console.log("user service:" + data);
				successHandler(data);
			});
		}
		return userservice;
	}]);



