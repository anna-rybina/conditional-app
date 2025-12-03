# Conditional App - Docker Integration Testing

Spring Boot приложение с условными профилями dev/prod и интеграционными тестами на Testcontainers.

## 📦 Быстрый старт


### Сборка образов
```
./gradlew clean bootJar
```
```
docker build -f Dockerfile.dev -t devapp .    # порт 8080, профиль dev
docker build -f Dockerfile.prod -t prodapp .  # порт 8081, профиль prod
```
### Запуск
```
docker run -p 8080:8080 devapp
docker run -p 8081:8081 prodapp
```
### Тестирование
```
./gradlew test
```
## 🧪 Что проверяет

- **DEV**: `GET /api/profile` → `Current profile is dev`
- **PROD**: `GET /api/profile` → `Current profile is production`

## 🔧 Технологии

- Java 17, Spring Boot 3.5.7
- Docker + Testcontainers
- Условные бины `@ConditionalOnProperty`
- Gradle