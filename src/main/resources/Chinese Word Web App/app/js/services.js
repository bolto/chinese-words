'use strict';

/* Services */

var chinesewordServices = angular.module('chinesewordServices', ['ngResource']);

chinesewordServices.factory('Profile', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/profiles/', {}, {
            query: {method:'GET', params:{}, isArray:true}
        });
    }]);

chinesewordServices.factory('Test', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/tests/:testId', {}, {
            list: {method:'GET', params:{entryId:''}, isArray:true},
            get: {method:'GET', params:{entryId:'@entryId'}, isArray:false},
            post:{method:'POST', params:{entryId:''}},
            update: {method:'PUT', params:{entryId:'@entryId'}},
            delete:{method:'DELETE', params:{entryId:'@entryId'}}
        });
    }]);
chinesewordServices.factory('Word', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/words/', {}, {
            list: {method:'GET', params:{search:'@search'}, isArray:true},
            get: {method:'GET', params:{wordId:'@wordId'}, isArray:false},
            post:{method:'POST', params:{wordId:''}},
            update: {method:'PUT', params:{wordId:'@wordId'}},
            delete:{method:'DELETE', params:{wordId:'@wordId'}}
        });
    }]);
chinesewordServices.factory('WordPingying', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/wordpingyings/', {}, {
            list: {method:'GET', params:{word:'@word'}, isArray:true}
        });
    }]);
chinesewordServices.factory('TestWordlist', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/tests/:testId/wordlists', {}, {
            list: {method:'GET', params:{entryId:''}, isArray:true},
            get: {method:'GET', params:{entryId:'@entryId'}, isArray:false},
            post:{method:'POST', params:{entryId:''}},
            update: {method:'PUT', params:{entryId:'@entryId'}},
            delete:{method:'DELETE', params:{entryId:'@entryId'}}
        });
    }]);

chinesewordServices.factory('PingyingCharacter', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/pingying_characters', {}, {
            list: {method:'GET', params:{}, isArray:true}
        });
    }]);
chinesewordServices.factory('Tone', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/tones', {}, {
            list: {method:'GET', params:{}, isArray:true}
        });
    }]);

chinesewordServices.factory('WordlistProfile', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/profiles/:profileId/wordlists/',
            {}, {
            query: {method:'GET', params:{profileId:'@profileId'}, isArray:true}
        });
    }
]);

chinesewordServices.factory('WordlistTest', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/tests/:testId/wordlists/',
            {}, {
                query: {method:'GET', params:{testId:'@testId'}, isArray:true}
            });
    }
]);

chinesewordServices.factory('WordlistAll', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/wordlists/',
            {}, {
                query: {method:'GET', params:{}, isArray:true}
            });
    }
]);

chinesewordServices.factory('WordlistTest', ['$resource',
    function($resource){
        return $resource('http://localhost:8080/api/tests/:testId/wordlists/',
            {}, {
                query: {method:'GET', params:{}, isArray:true}
            });
    }
]);
