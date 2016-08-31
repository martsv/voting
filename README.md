Voting System
=============

Voting system for deciding where to have a lunch

JSON API using Hibernate/Spring/SpringMVC

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
- If it is before 11:00 we asume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day.

## Описание REST-интерфейса

### Рестораны

- Список ресторанов
  * GET /rest/restaurants
- Информация по заданному ресторану
  * GET /rest/restaurants/{restaurant_id}
- Удаление ресторана (ROLE_ADMIN)
  * DELETE /rest/restaurants/{restaurant_id}
- Изменение информации о ресторане (ROLE_ADMIN)
  * PUT /rest/restaurants/{restaurant_id}
  * в теле запроса json с информацией о ресторане
- Добавление ресторана (ROLE_ADMIN)
  * POST /rest/restaurants
  * в теле запроса json с информацией о ресторане
- Количество голосов по заданному ресторану
  * GET /rest/restaurants/{restaurant_id}/votes
- Список победителей
  * GET /rest/restaurants/winners?date=<дата>
- Голосование за ресторан
  * POST /rest/restaurants/{restaurant_id}/vote

### Меню ресторанов

- Меню заданного ресторана на все даты
  * GET /rest/restaurants/{restaurant_id}/menu
- Меню заданного ресторана на заданную дату
  * GET /rest/restaurants/{restaurant_id}/menu/ondate?date=<дата>
- Удаление меню заданного ресторана на заданную дату (ROLE_ADMIN)
  * DELETE /rest/restaurants/{restaurant_id}/menu/ondate?date=<дата>
- Блюдо из меню заданного ресторана
  * GET /rest/restaurants/{restaurant_id}/menu/{menu_id}
- Удаление блюда из меню заданного ресторана (ROLE_ADMIN)
  * DELETE /rest/restaurants/{restaurant_id}/menu/{menu_id}
- Изменение блюда из меню заданного ресторана (ROLE_ADMIN)
  * PUT /rest/restaurants/{restaurant_id}/menu/{menu_id}
- Добавление блюда в меню заданного ресторана (ROLE_ADMIN)
  * POST /rest/restaurants/{restaurant_id}/menu

## Тестирование с помощью curl

- Список ресторанов
  * curl -u bill@microsoft.com:password http://localhost:8080/voting/rest/restaurants
- Меню ресторана
  * curl -u bill@microsoft.com:password http://localhost:8080/voting/rest/restaurants/100005/menu