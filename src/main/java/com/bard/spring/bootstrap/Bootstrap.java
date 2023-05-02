package com.bard.spring.bootstrap;

import com.bard.spring.domain.Category;
import com.bard.spring.domain.Customer;
import com.bard.spring.repositories.CategoryRepository;
import com.bard.spring.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LoadCategories();
        LoadCustomers();
    }

    private void LoadCategories() {
        Category fruits = new Category("Fruits");
        Category dried = new Category("Dried");
        Category fresh = new Category("Fresh");
        Category exotic = new Category("Exotic");
        Category nuts = new Category("Nuts");

        categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));

        log.info("Category data loaded = " + categoryRepository.count());
    }

    private void LoadCustomers() {
        Customer freddy = new Customer("Freddy","Newman");
        Customer michael = new Customer("Michael","Lachappele");
        Customer anne = new Customer("Anne","Hine");
        Customer alice = new Customer("Alice","Eastman");
        Customer angelica = new Customer("Angelica","Eastman");

        customerRepository.saveAll(Arrays.asList(freddy, michael, anne, alice, angelica));

        log.info("Customer data loaded = " + customerRepository.count());
    }
}
