package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.contains;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

public class StocksPortfolioTest {
    @InjectMocks
    StocksPortfolio portfolio;

    @Mock
    IStockmarketService market;

    @Test
    void test_totalvalue() {
        IStockmarketService market = Mockito.mock(IStockmarketService.class);
        StocksPortfolio portfolio = new StocksPortfolio(market);

        when(market.lookUpPrice("Amazon")).thenReturn(230.00);
        when(market.lookUpPrice("Apple")).thenReturn(220.00);
        when(market.lookUpPrice("Google")).thenReturn(250.00);

        portfolio.addStock(new Stock("Amazon", 30));
        portfolio.addStock(new Stock("Apple", 20));
        portfolio.addStock(new Stock("Google", 50));

        double result = portfolio.totalValue();

        double totalValue = 230.00 * 30 + 220.00 * 20 + 250.00 * 50;

        assertEquals(totalValue, result);
        assertThat(result, is(totalValue));

        verify(market, times(3)).lookUpPrice(anyString());

    }

    @Test
    void test_mostvaluableStocks() {
        IStockmarketService market = Mockito.mock(IStockmarketService.class);
        StocksPortfolio portfolio = new StocksPortfolio(market);

        when(market.lookUpPrice("Amazon")).thenReturn(230.00);
        when(market.lookUpPrice("Apple")).thenReturn(220.00);
        when(market.lookUpPrice("Google")).thenReturn(250.00);
        when(market.lookUpPrice("Tesla")).thenReturn(300.00);
        when(market.lookUpPrice("Microsoft")).thenReturn(280.00);

        portfolio.addStock(new Stock("Amazon", 30));
        portfolio.addStock(new Stock("Apple", 20));
        portfolio.addStock(new Stock("Google", 50));
        portfolio.addStock(new Stock("Tesla", 10));
        portfolio.addStock(new Stock("Microsoft", 40));

        List<Stock> topStocks = portfolio.mostValuableStocks(3);

        assertEquals(3, topStocks.size());
        assertThat(topStocks.stream().map(Stock::getLabel).toList(),
                contains("Google", "Microsoft", "Amazon"));

        verify(market, atLeast(5)).lookUpPrice(anyString()); 

    }

}
