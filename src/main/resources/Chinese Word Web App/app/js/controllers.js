'use strict';

/* Controllers */

var chinesewordControllers = angular.module('chinesewordControllers', []);

chinesewordControllers.controller('ProfileListCtrl', ['$scope', 'Profile', 'WordlistProfile', 'Word',
    function($scope, Profile, WordlistProfile, Word) {
        $scope.profiles = Profile.query();
        $scope.toggleShowWordlists = function(profile){
        	profile.isShowWordlists = !profile.isShowWordlists;
        	if(profile.isShowWordlists){
        		$scope.getWordlistsByProfile(profile);
        		$scope.isShowWords = false;
        	}
        }
        $scope.getWordlistsByProfile = function(profile){
        	profile.wordlists = WordlistProfile.query({profileId:profile.id});
        }
        $scope.showWords = function(profile, wordlist){
        	$scope.isShowWords = true;
        	$scope.words = Word.query({profileId:profile.id, wordlistId:wordlist.id});
        }

    }]);
chinesewordControllers.controller('CoreCtrl', ['$scope', 'Word', 'WordUtils',
    function($scope, Word, WordUtils) {
        $scope.word = Word.get({wordId:5245}, function(word){
            $scope.word.fw = WordUtils.toFormattedWord(word);
        });
    }]);
chinesewordControllers.controller('TestCtrl', ['$scope', 'Test', 'WordlistAll', 'WordlistTest', 'TestWordlist', 'WordUtils',
    function ($scope, Test, WordlistAll, WordlistTest, TestWordlist, WordUtils) {
        $scope.tests = Test.list();
        $scope.isDirty = false;
        $scope.isListReady = false;
        $scope.isGenerateReady = false;
        $scope.isShowPingying = true;
        $scope.showWordlists = function showWordlists(test){
            $scope.isDirty = false;
            $scope.isListReady = false;
            $scope.isGenerateReady = false;
            $scope.test = Test.get({testId:test.id}, function (response) {
                $scope.wordlists = WordlistAll.query(function (response) {
                    angular.forEach(response, function (item) {
                        item.selected = $scope.isExistingWordlist(item);
                        $scope.updateStatus();
                    });
                });
            });
        }
        $scope.isExistingWordlist = function isExistingWordlist(wordlist){
            for(var i=0; i< $scope.test.wordlists.length; i++){
                if(wordlist.id == $scope.test.wordlists[i].id){
                    return true;
                }
            }
            return false;
        }
        $scope.getSelectedIdList = function getSelectedIdList(){
            var list = [];
            for(var i = 0; i < $scope.wordlists.length; i++){
                if($scope.wordlists[i].selected){
                    list[list.length] = $scope.wordlists[i].id;
                }
            }
            return list;
        }
        $scope.getExistingIdList = function getExistingIdList(){
            var list = [];
            for(var i = 0; i < $scope.test.wordlists.length; i++){
                list[list.length] = $scope.test.wordlists[i].id;
            }
            return list;
        }
        $scope.selectWordlist = function selectWordlist(wordlist){
            wordlist.selected = !wordlist.selected;
            $scope.updateStatus();
        }
        $scope.clickIsShowPingying = function clickIsShowPingying(){
            $scope.isShowPingying = !$scope.isShowPingying;
        }
        $scope.updateStatus = function updateStatus(){
            if($scope.wordlists.length == 0){
                $scope.isDirty = false;
                $scope.isGenerateReady = false;
                $scope.isListReady = false;
                return;
            }
            var selIdList = $scope.getSelectedIdList();
            var curIdList = $scope.getExistingIdList();
            if(selIdList.length != curIdList.length){
                $scope.isDirty = true;
                $scope.isListReady = false;
                $scope.setGenerateControls($scope.isListReady);
                return;
            }
            // remove matching entries from both selIdList and curIdList
            // if the remaining lists do not match, then the overall selection has changed
            // thus it is dirty and save button should be enabled.
            for(var i=(selIdList.length-1); i>=0 ; i--){
                var index = curIdList.indexOf(selIdList[i]);
                if (index > -1) {
                    curIdList.splice(index, 1);
                    selIdList.splice(i, 1);
                }
            }
            $scope.isDirty = !(curIdList.length == 0 && selIdList.length == 0);
            $scope.isListReady = (!$scope.isDirty && $scope.getExistingIdList().length > 0);
            $scope.setGenerateControls($scope.isListReady);
        }
        $scope.save = function save(){
            $scope.test.wordlists = [];
            for(var i = 0; i < $scope.wordlists.length; i++){
                if($scope.wordlists[i].selected){
                    $scope.test.wordlists.push($scope.wordlists[i]);
                    $scope.test.wordlists[$scope.test.wordlists.length - 1].selected = undefined;
                }
            }
            $scope.test.$update({testId:$scope.test.id}, function(response){
                $scope.showWordlists($scope.test);
            });
        }
        $scope.list = function list(){
            var words = [];
            for(var i = 0; i < $scope.test.wordlists.length; i++){
                for(var j = 0; j < $scope.test.wordlists[i].words.length; j++){
                    var word = $scope.test.wordlists[i].words[j];
                    word.formatted_word = WordUtils.toFormattedWord(word);//WordUtils
                    words.push(word);
                }
            }
            $scope.words = words;
            $scope.showWords();
        }
        $scope.generate = function generate(){
            // this should generate random words, and should have additional
            // parameters limit max number of words
            var words = [];
            for(var i = 0; i < $scope.test.wordlists.length; i++){
                for(var j = 0; j < $scope.test.wordlists[i].words.length; j++){
                    var word = $scope.test.wordlists[i].words[j];
                    word.formatted_word = WordUtils.toFormattedWord(word);//WordUtils
                    words.push(word);
                }
            }
            $scope.words = words;
            $scope.showWords();
        }
        $scope.showWords = function showWords(){
            $scope.isShowWords = true;
        }
        $scope.setGenerateControls = function setGenerateControls(bool){
            $scope.isGenerateReady = bool;
        }
    }]);

chinesewordControllers.controller('WordlistListCtrl', ['$scope', 'WordlistProfile',
    function($scope, Wordlist) {
    $scope.getWordlistsByProfileId = function(id){
    	$scope.wordlists = Wordlist.query({profileId:id});
    }
    }]);
chinesewordControllers.controller('WordEditCtrl', ['$scope', '$routeParams', 'Word',
    function($scope, $routeParams, Word) {
        $scope.wordToEdit = Test.find(word);
        $scope.words = Word.query();
        for(var i=0, len=$scope.words.length; i<len; i++){

        }
    }]);
chinesewordControllers.controller('ProfileCtrl', ['$scope', 'Word',
    function($scope, Word) {
        $scope.words = Word.query();
    }]);
