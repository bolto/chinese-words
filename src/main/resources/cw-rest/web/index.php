<?php
require_once __DIR__ . '/../vendor/autoload.php';

$app = new Silex\Application();

$app->run();

$app->get('/', function(){
    return "Hello world";
});