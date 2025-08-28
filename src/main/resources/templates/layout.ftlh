<#import "spring.ftl" as spring>

<#macro layout title="EduFood — Главная">
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">EduFood</a>
            <div class="d-flex">

                            <#if SPRING_SECURITY_CONTEXT??
                            && SPRING_SECURITY_CONTEXT.authentication??
                            && SPRING_SECURITY_CONTEXT.authentication.principal?has_content
                            && SPRING_SECURITY_CONTEXT.authentication.principal != 'anonymousUser'>

                                <a href="/cart" class="btn btn-outline-primary me-2">
                                    🛒 Корзина
                                </a>
                                <a href="/profile" class="btn btn-outline-secondary me-2">Профиль</a>
                                <form action="/logout" method="post" class="d-inline">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-danger">Выйти</button>
                                </form>

                            <#else>
                                <a href="/auth/login" class="btn btn-outline-success me-2">Вход</a>
                                <a href="/auth/register" class="btn btn-primary">Регистрация</a>
                            </#if>
            </div>
        </div>



    </nav>

    <main class="container content">
        <#nested>
    </main>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>