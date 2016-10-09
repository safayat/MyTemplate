
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


/*
	authService.isAdmin = function (){
		var loginInfo = getLoginInfo();

	};
*/
		return authService;
	}]);



