package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {
    private SubTask subTask;

    @BeforeEach
    void beforeEach() {
        subTask = new SubTask("Test name", "Test description", Status.NEW, 1);
    }

    @Test
    void setId() {
        subTask.setId(1);
        assertEquals(0 ,subTask.getId(), "Id подзадачи равно Id эпика.");
    }


    @Test
    void isSubTaskEqualse() {
        SubTask subTask1 = new SubTask("Test name", "Test description", Status.NEW, 1);
        subTask.setId(1);
        subTask1.setId(1);

        assertEquals(subTask1, subTask, "Задачи должны быть равны.");
    }
}