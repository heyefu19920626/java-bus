package com.tang.spi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

class IServiceTest {
    @Test
    public void spi_test() {
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for (IService service : services) {
            service.print("hello");
        }
        Assertions.assertEquals(1, services.stream().count());
    }
}