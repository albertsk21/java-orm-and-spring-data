package softuni.exam.instagraphlite.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.instagraphlite.config.impl.XmlParserImpl;
import softuni.exam.instagraphlite.config.interfaces.XmlParser;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    @Bean
    public Gson gson(){
        return  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
