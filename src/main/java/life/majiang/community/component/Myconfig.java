package life.majiang.community.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Created by codedrinker on 2019/11/1.
 **/
@Configuration
public class Myconfig  {
    @Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }

}
