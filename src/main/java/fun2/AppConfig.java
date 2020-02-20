package fun2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@ComponentScan ("fun2")
@Component
public class AppConfig {

    @Bean (name = "item1")
    public Item item1(){
        return new ItemImpl();
    }

    //@Bean (name = "store")
    public Store store(){
        return new StoreImpl();
    }

    @Bean (name = "store1")
    public Store store1(){
        Store store = store();
        store.setItem(item1()); //setter dependency injection
        return store;
    }

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Store s = (Store)appContext.getBean("store1");
        System.out.println("my name is " + s.getItem().getClass());
    }

}
