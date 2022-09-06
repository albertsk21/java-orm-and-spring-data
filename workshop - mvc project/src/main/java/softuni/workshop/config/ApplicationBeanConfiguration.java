package softuni.workshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.workshop.util.IValidator;
import softuni.workshop.util.ValidatorImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public IValidator iValidator(){
        return new ValidatorImpl();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
