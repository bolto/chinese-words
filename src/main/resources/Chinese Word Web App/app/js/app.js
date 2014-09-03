'use strict';

/* App Module */

var chinesewordApp = angular.module('chinesewordApp', [
  'ngRoute',
  'chinesewordControllers',
  'chinesewordServices',
  'chinesewordCommonUtils'
]);
chinesewordApp.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i=0; i<total; i++)
            input.push(i);
        return input;
    };
});
chinesewordApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
        when('/profiles', {
            templateUrl: 'partials/profiles_div.html',
            controller: 'ProfileCtrl'
        }).
        when('/tests', {
            templateUrl: 'partials/test.html',
            controller: 'TestCtrl'
        }).
        when('/edits', {
            templateUrl: 'partials/word_edit_prototype.html',
            controller: 'WordEditCtrl'
        }).
        when('/profiles/:profileId/wordlists', {
            templateUrl: 'partials/wordlist-list.html',
            controller: 'WordlistListCtrl'
        }).
        otherwise({
            redirectTo: '/profiles'
      });
  }]);
