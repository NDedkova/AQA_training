#language: ru
  #encoding: UTF-8

  @test @negative
  Функционал: Негативный запуск тестов

    Сценарий: Ввод неверного пароля
      Допустим открыта страница "https://www.saucedemo.com/"
      И введен логин
      И введен "неверный пароль"
      И нажата кнопка входа
      Тогда вход на страницу не осуществлен