
var myApp = angular.module('RoutingApp');
myApp.factory('clientService', ['appConfig','ajaxService', function(appConfig,ajaxService) {

	var clientService = {};

	clientService.verifyUniqueness = function(fieldName, value,successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/private/api/client/search/unique?' + fieldName + "=" + value,'',function(data){
				successHandler(data.id);
			});
		}

	clientService.loadClient = function(id, successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/private/api/client',{"clientId" : id},function(data){
				successHandler(data);
			});
		}

	clientService.loadClientFollowUp = function(id, successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/admin/api/customer/followup/get',{"customerFollowUpid" : id},function(data){
				successHandler(data);
			});
		}

	clientService.searchClients = function(parmas,successHandler,errorHandler){
			ajaxService.get(appConfig.baseUrl + '/public/api/client',parmas,function(data){
				successHandler(data);
			});
		}

	clientService.loadPrimaryService = function (successHandler,errorHandler) {
		ajaxService.get(appConfig.baseUrl + '/private/api/primary/services', {}, function (data) {
			successHandler(data);
		});
	}

	clientService.loadService = function (successHandler,errorHandler) {
		ajaxService.get(appConfig.baseUrl + '/private/api/services', {}, function (data) {
			successHandler(data);
		});
	}

	clientService.loadCustomerFollowUps = function (param, successHandler,errorHandler) {
		ajaxService.get(appConfig.baseUrl + '/admin/api/customer/followup/list',param, function (data) {
			successHandler(data);
		});
	}

	clientService.getTotalAmount = function (serviceList) {
		var sum_amount = 0;
		serviceList.forEach(function(service){
			if(service.amount){
				sum_amount = sum_amount + service.amount;
			}
		});
		console.log(serviceList);
		console.log(sum_amount);
		return sum_amount;

	}

		return clientService;
	}]);





