package manager;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    //создать новую задачу
    int newTask(Task task);

    //обновить задачу
    //подразумивается что на вход подается уже измененный обЪект с таким же id
    //поменять тест
    Task updateTask(Task task);

    //получение списка задач
    ArrayList<Task> getTaskList();

    //получение задачи по id
    Task getTaskById(int id);

    //удаление всех задач
    void deleteAllTasks();

    //удаление задачи по id
    void deleteTask(int id);

    //создать новую подзадачу
    int newSubTask(SubTask subTask);

    //обновить подзадачу
    SubTask updateSubTask(SubTask subTask);

    //получение списка подзадач
    ArrayList<SubTask> getSubTaskList();

    //получение подзадачь определенного эпика
    ArrayList<SubTask> getSubTasksForEpic(int epicId);

    //получение подзадачи по id
    SubTask getSubTaskById(int id);

    //удаление всех подзадач
    void deleteAllSubTask();

    //удаление подзадачи по id
    void deleteSubTask(int id);

    //создать новый эпик
    int newEpic(Epic epic);

    //обновить эпик
    Epic updateEpic(Epic epic);

    // получение списка эпиков
    ArrayList<Epic> getEpicList();

    //получение эпика по id
    Epic getEpicById(int id);

    //удаление всех эпиков
    void deleteAllEpic();

    //удаление эпика по id
    void deleteEpic(int id);

    ArrayList<Task> getHistory();
}
