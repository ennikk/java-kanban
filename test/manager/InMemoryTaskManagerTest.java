package manager;

import model.Epic;
import model.Status;
import model.Task;
import model.SubTask;
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
    void deleteTasks() {
        Task task1 = new Task("Task1 name", "Task1 description", Status.NEW);
        int task1Id = taskManager.newTask(task1);

        Task task2 = new Task("Task2 name", "Task2 description", Status.NEW);
        int task2Id = taskManager.newTask(task2);

        Task task3 = new Task("Task3 name", "Task3 description", Status.NEW);
        int task3Id = taskManager.newTask(task3);

        taskManager.deleteTask(task1Id);
        assertEquals(2, taskManager.getTaskList().size(), "Задача не удалена.");

        taskManager.deleteAllTasks();
        assertEquals(0, taskManager.getTaskList().size(), "Задачи не удалены.");
    }


    @Test
    void newSubTaskAndEpic() {
        Epic epic = new Epic("Epic Name", "Epic description");
        int epicId = taskManager.newEpic(epic);
        SubTask subTask1 = new SubTask("Task name", "Task description", Status.NEW, epicId);
        int subTask1Id = taskManager.newSubTask(subTask1);

        assertNotNull(taskManager.getEpicById(epicId), "Задача не найдена.");
        assertNotNull(taskManager.getSubTaskById(subTask1Id), "Задача не найдена.");

        Epic checkEpic = new Epic("Epic Name", "Epic description");
        checkEpic.setId(epicId);
        assertEquals(checkEpic,taskManager.getEpicById(epicId), "Задачи не равны");
        assertEquals(1, taskManager.getEpicList().size(), "не верное количество задач");

        SubTask checkSubTask1 = new SubTask("Task name", "Task description", Status.NEW, epicId);
        checkSubTask1.setId(subTask1Id);
        assertEquals(checkSubTask1,taskManager.getSubTaskById(subTask1Id), "Задачи не равны");
        assertEquals(1, taskManager.getSubTaskList().size(), "не верное количество задач");
    }

    @Test
    void updateSubTask() {
        Epic epic = new Epic("Epic Name", "Epic description");
        int epicId = taskManager.newEpic(epic);
        SubTask subTask1 = new SubTask("Task name", "Task description", Status.NEW, epicId);
        int subTask1Id = taskManager.newSubTask(subTask1);
        SubTask newSubTask1 = new SubTask("Task NEWname", "Task NEWdescription", Status.DONE, epicId);
        newSubTask1.setId(subTask1Id);
        assertNotNull(taskManager.updateSubTask(newSubTask1),"Не удалось обновить задачу.");

        SubTask checkSubTask1 = new SubTask("Task NEWname", "Task NEWdescription", Status.DONE, epicId);
        checkSubTask1.setId(subTask1Id);
        assertEquals(checkSubTask1, taskManager.getSubTaskById(subTask1Id), "Ошибка в изменении задачи.Задачи не равны.");
        assertEquals(Status.DONE, epic.getStatus(), "Статус эпика не обновился.");
        assertEquals(1,taskManager.getSubTasksForEpic(epicId).size(), "Неверно сохранены подзадачи в эпике.");
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