<!DOCTYPE html>
<html>

<head>
    <title>Template chooser</title>

    <#include "../util/head.ftl">

        <link rel="stylesheet" href="Default_Light/css/template-chooser.css">
        <link rel="stylesheet" href="Default_Dark/css/template-chooser.css">

</head>

<body>

    <#include "../util/nav.ftl">

        <main>

            <form id="template-chooser" method="post" action="/template-chooser">
                <h1>Choose a template</h1>
                <div id="template-container">
                </div>
                <input type="hidden" name="template" id="selected-template">
                <input type="submit" value="Submit" id="submit-button" disabled>
            </form>
            <!-- Jquery -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="/js/template-chooser.js"></script>
        </main>


</body>

</html>