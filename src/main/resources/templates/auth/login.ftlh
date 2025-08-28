<#import "../layout.ftlh" as l>
<@l.layout title="Вход">

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header text-center bg-primary text-white">
                        <h4>Вход</h4>
                    </div>
                    <div class="card-body">
                        <form method="post" action="/auth/login">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <#if error??>
                                <div class="alert alert-danger text-center">
                                    Неверный email или пароль
                                </div>
                            </#if>


                            <div class="mb-3">
                                <label for="username" class="form-label">Email или имя пользователя</label>
                                <input type="text" id="username" name="username" class="form-control">
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Пароль</label>
                                <input type="password" id="password" name="password" class="form-control">
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Войти</button>
                            </div>
                        </form>

                        <div class="text-center mt-3">
                            Нет аккаунта? <a href="/auth/register">Зарегистрироваться</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@l.layout>
