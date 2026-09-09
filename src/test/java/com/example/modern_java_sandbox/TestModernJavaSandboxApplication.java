package com.example.modern_java_sandbox;

import org.springframework.boot.SpringApplication;

public class TestModernJavaSandboxApplication {

	public static void main(String[] args) {
		SpringApplication.from(ModernJavaSandboxApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
