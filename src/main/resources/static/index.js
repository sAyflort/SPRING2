angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    }

    $scope.fillCartTable = function () {
        $http.get('http://localhost:8189/market/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.deleteProductOfCart = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/cart/delete/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.changeQuantityOfProduct = function (id, change) {
        $http.get('http://localhost:8189/market/api/v1/cart/changeQuantity/' + id + '/' + change)
            .then(function (response){
                $scope.fillCartTable();
            });
    }

    $scope.addToCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.dropCartsProducts = function () {
        $http.delete('http://localhost:8189/market/api/v1/cart/delete')
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.fillTable();
    $scope.fillCartTable();
});