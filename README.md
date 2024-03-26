## Приложение Склад товарав Storage

Используется 2 сущности:
- категория товара
- товар

### Запуск приложения в Docker:
- команда maven clean package
- запустить команду в терминале docker-compose up
- запустить коллекцию Postman для проверки

### Локальный запуск приложения под профилем dev:
- поставить в VM options -Dspring.profiles.active=dev
- запустить контейнер с БД
- запустить приложение локально
- запустить коллекцию Postman для проверки

### Swagger
http://localhost:8080/swagger-ui/index.html#/

### Коллекция Postman
Склад товаров.postman_collection.json
Лежит в проекте



