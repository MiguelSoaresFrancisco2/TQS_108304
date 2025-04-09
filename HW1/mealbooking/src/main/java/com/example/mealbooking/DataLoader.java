package com.example.mealbooking;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Restaurant;
import com.example.mealbooking.repository.MealRepository;
import com.example.mealbooking.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    public DataLoader(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Adicionar restaurantes de exemplo no banco de dados
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Cantina Crasto");
        restaurant1.setDescription("Restaurante localizado no campus Crasto, com opções de refeições variadas.");
        restaurant1.setAddress("Campus Crasto");
        restaurantRepository.save(restaurant1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Cantina Santiago");
        restaurant2.setDescription("Restaurante com pratos tradicionais e opções vegetarianas.");
        restaurant2.setAddress("Campus Santiago");
        restaurantRepository.save(restaurant2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Campi Grelhados");
        restaurant3.setDescription("Restaurante especializado em grelhados e carnes frescas.");
        restaurant3.setAddress("Campus Grelhados");
        restaurantRepository.save(restaurant3);

        Restaurant restaurant4 = new Restaurant();
        restaurant4.setName("Cantina Estga");
        restaurant4.setDescription("Cantina que oferece uma variedade de pratos da cozinha regional.");
        restaurant4.setAddress("Campus Estga");
        restaurantRepository.save(restaurant4);

        Restaurant restaurant5 = new Restaurant();
        restaurant5.setName("Restaurante Universitário");
        restaurant5.setDescription("Restaurante com foco em refeições equilibradas e saudáveis.");
        restaurant5.setAddress("Campus Universitário");
        restaurantRepository.save(restaurant5);

        Restaurant restaurant6 = new Restaurant();
        restaurant6.setName("Restaurante Vegetariano");
        restaurant6.setDescription("Restaurante vegetariano com opções frescas e orgânicas.");
        restaurant6.setAddress("Campus Vegetariano");
        restaurantRepository.save(restaurant6);

        // Adicionar refeições de exemplo para cada restaurante
        Meal meal1 = new Meal();
        meal1.setName("Feijoada");
        meal1.setDescription("Feijão preto com carne de porco e arroz.");
        meal1.setPrice(12.50);
        meal1.setDayOfWeek("Segunda-feira");
        meal1.setRestaurant(restaurant1);
        mealRepository.save(meal1);

        Meal meal2 = new Meal();
        meal2.setName("Arroz de Marisco");
        meal2.setDescription("Arroz com mariscos frescos.");
        meal2.setPrice(15.00);
        meal2.setDayOfWeek("Terça-feira");
        meal2.setRestaurant(restaurant1);
        mealRepository.save(meal2);

        Meal meal3 = new Meal();
        meal3.setName("Bacalhau à Brás");
        meal3.setDescription("Bacalhau desfiado com batata frita e ovos.");
        meal3.setPrice(14.00);
        meal3.setDayOfWeek("Segunda-feira");
        meal3.setRestaurant(restaurant2);
        mealRepository.save(meal3);

        Meal meal4 = new Meal();
        meal4.setName("Vegetariano");
        meal4.setDescription("Prato vegetariano com legumes grelhados e arroz integral.");
        meal4.setPrice(10.50);
        meal4.setDayOfWeek("Terça-feira");
        meal4.setRestaurant(restaurant2);
        mealRepository.save(meal4);

        Meal meal5 = new Meal();
        meal5.setName("Churrasco");
        meal5.setDescription("Churrasco de carne com batatas e salada.");
        meal5.setPrice(18.00);
        meal5.setDayOfWeek("Segunda-feira");
        meal5.setRestaurant(restaurant3);
        mealRepository.save(meal5);

        Meal meal6 = new Meal();
        meal6.setName("Frango Grelhado");
        meal6.setDescription("Frango grelhado com arroz e salada.");
        meal6.setPrice(12.00);
        meal6.setDayOfWeek("Terça-feira");
        meal6.setRestaurant(restaurant3);
        mealRepository.save(meal6);

        Meal meal7 = new Meal();
        meal7.setName("Feijoada Vegetariana");
        meal7.setDescription("Feijão preto com legumes e arroz integral.");
        meal7.setPrice(11.00);
        meal7.setDayOfWeek("Segunda-feira");
        meal7.setRestaurant(restaurant4);
        mealRepository.save(meal7);

        Meal meal8 = new Meal();
        meal8.setName("Sopa de Legumes");
        meal8.setDescription("Sopa de legumes frescos da estação.");
        meal8.setPrice(7.50);
        meal8.setDayOfWeek("Terça-feira");
        meal8.setRestaurant(restaurant4);
        mealRepository.save(meal8);

        Meal meal9 = new Meal();
        meal9.setName("Lasagna");
        meal9.setDescription("Lasagna com molho de carne e queijo.");
        meal9.setPrice(14.00);
        meal9.setDayOfWeek("Segunda-feira");
        meal9.setRestaurant(restaurant5);
        mealRepository.save(meal9);

        Meal meal10 = new Meal();
        meal10.setName("Salada Mediterrânea");
        meal10.setDescription("Salada fresca com azeitonas, pepino e tomate.");
        meal10.setPrice(9.00);
        meal10.setDayOfWeek("Terça-feira");
        meal10.setRestaurant(restaurant5);
        mealRepository.save(meal10);

        Meal meal11 = new Meal();
        meal11.setName("Bowl de Quinoa");
        meal11.setDescription("Quinoa com legumes grelhados e molho de tahine.");
        meal11.setPrice(12.00);
        meal11.setDayOfWeek("Segunda-feira");
        meal11.setRestaurant(restaurant6);
        mealRepository.save(meal11);

        Meal meal12 = new Meal();
        meal12.setName("Espaguete com Pesto");
        meal12.setDescription("Espaguete com molho pesto fresco.");
        meal12.setPrice(11.50);
        meal12.setDayOfWeek("Terça-feira");
        meal12.setRestaurant(restaurant6);
        mealRepository.save(meal12);
    }
}
