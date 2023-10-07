<!DOCTYPE html>
<html>

<head>
    <title>Player list</title>
    <link rel="stylesheet" type="text/css" href="Default_Light/css/player-list.css">

    <#include "../util/head.ftl">
</head>

<body>

<#include "../util/nav_en.ftl">

<main>

    <h1>Player list</h1>

    <#--  Search bar  -->
    <div id="search-bar">
        <input type="text" id="search-input" placeholder="Search for players...">
    </div>


</main>

<#-- Jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="module" src="/js/player-list.js"></script>


</body>

</html>