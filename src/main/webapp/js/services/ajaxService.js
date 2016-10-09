
var myApp = angular.module('RoutingApp');
myApp.factory('ajaxService', ['$http','$location','appConfig', function($http,$location,appConfig) {

	var ajaxService = {};
	console.log(appConfig);
	ajaxService.get = function(url, params, successCallback, errorCallback){
		$http({
			url: url,
			method: "GET",
			params: params
			}).success(function(data){
				if(successCallback){
					successCallback(data);
				}

		}).error(function(data, status){
			console.log("error:" + data);
			if(data == 'USER_IS_UN_AUTHORIZED'){
				$location.path('/login');
			}

			if(errorCallback){
				errorCallback(data, status);
			}
		});

	}
    ajaxService.post = function(url, data, successCallback, errorCallback){
        $http({
            url: url,
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            data: data
        }).success(function(data){
                if(successCallback){
                    successCallback(data);
                }

            }).error(function(data, status){
				console.log("error:" + data);
				if(data == 'USER_IS_UN_AUTHORIZED'){
					$location.path('/login');
				}

				if(errorCallback){
					errorCallback(data, status);
				}
            });
    }

        return ajaxService;
	}]);



