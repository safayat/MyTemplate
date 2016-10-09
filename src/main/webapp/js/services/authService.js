
var myApp = angular.module('RoutingApp');
myApp.factory('authService', ['$http','localStorageService','appConfig','$rootScope','userservice',
	function($http,localStorageService,appConfig,$rootScope,userservice) {

	var authService = {};
	function getLoginInfo(){
		return localStorageService.get("logged_in_user");
	}
	authService.saveLoginInfo = function (loginInfo){
		localStorageService.set("logged_in_user", loginInfo);
	};
	authService.getLoginInfo = function (){
		return getLoginInfo();
	};

	authService.isLoggedIn = function (){
		var loginInfo = getLoginInfo();
		return loginInfo != undefined && loginInfo != null;
	};

	authService.init = function (){
		var loginInfo = getLoginInfo();
		if(loginInfo){
			$rootScope.username = loginInfo.username;
		}
	};

	authService.isSuperAdmin = function (){
		var loginInfo = getLoginInfo();
		var superAdmin = false;
		loginInfo.roleList.forEach(function(role){
			if(role.roleName == 'ROLE_SUPER'){
				superAdmin = true;
			}
		});
		return superAdmin;
	};
	authService.reload = function (){
		userservice.getCurrentUser(function(data){
			console.log(data);
			authService.saveLoginInfo(data);
		});
	};
	authService.isAdmin = function (){
		var loginInfo = getLoginInfo();
		var admin = false;
		loginInfo.roleList.forEach(function(role){
			if(role.roleName == 'ROLE_ADMIN'){
				admin = true;
			}
		});
		return admin;
	};

	authService.getClientFields = function (){
		var loginInfo = getLoginInfo();
		console.log(loginInfo);
		if(loginInfo){
			var clientFieldAsStr = loginInfo.selectedClientFields;
			if(!clientFieldAsStr || clientFieldAsStr == ""){
				return undefined;
			}
			var clientFields = {};
			var splittedClientFields = clientFieldAsStr.split(" ");
			console.log(clientFieldAsStr);
			for(var i=0;i<splittedClientFields.length;i++){
				clientFields[splittedClientFields[i]] = true;
			}
			return clientFields;
		}
		return undefined;
	};

	authService.getTicketFields = function (){
		var loginInfo = getLoginInfo();
		console.log(loginInfo);
		if(loginInfo){
			var ticketFieldAsStr = loginInfo.selectedTicketFields;
			if(!ticketFieldAsStr || ticketFieldAsStr == ""){
				return undefined;
			}
			var ticketFields = {};
			var splittedTicketFields = ticketFieldAsStr.split(" ");
			console.log(ticketFieldAsStr);
			for(var i=0;i<splittedTicketFields.length;i++){
				ticketFields[splittedTicketFields[i]] = true;
			}
			return ticketFields;
		}
		return undefined;
	};

/*
	authService.isAdmin = function (){
		var loginInfo = getLoginInfo();

	};
*/
		return authService;
	}]);



