<#import "../layout.ftlh" as l>

<@l.layout title="Регистрация">

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header text-center bg-primary text-white">
                        <h4>Регистрация</h4>
                    </div>
                    <div class="card-body">
                        <form method="post" action="/auth/register">
                            <div class="mb-3">
                                <label for="username" class="form-label">Имя пользователя</label>
                                <input type="text" id="username" name="username" class="form-control"
                                       value="${registerDto.username!}">
                                <#if errors?? && errors.hasFieldErrors("username")>
                                    <div class="text-danger mt-1">
                                        ${errors.getFieldError("username").defaultMessage}
                                    </div>
                                </#if>
                            </div>

                            <div class="mb-3">
                                <#if registrationError??>
                                    <div class="alert alert-danger" role="alert">
                                        ${registrationError}
                                    </div>
                                </#if>
                                <label for="email" class="form-label">Email</label>
                                <input type="email" id="email" name="email" class="form-control"
                                       value="${registerDto.email!}">
                                <#if errors?? && errors.hasFieldErrors("email")>
                                    <div class="text-danger mt-1">
                                        ${errors.getFieldError("email").defaultMessage}
                                    </div>
                                </#if>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Пароль</label>
                                <input type="password" id="password" name="password" class="form-control">
                                <#if errors?? && errors.hasFieldErrors("password")>
                                    <div class="text-danger mt-1">
                                        ${errors.getFieldError("password").defaultMessage}
                                    </div>
                                </#if>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                            </div>
                        </form>

                        <div class="text-center mt-3">
                            Уже есть аккаунт? <a href="/auth/login">Войти</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@l.layout>
