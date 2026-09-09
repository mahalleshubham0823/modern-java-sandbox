package com.example.modern_java_sandbox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ModernJavaSandboxApplicationTests {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	@Test
	void contextLoads() {
	}

    @Test
    void verifyVirtualThreadsAreRunning(){
        var isVirtual = new AtomicBoolean(false);

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            executor.submit(()->{
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread);
                isVirtual.set(currentThread.isVirtual());
            });
        }
        assertTrue(isVirtual.get(),"Expected execution to run on a Virtual Thread!");
    }

}
