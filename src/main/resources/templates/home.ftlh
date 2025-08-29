<#import "layout.ftlh" as l>


<@l.layout title="Заведения">
<div class="container mt-4">
    <h1 class="mb-4">Заведения</h1>


    <form method="get" action="/search" class="mb-4">
        <div class="input-group">
            <input type="text" class="form-control" name="name" placeholder="Поиск заведений" value="${searchQuery!}">
            <button class="btn btn-outline-secondary" type="submit">Поиск</button>
        </div>
    </form>

    <#if restaurants.totalElements == 0>
        <div class="alert alert-info" role="alert">
            <strong>Внимание!</strong> Заведения по вашему запросу не найдены.
            <a href="/" class="alert-link">Вернуться ко всем заведениям</a>.
        </div>
    <#else>
        <div class="row row-cols-1 g-4">
            <#list restaurants.content as restaurant>
                <div class="col">
                    <div class="card h-100 shadow-sm">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <img src="images/download/${restaurant.imageUrl}" class="card-img-top" alt="${restaurant.name}" style="height: 400px; object-fit: cover;">
<#--                        <img src="/images/download?filename=${restaurant.imageUrl}" class="card-img-top" alt="${restaurant.name}" style="height: 400px; object-fit: cover;">-->

                        <div class="card-body">
                            <h5 class="card-title">${restaurant.name}</h5>
                            <p class="card-text">${restaurant.description}</p>
                            <a href="/restaurants/${restaurant.id}" class="btn btn-primary">Смотреть меню</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <#if restaurants.totalPages gt 1>
            <div class="mt-4 d-flex justify-content-center">
                <nav>
                    <ul class="pagination">
                        <#if restaurants.hasPrevious()>
                            <li class="page-item">
                                <a class="page-link" href="?name=${searchQuery!}&page=${restaurants.number - 1}">← Назад</a>
                            </li>
                        </#if>
                        <li class="page-item active">
                            <span class="page-link">${restaurants.number + 1}</span>
                        </li>
                        <#if restaurants.hasNext()>
                            <li class="page-item">
                                <a class="page-link" href="?name=${searchQuery!}&page=${restaurants.number + 1}">Далее →</a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </div>
        </#if>

    </#if>
</div>
</@l.layout>
