package softuni.workshop.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.workshop.util.impl.XmlParserImpl;
import softuni.workshop.util.interfaces.XmlParser;

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



}
