package manager;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//в рамках мэнеджера нет возможности задать id задачи,все id генерируются мэнеджером
class InMemoryTaskManagerTest {
  private TaskManager taskManager;


    @BeforeEach
    void beforeEach(){
     taskManager = Managers.getDefaultTaskManager();

    }
    @Test
    void newTask() {
        Task task1 = new Task("Task name", "Task description", Status.NEW);
        int task1Id = taskManager.newTask(task1);
        assertNotNull(taskManager.getTaskById(task1Id), "Задача не найдена.");

        Task checkTask1 = new Task("Task name", "Task description", Status.NEW);
        checkTask1.setId(task1Id);
        assertEquals(checkTask1,taskManager.getTaskById(task1Id), "Задачи не равны");

        assertEquals(1, taskManager.getTaskList().size(), "не верное количество задач");


    }

    @Test
    void updateTask() {
        Task task1 = new Task("Task name", "Task description", Status.NEW);
       int task1Id = taskManager.newTask(task1);

        Task newTask1 = new Task("newTask name", "newTask description" , Status.IN_PROGRESS);
        newTask1.setId(task1Id);

       assertNotNull(taskManager.updateTask(newTask1),"Такой задачи нет.");
        Task checkTask1 = new Task("newTask name", "newTask description", Status.IN_PROGRESS);
        checkTask1.setId(task1Id);
        assertEquals(checkTask1, taskManager.getTaskById(task1Id), "Ошибка в изменении задачи.Задачи не равны.");
    }

    @Test
    void deleteAllTasks() {

    }

    @Test
    void deleteTask() {

    }

    @Test
    void newSubTask() {
    }

    @Test
    void updateSubTask() {
    }

    @Test
    void getSubTaskList() {
    }

    @Test
    void getSubTasksForEpic() {
    }

    @Test
    void getSubTaskById() {
    }

    @Test
    void deleteAllSubTask() {
    }

    @Test
    void deleteSubTask() {
    }

    @Test
    void newEpic() {
    }

    @Test
    void updateEpic() {
    }

    @Test
    void getEpicList() {
    }

    @Test
    void getEpicById() {
    }

    @Test
    void deleteAllEpic() {
    }

    @Test
    void deleteEpic() {
    }

    @Test
    void getHistoryManager() {
    }
}