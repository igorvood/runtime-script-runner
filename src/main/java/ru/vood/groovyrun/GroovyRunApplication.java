package ru.vood.groovyrun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import groovy.lang.GroovyShell ;
import groovy.lang.GroovyClassLoader ;
import groovy.util.GroovyScriptEngine ;
import java.io.File ;

@SpringBootApplication
public class GroovyRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroovyRunApplication.class, args);
    }

}
