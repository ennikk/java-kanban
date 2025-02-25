package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void getDefaultTaskManager() {
        TaskManager taskManager = Managers.getDefaultTaskManager();
        assertNotNull(taskManager.getTaskList(),"Ошибка инициализации");
        assertNotNull(taskManager.getSubTaskList(),"Ошибка инициализации");
        assertNotNull(taskManager.getEpicList(),"Ошибка инициализации");
        assertNotNull(taskManager.getHistoryManager(),"Ошибка инициализации");

        ArrayList<Task> emptyTaskList = new ArrayList<>();
        ArrayList<SubTask> emptySubTaskList = new ArrayList<>();
        ArrayList<Epic> emptyEpicList = new ArrayList<>();
        ArrayList<Task> emptyHistory = new ArrayList<>();
        assertEquals(emptyTaskList, taskManager.getTaskList(),"Ошибка инициализации");
        assertEquals(emptySubTaskList, taskManager.getSubTaskList(),"Ошибка инициализации");
        assertEquals(emptyEpicList, taskManager.getEpicList(),"Ошибка инициализации");
        assertEquals(emptyHistory, taskManager.getHistoryManager(),"Ошибка инициализации");

    }

    @Test
    void getDefaultHistory() {
        HistoryManager history = Managers.getDefaultHistory();
        ArrayList<Task> emptyHistory = new ArrayList<>();
        assertEquals(emptyHistory, history.getHistory(),"Ошибка инициализации");
    }
}