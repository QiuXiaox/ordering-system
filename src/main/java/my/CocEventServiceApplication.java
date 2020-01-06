/**
 * 
 */
package my;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 1. 主类：@SpringBootApplication
 * 2. DAO扫描：@MapperScan
 * 3. 事务AOP：@ImportResource
 * 4. 事务注解：@EnableTransactionManagement
 
 * @author wuzhb
 *
 */
@SpringBootApplication // 启动类
@MapperScan(basePackages = "my.mapper") // Dao
@ImportResource(locations = {"classpath:config/applicationContext-transaction.xml"}) // 事务AOP
@EnableTransactionManagement // 可选：事务Annotation（等同于xml配置方式的 <tx:annotation-driven />）
@EnableScheduling //用于发现定时器任务
public class CocEventServiceApplication implements CommandLineRunner  {

	@Override
	public void run(String... args) throws Exception {
		 TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CocEventServiceApplication.class, args);
	}
    
    @Bean
    public Module dateDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new DateDeserializer());
        return module;
    }
	
	

    /**
     * 添加日期字符串的解析。
     * 
     * @return 日期转换器
     */
    @Bean
    public Converter<String, Date> stringToDateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if (source == null || source.length() == 0) {
                    return null;
                }
                
                SimpleDateFormat sdf;
                if (source.length() > 19) {
                    //Mon Nov 13 22:23:02 GMT+08:00 2017
                    String charBeforeZone = source.substring(23, 24);
                    if (" ".equals(charBeforeZone)) {
                        source = source.substring(0, 23) + '+' + source.substring(24);
                    }
                    sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", DateFormatSymbols.getInstance(Locale.ENGLISH));
                } else if (source.length() > 10) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
    

}
