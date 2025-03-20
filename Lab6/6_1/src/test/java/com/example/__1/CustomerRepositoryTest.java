package com.example.__1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

@Testcontainers
@SpringBootTest
public class CustomerRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.2")
            .withUsername("test")
            .withPassword("test")   
            .withDatabaseName("customer");

    @Autowired
    private CustomerRepository customerRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver"); 
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }


    @Test
    void contextLoads() {
        Customer customer = new Customer("Default User", "default@example.com");
        customerRepository.save(customer);
        System.out.println("Context loads!");
    }

    @Test
    void test_addCustomer() {
        String NAME = "John Doe";
        String EMAIL = "john.doe@example.com";

        Customer customer = new Customer(NAME, EMAIL);
        customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findByEmail(EMAIL);

        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getName()).isEqualTo(NAME);
        assertThat(foundCustomer.get().getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void test_findByName() {
        String NAME = "Alice Smith";
        String EMAIL = "alice.smith@example.com";

        Customer customer = new Customer(NAME, EMAIL);
        customerRepository.save(customer);

        List<Customer> foundCustomers = customerRepository.findByName(NAME);

        assertThat(foundCustomers).isNotEmpty();
        assertThat(foundCustomers.get(0).getEmail()).isEqualTo(EMAIL);
    }
}
