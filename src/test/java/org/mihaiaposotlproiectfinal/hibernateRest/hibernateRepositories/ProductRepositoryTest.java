package org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateProduct;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired  private ProductRepository repository;
    @Autowired  private ClientRepository clientRepository;
    @Autowired  private TransactionRepository transactionRepositoy;

    @Test
    public void should_find_all_customers() {

        List<HibernateProduct> products = repository.findAll();

        int noOfProducts = 2;
        assertThat(products).hasSize(noOfProducts);
    }

    @Test
    public void should_find_with_name_ending_population_less_than() {

        List<HibernateProduct> products = repository.findByNameEndingWithAndPopulationLessThan("ina");
        assertThat(products).isNotEmpty();
    }

    @Test
    public void tryThis(){

    }
}