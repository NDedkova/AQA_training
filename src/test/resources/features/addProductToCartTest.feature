#language: ru
#encoding: UTF-8

  @test @addTest
  Функционал: Непараметризированный запуск тестов

    Сценарий: Добавление товара в корзину
      Допустим открыта страница "https://www.saucedemo.com/"
      И введен логин
      И введен пароль
      И нажата кнопка входа
      И выбран товар
      Тогда в корзине есть товар