package my;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
 
    //拦截器省略
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//windows下
    	//registry.addResourceHandler("/img/**").addResourceLocations("file:" + "d:/picture/");
    	//linux下
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + "/home/takeout/picture/");
    }
}