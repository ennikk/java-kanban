package manager;

import model.Epic;
import model.Status;
import model.Task;
import model.SubTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//в рамках мэнеджера нет возможности задать id задачи,все id генерируются мэнеджером
class InMemoryTaskManagerTest {
    private TaskManager taskManager;


    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefaultTaskManager();

    }

    @Test
    void newTask() {
        Task task1 = new Task("Task name", "Task description", Status.NEW);
        int task1Id = taskManager.newTask(task1);
        assertNotNull(taskManager.getTaskById(task1Id), "Задача не найдена.");

        Task checkTask1 = new Task("Task name", "Task description", Status.NEW);
        checkTask1.setId(task1Id);
        assertEquals(checkTask1, taskManager.getTaskById(task1Id), "Задачи не равны");

        assertEquals(1, taskManager.getTaskList().size(), "не верное количество задач");


    }

    @Test
    void updateTask() {
        Task task1 = new Task("Task name", "Task description", Status.NEW);
        int task1Id = taskManager.newTask(task1);

        Task newTask1 = new Task("newTask name", "newTask description", Status.IN_PROGRESS);
        newTask1.setId(task1Id);

        assertNotNull(taskManager.updateTask(newTask1), "Такой задачи нет.");
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
        assertEquals(checkEpic, taskManager.getEpicById(epicId), "Задачи не равны");
        assertEquals(1, taskManager.getEpicList().size(), "не верное количество задач");

        SubTask checkSubTask1 = new SubTask("Task name", "Task description", Status.NEW, epicId);
        checkSubTask1.setId(subTask1Id);
        assertEquals(checkSubTask1, taskManager.getSubTaskById(subTask1Id), "Задачи не равны");
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
        assertNotNull(taskManager.updateSubTask(newSubTask1), "Не удалось обновить задачу.");

        SubTask checkSubTask1 = new SubTask("Task NEWname", "Task NEWdescription", Status.DONE, epicId);
        checkSubTask1.setId(subTask1Id);
        assertEquals(checkSubTask1, taskManager.getSubTaskById(subTask1Id), "Ошибка в изменении задачи.Задачи не равны.");
        assertEquals(Status.DONE, epic.getStatus(), "Статус эпика не обновился.");
        assertEquals(1, taskManager.getSubTasksForEpic(epicId).size(), "Неверно сохранены подзадачи в эпике.");
    }


    @Test
    void deleteSubTask() {
        Epic epic = new Epic("Epic Name", "Epic description");
        int epicId = taskManager.newEpic(epic);
        SubTask subTask1 = new SubTask("Task1 name", "Task1 description", Status.DONE, epicId);
        int subTask1Id = taskManager.newSubTask(subTask1);
        SubTask subTask2 = new SubTask("Task2 name", "Task2 description", Status.DONE, epicId);
        int subTask2Id = taskManager.newSubTask(subTask2);
        SubTask subTask3 = new SubTask("Task3 name", "Task3 description", Status.NEW, epicId);
        int subTask3Id = taskManager.newSubTask(subTask3);

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Не верно расчитан статус эпика.");

        taskManager.deleteSubTask(subTask3Id);
        assertEquals(Status.DONE, epic.getStatus(), "Не верно расчитан статус эпика.");
        assertEquals(2, taskManager.getSubTaskList().size(), "Ошибка при удалении.");
        taskManager.deleteAllSubTask();
        assertEquals(Status.NEW, epic.getStatus(), "Не верно расчитан статус эпика.");
        assertEquals(0, taskManager.getSubTaskList().size(), "Ошибка при удалении.");

    }


    @Test
    void updateEpic() {
        Epic epic = new Epic("Epic Name", "Epic description");
        int epicId = taskManager.newEpic(epic);
        Epic newEpic = new Epic("Epic newName", "Epic newDescription");
        newEpic.setId(epicId);
        Epic chackNewEpic = new Epic("Epic newName", "Epic newDescription");
        chackNewEpic.setId(epicId);
        assertNotNull(taskManager.updateEpic(newEpic), "Не удалось обновить задачу.");
        assertEquals(chackNewEpic, taskManager.getEpicById(epicId), "Ошибка в изменении задачи.Задачи не равны.");

    }

    @Test
    void deleteEpic() {
        Epic epic1 = new Epic("Epic Name", "Epic description");
        int epic1Id = taskManager.newEpic(epic1);
        SubTask subTask11 = new SubTask("Task1 name", "Task1 description", Status.DONE, epic1Id);
        int subTask11Id = taskManager.newSubTask(subTask11);
        SubTask subTask12 = new SubTask("Task2 name", "Task2 description", Status.DONE, epic1Id);
        int subTask12Id = taskManager.newSubTask(subTask12);
        SubTask subTask13 = new SubTask("Task3 name", "Task3 description", Status.NEW, epic1Id);
        int subTask13Id = taskManager.newSubTask(subTask13);

        Epic epic2 = new Epic("Epic Name", "Epic description");
        int epic2Id = taskManager.newEpic(epic2);
        SubTask subTask21 = new SubTask("Task1 name", "Task1 description", Status.DONE, epic2Id);
        int subTask21Id = taskManager.newSubTask(subTask21);
        SubTask subTask22 = new SubTask("Task2 name", "Task2 description", Status.DONE, epic2Id);
        int subTask22Id = taskManager.newSubTask(subTask22);
        SubTask subTask23 = new SubTask("Task3 name", "Task3 description", Status.NEW, epic2Id);
        int subTask23Id = taskManager.newSubTask(subTask23);

        taskManager.deleteEpic(epic1Id);
        assertEquals(1, taskManager.getEpicList().size(), "Эпик удален не корректно.");
        assertEquals(3, taskManager.getSubTaskList().size(), "Подзадачи удаленного эпика удалены" +
                " не корректно.");

        taskManager.deleteAllEpic();
        assertEquals(0, taskManager.getEpicList().size(), "Эпик удален не корректно.");
        assertEquals(0, taskManager.getSubTaskList().size(), "Подзадачи удаленного эпика удалены" +
                " не корректно.");

    }


    @Test
    void getHistoryManager() {
        Epic epic = new Epic("Epic Name", "Epic description");
        int epicId = taskManager.newEpic(epic);
        SubTask subTask1 = new SubTask("Task1 name", "Task1 description", Status.DONE, epicId);
        int subTask1Id = taskManager.newSubTask(subTask1);
        SubTask subTask2 = new SubTask("Task2 name", "Task2 description", Status.DONE, epicId);
        int subTask2Id = taskManager.newSubTask(subTask2);
        SubTask subTask3 = new SubTask("Task3 name", "Task3 description", Status.NEW, epicId);
        int subTask3Id = taskManager.newSubTask(subTask3);
        Task task = new Task("Task name", "Task description", Status.NEW);
        int taskId = taskManager.newTask(task);

        taskManager.getTaskById(taskId);
        taskManager.getEpicById(epicId);
        taskManager.getSubTaskById(subTask1Id);
        taskManager.getSubTaskById(subTask2Id);
        taskManager.getSubTaskById(subTask3Id);

        assertEquals(5, taskManager.getHistory().size(), "Не верно работает запись в историю.");

        Task newTask = new Task("Task newName", "Task newDescription", Status.DONE);
        newTask.setId(taskId);
        taskManager.updateTask(newTask);

        assertEquals(newTask.getId(), taskManager.getHistory().get(0).getId(), "Разные Id");
        assertNotEquals(newTask, taskManager.getHistory().get(0), "Задача в истории изменилась");

        //проверяем новый функционал
        //запись
        taskManager.getSubTaskById(subTask1Id);
        assertEquals(5, taskManager.getHistory().size(), "Не верно работает запись в историю.");
        assertEquals(subTask1, taskManager.getHistory().getLast(), "Не верно работает запись в историю.");

        //удаление
        taskManager.deleteSubTask(subTask1Id);
        assertEquals(4, taskManager.getHistory().size(), "Не верно работает удаление из историю.");
        assertNotEquals(subTask1, taskManager.getHistory().getLast(), "Не верно работает удаление из историю.");

        taskManager.deleteAllEpic();
        assertEquals(1, taskManager.getHistory().size(), "Не верно работает удаление из историю.");
        assertEquals(task, taskManager.getHistory().getLast(), "Не верно работает удаление из историю.");
    }
}