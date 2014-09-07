'use strict';

/* Directives */
var chinesewordDirectives = angular.module('chinesewordDirectives', []);

chinesewordDirectives.directive('aDisabled', function() {
    return {
        compile: function(tElement, tAttrs, transclude) {
            //Toggle "disabled" to class when aDisabled becomes true
            return function (scope, iElement, iAttrs) {
                scope.$watch(iAttrs["aDisabled"], function(newValue) {
                    if (newValue !== undefined) {
                        iElement.toggleClass("disabled", newValue);
                    }
                });
            };
        }
    };
});
