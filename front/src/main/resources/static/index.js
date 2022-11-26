angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    // Авторизация
    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.springMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        return $localStorage.springMarketUser;
    };

    $scope.authCheck = function () {
        $http.get('http://localhost:5555/auth/auth_check')
            .then(function (response) {
                alert(response.data.value);
            });
    };

    if ($localStorage.springMarketUser) {
        try {
            let jwt = $localStorage.springMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token s expired!!!");
                delete $localStorage.springMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springMarketUser.token;
    }

    // Таблица продуктов
    $scope.fillTable = function () {
        /*let url = new URL('http://localhost:5555/core/api/v1/products');
        let params = url.searchParams;
        $scope.filters.titleFilter.toString();*/
        console.log();
        $scope.filte
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:5555/core/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    // Таблица корзины
    $scope.fillCartTable = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.deleteProductOfCart = function (id) {
        $http.delete('http://localhost:5555/cart/api/v1/cart/delete/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.changeQuantityOfProduct = function (id, change) {
        $http.get('http://localhost:5555/cart/api/v1/cart/change/' + id + '/' + change)
            .then(function (response){
                $scope.fillCartTable();
            });
    }

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.dropCartsProducts = function () {
        $http.delete('http://localhost:5555/cart/api/v1/cart/clear')
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    // Работа с заказом
    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders', $scope.orderDetails)
            .then(function (response){
                alert("Заказ сделан")
                $scope.fillCartTable();
            });
    }

    $scope.fillTable();
    $scope.fillCartTable();
});