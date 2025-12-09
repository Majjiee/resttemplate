package com.example.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.car.repositories.CarRepository;
import com.example.car.entities.Car;


@SpringBootApplication
@EnableDiscoveryClient // Active l'enregistrement auprès d'Eureka
public class CarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

    /**
     * Configure RestTemplate pour la communication inter-services
     * 
     * @return Instance configurée de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Configuration des timeouts
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // 5 secondes pour la connexion
        requestFactory.setReadTimeout(5000); // 5 secondes pour la lecture

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
    @Bean
CommandLineRunner run(CarRepository carRepository) {
    return args -> {
        carRepository.save(new Car(null, "123456", "Toyota", "Corolla", 1L));
        carRepository.save(new Car(null, "789012", "BMW", "M3", 2L));
        carRepository.save(new Car(null, "345678", "Honda", "Civic", 3L));
    };
}
}
