package sgu.j2ee.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dopjo5p58"); 
        config.put("api_key", "338435176442292");       
        config.put("api_secret", "9z1y0WTjOpDCg3gySz_ZMam3W0g"); 
        return new Cloudinary(config);
    }
}
