package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void beforeEach() {
        task = new Task("Test name", "Test description", Status.NEW);
    }

    @Test
    void getName() {
        String checkName = "Test name";
        assertEquals(checkName, task.getName(), " Не правильно выводится имя");
    }

    @Test
    void setName() {
        task.setName("New name");
        String checkName = "New name";
        assertEquals(checkName, task.getName(), "Ошибка в изменении имени.");
    }

    @Test
    void getDescription() {
        String checkDescription = "Test description";
        assertEquals(checkDescription, task.getDescription(), " Не правильно выводится описание.");

    }

    @Test
    void setDescription() {
        task.setDescription("New description");
        String checkDescription = "New description";
        assertEquals(checkDescription, task.getDescription(), "Ошибка в изменении Description.");
    }

    @Test
    void setAndGetId() {
        task.setId(1);
        int checkId = 1;
        assertEquals(checkId, task.getId(), "Ошибка в установке ID.");
    }

    @Test
    void setAndGetStatus() {
        task.setStatus(Status.DONE);
        Status checkStatus = Status.DONE;
        assertEquals(checkStatus, task.getStatus(), "Ошибка в установке статуса.");
    }

    @Test
    void isTaskEqualse() {
        Task task1 = new Task("Test name", "Test description", Status.NEW);
        task.setId(1);
        task1.setId(1);

        assertEquals(task1, task, "Задачи должны быть равны.");
    }
}