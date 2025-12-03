package com.github.annarybina.conditionalapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers // Убираем @SpringBootTest
public class ConditionalAppIntegrationTest {

    @Container
    private static final GenericContainer<?> devContainer =
            new GenericContainer<>("devapp:latest")
                    .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> prodContainer =
            new GenericContainer<>("prodapp:latest")
                    .withExposedPorts(8081);

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void devProfileShouldReturnDevMessage() {
        Integer devPort = devContainer.getMappedPort(8080);
        System.out.println("🟢 DEV контейнер запущен на порту: " + devPort);

        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + devPort + "/api/profile",
                String.class
        );

        System.out.println("🟢 Ответ DEV: " + response.getBody());
        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void prodProfileShouldReturnProductionMessage() {
        Integer prodPort = prodContainer.getMappedPort(8081);
        System.out.println("🔴 PROD контейнер запущен на порту: " + prodPort);

        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + prodPort + "/api/profile",
                String.class
        );

        System.out.println("🔴 Ответ PROD: " + response.getBody());
        assertEquals("Current profile is production", response.getBody());
    }
}