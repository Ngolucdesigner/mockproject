package com.anks.tech.ecommerce.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuaration {
    @Bean
    public ModelMapper initModelMapper() {
        return new ModelMapper();
    }


}
