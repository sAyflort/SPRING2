angular.module('market').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/auth/';

    $scope.funcRegistration = function () {
        $http.post(contextPath + 'registration', $scope.regUser).then(function (response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.marchMarketUser = {username: $scope.regUser.username, token: response.data.token};
                $localStorage.regUser = null;
                $location.path('/');
            }
        });
    }
});