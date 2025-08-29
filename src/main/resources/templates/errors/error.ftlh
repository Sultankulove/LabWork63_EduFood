<#import "../layout.ftlh" as l>

<@l.layout title="Ошибка ${status}">
    <div class="container mt-5">
        <div class="alert alert-danger shadow-sm p-4 rounded">

            <h1 class="display-5">Ошибка ${status}: ${reason!}</h1>

            <#if message?? && message?length != 0>
                <p class="mt-3"><strong>Описание:</strong> ${message}</p>
            </#if>

            <#if details??>
                <p class="text-muted"><strong>Запрошенный путь:</strong> ${details}</p>
            </#if>


            <a href="/" class="btn btn-outline-primary mt-4">Вернуться на главную</a>
        </div>
    </div>
</@l.layout>
