package com.example.modern_java_sandbox.repository;

import com.example.modern_java_sandbox.domain.Task;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.TimeZone;
import com.example.modern_java_sandbox.TestcontainersConfiguration;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class TaskRepositoryTests {

    static{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveAndFindTaskInPostgress(){
        Task task= new Task("Explore spring data jpa","IN_PROGRESS");
        Task saved =taskRepository.save(task);

        assertNotNull(saved.getId());

        var foundTasks = taskRepository.findByStatus("IN_PROGRESS");

        assertEquals(1,foundTasks.size());
        assertEquals("Explore spring data jpa", foundTasks.getFirst().getTitle());
    }
}
