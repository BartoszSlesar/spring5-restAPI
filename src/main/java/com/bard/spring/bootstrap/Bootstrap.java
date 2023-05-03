package com.bard.spring.bootstrap;

import com.bard.spring.domain.Category;
import com.bard.spring.domain.Customer;
import com.bard.spring.domain.Vendor;
import com.bard.spring.repositories.CategoryRepository;
import com.bard.spring.repositories.CustomerRepository;
import com.bard.spring.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category("Fruits");
        Category dried = new Category("Dried");
        Category fresh = new Category("Fresh");
        Category exotic = new Category("Exotic");
        Category nuts = new Category("Nuts");

        categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));

        log.info("Category data loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer freddy = new Customer("Freddy", "Newman");
        Customer michael = new Customer("Michael", "Lachappele");
        Customer anne = new Customer("Anne", "Hine");
        Customer alice = new Customer("Alice", "Eastman");
        Customer angelica = new Customer("Angelica", "Eastman");

        customerRepository.saveAll(Arrays.asList(freddy, michael, anne, alice, angelica));

        log.info("Customer data loaded = " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor("Western Tasty Fruits Ltd.");
        Vendor vendor2 = new Vendor("Exotic Fruits Company");
        Vendor vendor3 = new Vendor("Home Fruits");
        Vendor vendor4 = new Vendor("Fun Fresh Fruits Ltd.");
        Vendor vendor5 = new Vendor("Nuts for Nuts Company.");
        Vendor vendor6 = new Vendor("quitanda Fruits from Brazil Ltd.");
        Vendor vendor7 = new Vendor("quitanda Fruits from Brazil Ltd.");
        Vendor vendor8 = new Vendor("quitanda Fruits from Brazil Ltd.");

        vendorRepository.saveAll(Arrays.asList(vendor1, vendor2,
                vendor3, vendor4, vendor5,
                vendor6, vendor7, vendor8));

        log.info("Vendor data loaded = " + vendorRepository.count());
    }
}
