<#import "layout.ftlh" as l>

<@l.layout title="Корзина">

    <div class="container mt-4">
        <h2>Ваша корзина</h2>

        <#if cartItems?size == 0>
            <div class="alert alert-info">Ваша корзина пуста.</div>
        <#else>
            <table class="table">
                <thead>
                <tr>
                    <th>Блюдо</th>
                    <th>Цена</th>
                    <th>Количество</th>
                    <th>Итого</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#assign total = 0>
                <#list cartItems as item>
                    <#assign dish = item.dish>
                    <#assign quantity = item.quantity>
                    <#assign lineTotal = dish.price * quantity>
                    <tr>
                        <td>${dish.name}</td>
                        <td>${dish.price?string.currency}</td>
                        <td>${quantity}</td>
                        <td>${lineTotal?string.currency}</td>
                        <td>
                            <form method="post" action="/cart/remove" style="display:inline;">
                                <input type="hidden" name="dishId" value="${dish.id}">
                                <button type="submit" class="btn btn-sm btn-danger">Удалить</button>
                            </form>
                        </td>
                    </tr>
                    <#assign total += lineTotal>
                </#list>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="3" class="text-end">Итого:</th>
                    <th>${total?string.currency}</th>
                    <th></th>
                </tr>
                </tfoot>
            </table>
        </#if>
    </div>

</@l.layout>
