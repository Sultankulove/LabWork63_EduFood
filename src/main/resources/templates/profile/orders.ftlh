<#import "../layout.ftlh" as l>

<@l.layout title="История заказов">
    <div class="container mt-4">
        <h2>История заказов</h2>

        <#if orders?size == 0>
            <div class="alert alert-info">У вас пока нет заказов.</div>
        <#else>
            <#list orders as order>
                <div class="card mb-3">
                    <div class="card-header">
                        <div class="d-flex justify-content-between">
                            <div>
                                <strong>Заказ №${order.id}</strong>
                                <span class="text-muted">от ${order.createdAt}</span>
                            </div>
                            <div>
                                Сумма: <strong>${order.totalPrice} сом</strong>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush">
                        <#list order.orderItems as item>
                            <li class="list-group-item">
                                ${item.dish.name} (${item.dish.restaurant.name}) — ${item.quantity} шт.
                            </li>
                        </#list>
                    </ul>
                </div>
            </#list>
        </#if>
    </div>
</@l.layout>
