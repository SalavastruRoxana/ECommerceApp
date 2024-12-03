package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // in memory db
//@SpringBootTest(classes = ECommerceAppApplication.class)
//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Autowired
    @Qualifier("CustomerImpl")
    private CustomerServiceImpl customerServiceImpl;




    @Test
        public void customerRepository_SaveCustomer()
        {
            Customer customer = new Customer("John", "Smith", "a@yahoo.com", "1234@john", "0756467899", "Str. North nr 21", "Chicago", "Illionis", "789740-01", "Illionis");

            Customer savedCustomer = customerServiceImpl.createCustomer(customer);

            assertThat(savedCustomer).isNotNull();
            assertThat(savedCustomer.getFirstName()).isEqualTo("John");
            assertThat(savedCustomer.getLastName()).isEqualTo("Smith");
            assertThat(savedCustomer.getEmail()).isEqualTo("a@yahoo.com");
            assertThat(savedCustomer.getId()).isGreaterThan(0);
        }
}
