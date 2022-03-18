package se.iths.springloppis;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import se.iths.springloppis.entity.RoleEntity;
import se.iths.springloppis.repository.RoleRepository;

@SpringBootApplication
public class SpringLoppisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLoppisApplication.class, args);
    }

    // Generate data at startup
    @Bean
    public CommandLineRunner setUpRole(RoleRepository roleRepository) {
        return (args) -> {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            roleRepository.save(new RoleEntity("ROLE_USER"));
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
