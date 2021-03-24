<html>
    <#include "./stylesheet.css">

    <head>
        <title> ${name} </title>
    </head>

    <body>
        <h1>
            ${name}
        </h1>

        <h3>
            <i> Found at: ${path} </i>
        </h3>

        <ul>
            <#list items as item>
                <li> ${item.getName()} (${item.getClass().getSimpleName()}) </li>
            </#list>
        </ul>

    </body>
</html>