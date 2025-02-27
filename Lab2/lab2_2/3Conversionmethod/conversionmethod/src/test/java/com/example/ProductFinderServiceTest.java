package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) 
public class ProductFinderServiceTest {

    @InjectMocks
    private ProductFinderService service; 

    @Mock
    private ISimpleHttpClient http; 

    @BeforeEach
    void setUp() {
        Mockito.reset(http);
    }

    @Test
    void testFindProductDetailsValid() {  String jsonResponse = """
            {
                "id": 3,
                "title": "Mens Cotton Jacket",
                "price": 55.99,
                "description": "Great jacket for casual wear.",
                "image": "https://example.com/jacket.jpg",
                "category": "men's clothing"
            }
        """;

        when(http.doHttpGet("https://fakestoreapi.com/products/3")).thenReturn(jsonResponse);

        Optional<Product> productOpt = service.findProductDetails(3);

        assertTrue(productOpt.isPresent());
        Product product = productOpt.get();
        assertEquals(3, product.getId());
        assertEquals("Mens Cotton Jacket", product.getTitle());

    }

    @Test
    void testFindProductDetailsInvalid() { 
        when(http.doHttpGet("https://fakestoreapi.com/products/300")).thenReturn("");

        Optional<Product> productOpt = service.findProductDetails(300);

        assertTrue(productOpt.isEmpty(), "Não existe");
    }
}
