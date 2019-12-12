package demo.pkg.helloapp.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author daniell
 */
@Configuration
@MapperScan("demo.pkg.helloapp.dao")
public class MybatisMapperConfig {

}
