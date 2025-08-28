<#import "layout.ftlh" as l>

<@l.layout title="${restaurant.name}">

    <div class="container mt-4">
        <h2>${restaurant.name}</h2>
        <p>${restaurant.description}</p>

        <div class="row row-cols-1 row-cols-md-2 g-4">
            <#list dishes.content as dish>
                <div class="col">
                    <div class="card h-100">
                        <img src="${dish.imageUrl}" class="card-img-top" alt="${dish.name}" style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${dish.name}</h5>
                            <p class="card-text">${dish.description}</p>
                            <p class="card-text fw-bold">${dish.price} ₽</p>

                            <form method="post" action="/cart/add">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <input type="hidden" name="dishId" value="${dish.id}">
                                <input type="number" name="quantity" value="1" min="1" class="form-control mb-2" style="width: 80px;">
                                <button type="submit" class="btn btn-primary">Добавить в корзину</button>
                            </form>

                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <div class="mt-4 d-flex justify-content-center">
            <nav>
                <ul class="pagination">
                    <#if dishes.hasPrevious()>
                        <li class="page-item">
                            <a class="page-link" href="/restaurants/${restaurant.id}?page=${dishes.number - 1}">← Назад</a>
                        </li>
                    </#if>
                    <li class="page-item active">
                        <span class="page-link">${dishes.number + 1}</span>
                    </li>
                    <#if dishes.hasNext()>
                        <li class="page-item">
                            <a class="page-link" href="/restaurants/${restaurant.id}?page=${dishes.number + 1}">Далее →</a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>

</@l.layout>
