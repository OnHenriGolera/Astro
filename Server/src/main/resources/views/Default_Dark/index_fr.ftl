<!DOCTYPE html>
<html>

<head>
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="Default_Light/css/index.css">
    <link rel="stylesheet" type="text/css" href="Default_Dark/css/index.css">

    <#include "util/head.ftl">
</head>

<body>

    <#include "util/nav_en.ftl">

        <main>

            <section id="top">
                <h1 class="astro">Astro'</h1>
                <h2>Un projet simple, open-source, et gratuit pour gérer vos compétitions.</h2>
                <p class="name">ForkBench.</p>
                <a href="#presentation"><img src="/img/down-arrow.svg" alt="Down arrow" id="down-arrow"></a>
            </section>

            <section id="presentation">

                <article>
                    <h3>Qu'est-ce qu'<mark><i id="mark1">Astro'</i></mark> ?</h3>
                    <p><i>Astro'</i> est une idée d'application web pour gérer vos compétitions. Le but est de la rendre gratuite, et facile à utiliser. De plus, elle est open-source, donc vous pouvez contribuer au projet si vous le souhaitez : <strong>créons un projet ensemble</strong> !</p>
                </article>


                <article>

                    <h3>Comment il a été fait ?</h3>
                    <p>Ce projet a été fait avec Java en utilisant le framework <mark><a href="https://sparkjava.com" id="mark2">Spark</a></mark> : un serveur héberge l'application, et le client peut y accéder via un navigateur web. La base de données est stockée localement avec <mark><a href="https://www.h2database.com/" id="mark2">H2</a></mark>.</p>

                </article>

            </section>

        </main>

</body>

</html>