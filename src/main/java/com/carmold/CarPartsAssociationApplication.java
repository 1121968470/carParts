package com.carmold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages = {"com.carmold"})

@ImportResource(locations = {"shiro.xml"})
public class CarPartsAssociationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarPartsAssociationApplication.class, args);
    }

}
