package carl.dev.demo.apidocs;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig{

    public SwaggerConfig() {
        super("carl.dev.demo.endpoint");
    }
}
