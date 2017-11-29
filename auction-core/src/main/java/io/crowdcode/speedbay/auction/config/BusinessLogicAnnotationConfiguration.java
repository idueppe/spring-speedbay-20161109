package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.util.CachablePostProcessor;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
@ComponentScan(
        basePackages = {"io.crowdcode.speedbay.auction"
//                ,"io.crowdcode.speedbay.common.inmemory"
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "io\\.crowdcode\\.speedbay\\.auction\\.config.*")
        }
)
public class BusinessLogicAnnotationConfiguration {


    // Weil InMemoryStore aus einem anderen Modul kommt,
    // wird es lieber explizit über factory Methode instanziert
    // statt das Package zu scannen.
    @Bean(initMethod = "init")
    public InMemoryStore<Auction> inMemoryStore() {
        return new InMemoryStore<Auction>();
    }

    @Bean
    public static CachablePostProcessor cachablePostProcessor() {
        return new CachablePostProcessor();
    }

    @Bean
    public DataSource dataSourceFromJndi() {
        // lang und breit den Code um auf JndI zuzugreifen.
        return null;
    }

    @Bean
    public JndiObjectFactoryBean dataSourceFromJndiFactoryBean() {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName("/jndi/datasource/productDB");
        return factoryBean;
    }

}
