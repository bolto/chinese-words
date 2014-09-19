'use strict';

/* App Module */

var chinesewordApp = angular.module('chinesewordApp', [
  'ngRoute',
  'chinesewordControllers',
  'chinesewordServices',
  'chinesewordCommonUtils',
  'chinesewordDirectives',
  'ui.bootstrap'
]);
var ModalDemoCtrl = function ($scope, $modal, $log) {

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.open = function (size) {

        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: ModalInstanceCtrl,
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
};

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {

    $scope.items = items;
    $scope.selected = {
        item: $scope.items[0]
    };

    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};
chinesewordApp.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i=0; i<total; i++)
            input.push(i);
        return input;
    };
});
chinesewordApp.config(['$routeProvider', '$locationProvider',
  function($routeProvider, $locationProvider) {
    $routeProvider.
        when('/profiles', {
            templateUrl: 'partials/profiles_div.html',
            controller: 'ProfileCtrl'
        }).
        when('/tests', {
            templateUrl: 'partials/test.html',
            controller: 'TestCtrl'
        }).
        when('/tests2', {
            templateUrl: 'partials/wl_word_py_select_test.html',
            controller: 'Test2Ctrl'
        }).
        when('/edits', {
            templateUrl: 'partials/word_edit_prototype.html',
            controller: 'WordEditCtrl'
        }).
        when('/wordlist_word', {
            templateUrl: 'partials/wl_word_py_select.html',
            controller: 'WordlistWordEditCtrl'
        }).
        when('/wordlistwords', {
            templateUrl: 'partials/wordlist_word.html',
            controller: 'WordlistWordCtrl'
        }).
        otherwise({
            redirectTo: '/tests'
      });
      //$locationProvider.html5Mode(true);
  }]);
