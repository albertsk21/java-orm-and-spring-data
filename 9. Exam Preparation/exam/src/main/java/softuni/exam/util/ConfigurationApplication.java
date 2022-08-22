package softuni.exam.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.impl.XmlParserImpl;
import softuni.exam.util.interfaces.XmlParser;

import java.util.Random;

@Configuration
public class ConfigurationApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

    @Bean
    public Random random(){
        return new Random();
    }


}
