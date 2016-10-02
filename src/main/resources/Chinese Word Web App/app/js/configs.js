'use strict';

/* App Module */

var chinesewordConfigs = angular.module('chinesewordConfigs', []);

chinesewordConfigs.config(['$routeProvider', '$locationProvider',
  function($routeProvider, $locationProvider) {
    $routeProvider.
        when('/profiles', {
            templateUrl: 'partials/profiles_div.html',
            controller: 'ProfileCtrl'
        }).
        when('/wordlists/new', {
            templateUrl: 'partials/new_wordlist_form.html',
            controller: 'WordlistFormCtrl'
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
