# Notification Service

Notification Service - это сервис для отправки уведомлений через различные каналы (SMS, WhatsApp, Email). Пользователи
могут добавлять получателей уведомлений, создавать шаблоны сообщений и выбирать, через какие каналы отправить
уведомления.

## Основные функции

- Добавление получателей уведомлений с контактами (SMS, WhatsApp, Email).
- Создание и управление шаблонами сообщений.
- Отправка уведомлений через выбранные каналы.

## Технологии

- **Java**: Основной язык программирования.
- **Spring Boot**: Фреймворк для разработки бэкенда.
- **PostgreSQL**: СУБД для хранения данных.
- **Kafka**: Планируется для обработки отправок сообщений.
- **Twilio**: Для отправки SMS и сообщений в WhatsApp.
- **Java Email Sender**: Для отправки email-уведомлений.
- **Spring Security и JWT**: Для авторизации и аутентификации пользователей.

## Установка и настройка

1. Создайте базу данных PostgreSQL с именем `notification_service`.
2. Обновите `application.yml`, указав свои учетные данные для подключения к базе данных:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/notification_service
       username: your_db_username
       password: your_db_password
3. Настройте креденшелы для почты и Twilio в application.yml:
   ```yaml
   mail:
     host: your_email_host
     username: your_email_username
     password: your_email_password
     port: your_email_port
   twilio:
     sid: your_twilio_sid
     token: your_twilio_token
     number: your_twilio_number
     whatsappNumber: your_twilio_whatsapp_number
4. Запустите приложение с помощью Maven или вашего IDE.

## Использование

Основные REST API endpoints:

### Аутентификация

- **POST /api/auth/login** - Вход в систему.
- **POST /api/auth/register** - Регистрация нового пользователя.

### Шаблоны сообщений

- **POST /api/template** - Создание шаблона сообщений.
- **GET /api/template** - Получение списка шаблонов.
- **PATCH /api/template** - Обновление шаблона сообщений.
- **DELETE /api/template/{id}** - Удаление шаблона по ID.

### Получатели

- **POST /api/receiver** - Добавление получателя.
- **GET /api/receiver** - Получение списка получателей с пагинацией.
- **POST /api/receiver/{id}/contact** - Добавление контакта к существующему получателю.

### Уведомления

- **POST /api/notification/send** - Отправка уведомлений на выбранные каналы.

## Документация

Для подробной документации API доступен Swagger UI: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

## CI/CD

В проекте настроен CI с помощью GitHub Actions. Workflow находится в директории `.github/workflows`.

