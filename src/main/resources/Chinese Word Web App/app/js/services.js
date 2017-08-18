'use strict';

/* Services */

var chinesewordServices = angular.module('chinesewordServices', ['ngResource']);
var server_url =  window.location.protocol + '//' + window.location.hostname + ':8080'
chinesewordServices.factory('Profile', ['$resource',
    function($resource){
        return $resource(server_url + '/api/profiles/', {}, {
            query: {method:'GET', params:{}, isArray:true}
        });
    }]);

chinesewordServices.factory('Test', ['$resource',
    function($resource){
        return $resource(server_url + '/api/tests/:testId', {}, {
            list: {method:'GET', params:{entryId:''}, isArray:true},
            get: {method:'GET', params:{entryId:'@entryId'}, isArray:false},
            post:{method:'POST', params:{entryId:''}},
            update: {method:'PUT', params:{entryId:'@entryId'}},
            "delete":{method:'DELETE', params:{entryId:'@entryId'}}
        });
    }]);
chinesewordServices.factory('Word', ['$resource',
    function($resource){
        return $resource(server_url + '/api/words/', {}, {
            list: {method:'GET', params:{search:'@search'}, isArray:true},
            get: {method:'GET', params:{wordId:'@wordId'}, isArray:false},
            post:{method:'POST', params:{wordId:''}},
            update: {method:'PUT', params:{wordId:'@wordId'}},
            "delete":{method:'DELETE', params:{wordId:'@wordId'}}
        });
    }]);
chinesewordServices.factory('Wordlist', ['$resource',
    function($resource){
        return $resource(server_url + '/api/wordlists/:wordlistId/', {name:'@name', words:'@words'}, {
            post: {method:'POST', params:{name:'@name', words:'@words'}}
        });
    }]);
chinesewordServices.factory('WordPingying', ['$resource',
    function($resource){
        return $resource(server_url + '/api/wordpingyings/', {}, {
            list: {method:'GET', params:{word:'@word'}, isArray:true},
            update: {method:'PUT', params:{}}
        });
    }]);
chinesewordServices.factory('WordlistWord', ['$resource',
    function($resource){
        return $resource(server_url + '/api/wordlistword/:id', {}, {
            get: {method:'GET', params:{id:'@id'}},
            update: {method:'PUT', params:{id:'@id'}}
        });
    }]);
chinesewordServices.factory('TestWordlist', ['$resource',
    function($resource){
        return $resource(server_url + '/api/tests/:testId/wordlists', {}, {
            list: {method:'GET', params:{entryId:''}, isArray:true},
            get: {method:'GET', params:{entryId:'@entryId'}, isArray:false},
            post:{method:'POST', params:{entryId:''}},
            update: {method:'PUT', params:{entryId:'@entryId'}},
            "delete":{method:'DELETE', params:{entryId:'@entryId'}}
        });
    }]);

chinesewordServices.factory('PingyingCharacter', ['$resource',
    function($resource){
        return $resource(server_url + '/api/pingying_characters', {}, {
            list: {method:'GET', params:{}, isArray:true}
        });
    }]);
chinesewordServices.factory('Tone', ['$resource',
    function($resource){
        return $resource(server_url + '/api/tones', {}, {
            list: {method:'GET', params:{}, isArray:true}
        });
    }]);

chinesewordServices.factory('WordlistProfile', ['$resource',
    function($resource){
        return $resource(server_url + '/api/profiles/:profileId/wordlists/',
            {}, {
            query: {method:'GET', params:{profileId:'@profileId'}, isArray:true}
        });
    }
]);

chinesewordServices.factory('WordlistTest', ['$resource',
    function($resource){
        return $resource(server_url + '/api/tests/:testId/wordlists/',
            {}, {
                query: {method:'GET', params:{testId:'@testId'}, isArray:true}
            });
    }
]);

chinesewordServices.factory('WordlistAll', ['$resource',
    function($resource){
        return $resource(server_url + '/api/wordlists/',
            {}, {
                list: {method:'GET', params:{}, isArray:true}
            });
    }
]);

chinesewordServices.factory('WordlistTest', ['$resource',
    function($resource){
        return $resource(server_url + '/api/tests/:testId/wordlists/',
            {}, {
                query: {method:'GET', params:{}, isArray:true}
            });
    }
]);
