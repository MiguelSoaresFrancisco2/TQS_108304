package com.example.__1;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.assertj.core.api.Assertions.assertThat;

public class TestcontainersTest {

    @Test
    void testPostgresContainer() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.2")) {
            postgres.start();
            assertThat(postgres.isRunning()).isTrue();
            System.out.println("Postgres está a correr em: " + postgres.getJdbcUrl());
        }
    }
}

    