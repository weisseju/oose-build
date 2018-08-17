package de.oose.environmentservice.fx.spring;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFxmlLoader {

    private final ApplicationContext applicationContext;

    public SpringFxmlLoader(Class<?> configClass) {
        applicationContext =
                new AnnotationConfigApplicationContext(configClass);
    }

    public SpringFxmlLoader(String... configLocations) {
        applicationContext =
                new ClassPathXmlApplicationContext(configLocations);
    }

    public Object load(String url) {
        try (InputStream fxmlStream =
                SpringFxmlLoader.class.getResourceAsStream(url)) {

            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    return applicationContext.getBean(clazz);
                }
            });
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(url, ioException);
        }
    }
}
