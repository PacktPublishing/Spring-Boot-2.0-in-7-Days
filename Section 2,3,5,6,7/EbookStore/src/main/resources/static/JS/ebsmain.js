/**
 * 
 */

var app =  angular.module("EBookStoreManagement", []);

app.controller("BookController", function($scope, $http)
{
	$scope.booklist=[];
	
	_getbooklist();
	
	function _getbooklist()
	{
		$http({
            method: 'GET',
            url: '/rest/books'
        }).then(
            function(res) { // success
                $scope.booklist = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
	}
});

app.controller("PersonController", function($scope, $http)
{
	$scope.currentPerson = {
		id:-1,
		firstName:"",
		lastName:"",
		username:"",
		password:"",
		userRole:""
	}
	
	function createPerson()
	{
		$http({
			 method: 'POST',
	            url: '/rest/person/new',
	            data: angular.toJson($scope.currentPerson),
	            headers: {
	                'Content-Type': 'application/json'
	            }
		}).then(
			function(res)
			{
				console.log(res.data);
				$scope.currentPerson = res.data;
				$location.href="author.html";
			},
			function(res)
			{
				console.log("Error:"+res.status + ":" + res.data);
			}
		);
	}
});