package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest(classes = ECommerceAppApplication.class) // or ECommerceApp, or controller
//@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // in memory db
public class CustomerRepositoryTest {

    //@Mock
    @Autowired
    //@InjectMocks
    private CustomerRepository customerRepository;


    @Test
    public void customerRepository_SaveCustomer()
    {
        Customer customer = new Customer("John", "Smith", "a@yahoo.com", "1234@john", "0756467899", "Str. North nr 21", "Chicago", "Illionis", "789740-01", "Illionis");

        Customer savedCustomer = customerRepository.save(customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getFirstName()).isEqualTo("John");
        assertThat(savedCustomer.getLastName()).isEqualTo("Smith");
        assertThat(savedCustomer.getEmail()).isEqualTo("a@yahoo.com");
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    public void customerRepository_GetCustomer()
    {
        Customer customer = new Customer("John", "Smith", "a@yahoo.com", "1234@john", "0756467899", "Str. North nr 21", "Chicago", "Illionis", "789740-01", "Illionis");

        Customer savedCustomer = customerRepository.save(customer);

        Integer customerId =  savedCustomer.getId();
        Customer receivedCustomer = customerRepository.findById(customerId);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getFirstName()).isEqualTo(receivedCustomer.getFirstName());
        assertThat(savedCustomer.getLastName()).isEqualTo(receivedCustomer.getLastName());
        assertThat(savedCustomer.getEmail()).isEqualTo(receivedCustomer.getEmail());
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    //TODO: not tested yet Required: List Provided: Iterable
    public void customerRepository_GetCustomers()
    {
        Customer customer1 = new Customer("John", "Smith", "a@yahoo.com", "1234@john", "0756467899", "Str. North nr 21", "Chicago", "Illionis", "789740-01", "Illionis");
        Customer customer2 = new Customer("John", "Smith", "a@yahoo.com", "1234@john", "0756467899", "Str. North nr 21", "Chicago", "Illionis", "789740-01", "Illionis");

        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);


        List<Customer> receivedCustomers = (List<Customer>) customerRepository.findAll();
//
        assertThat(receivedCustomers).isNotNull();
        assertThat(receivedCustomers.size()).isEqualTo(2);
    }
}

