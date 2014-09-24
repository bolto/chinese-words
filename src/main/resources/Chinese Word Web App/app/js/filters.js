'use strict';

/* Filters */
var chinesewordFilters = angular.module('chinesewordFilters', []);
chinesewordFilters.filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i=0; i<total; i++)
            input.push(i);
        return input;
    };
});