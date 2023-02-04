package kr.mythings.ds.mychef;

import org.h2.tools.Server;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class DsMyChefApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsMyChefApplication.class, args);
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer().start();
    }

    @Bean
    Hibernate5Module hibernate5Module() {
        /*
         * case1
         * return new hibernate5Module();
         */

        // case2
        // Hibernate5Module hibernate5Module = new Hibernate5Module();
        /*
         * 아래 옵션은 되도록 사용하면 안됨.
         * hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
         */

        return new Hibernate5Module();
    }
}
