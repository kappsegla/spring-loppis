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
            if( roleRepository.findByRole("ROLE_ADMIN") == null )
                roleRepository.save(new RoleEntity("ROLE_ADMIN"));
            if( roleRepository.findByRole("ROLE_USER") == null )
            roleRepository.save(new RoleEntity("ROLE_USER"));
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
