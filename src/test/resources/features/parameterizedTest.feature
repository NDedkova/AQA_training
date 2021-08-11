#language:ru
#encoding:UTF-8

@test @price
Функционал: Параметризированный запуск тестов

  Структура сценария: Проверка цены товара <productName>
    Допустим открыта страница "https://www.saucedemo.com/"
    И введен логин
    И введен пароль
    И нажата кнопка входа
    И выполнено нажатие на ссылку "<productName>"
    Тогда цена товара равна "<productPrice>"

    Примеры:
      | productName             | productPrice |
      | Sauce Labs Backpack     | $29.99       |
      | Sauce Labs Bike Light   | $9.99        |
      | Sauce Labs Bolt T-Shirt | $15.99       |