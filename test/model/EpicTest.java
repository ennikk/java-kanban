package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    private Epic epic;

    @BeforeEach
    void beforeEach() {
        epic = new Epic("Test name", "Test description");
    }

    @Test
    void addSubTasksId() {
        epic.setId(1);
        assertEquals(-1, epic.addSubTasksId(1), "Эпик принял свой id в качестве подзадачи.");
    }

    @Test
    void getSubTasksId() {
        epic.addSubTasksId(1);
        epic.addSubTasksId(2);
        epic.addSubTasksId(3);
        assertEquals(3, epic.getSubTasksId().size(), "Неверное количество подзадач.");
    }

    @Test
    void isEpicsEqualse() {
        Epic epic1 = new Epic("Test name", "Test description");
        epic.setId(1);
        epic1.setId(1);

        assertEquals(epic1, epic, "Задачи должны быть равны.");
    }
}