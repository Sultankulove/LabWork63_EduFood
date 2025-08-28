<#import "layout.ftlh" as l>


<@l.layout title="Заведения">
<div class="container mt-4">
    <h1 class="mb-4">Заведения</h1>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list restaurants.content as restaurant>
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="${restaurant.imageUrl}" class="card-img-top" alt="${restaurant.name}" style="height: 200px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${restaurant.name}</h5>
                        <p class="card-text">${restaurant.description}</p>
                        <a href="/restaurants/${restaurant.id}" class="btn btn-primary">Смотреть меню</a>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <div class="mt-4 d-flex justify-content-center">
        <nav>
            <ul class="pagination">
                <#if restaurants.hasPrevious()>
                    <li class="page-item">
                        <a class="page-link" href="/?page=${restaurants.number - 1}">← Назад</a>
                    </li>
                </#if>
                <li class="page-item active">
                    <span class="page-link">${restaurants.number + 1}</span>
                </li>
                <#if restaurants.hasNext()>
                    <li class="page-item">
                        <a class="page-link" href="/?page=${restaurants.number + 1}">Далее →</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
</div>
</@l.layout>
