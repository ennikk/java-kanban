import manager.TaskManager;
import model.*;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        //создаем две задачи
        Task task1 = new Task("Купить лего набор", "Малому нужен набор лего звездные войны" +
                " Явин-4", Status.NEW);
        Task task2 = new Task("Закончить спринт", "надо бы поторопиться", Status.NEW);

        taskManager.newTask(task1);
        taskManager.newTask(task2);

        //создаем два эпика
        Epic epic1 = new Epic("Сделать ремонт","страшно, очень страшно, если бы мы знали что" +
                " это такое...");
        Epic epic2 = new Epic("Поменять лампочку в машине","Нужно всетаки это сделать");
        taskManager.newEpic(epic1);
        taskManager.newEpic(epic2);

        //добавляем подзадачи
        SubTask subTask1 = new SubTask("C счего начать?", "въехать в стройку", Status.NEW,
                2);
        SubTask subTask2 = new SubTask("Дипломатия", "Убедить жену что все и так ок", Status.NEW,
                2);
        SubTask subTask3 = new SubTask("Купить лампочку", "дойти до магазина и купить", Status.NEW,
                3);
        taskManager.newSubTask(subTask1);
        taskManager.newSubTask(subTask2);
        taskManager.newSubTask(subTask3);

        System.out.println(taskManager.getTaskList());
        System.out.println(taskManager.getEpicList());
        System.out.println(taskManager.getSubTaskList());
        System.out.println();
        System.out.println();

// для обновления задачь создадим новые объекты -- ~копии уже существующих c изменениями
        Task newTask1 = new Task("Купить лего набор", "Малому нужен набор лего звездные войны" +
                " Явин-4.Почти купил", Status.IN_PROGRESS);
        newTask1.setId(task1.getId());
        Task newTask2 = new Task("Закончить спринт", "ускорился", Status.IN_PROGRESS);
        newTask2.setId(task2.getId());

        Epic newEpic2 = new Epic("Поменять лампочку в авто","купил, но возможн не ту");
        newEpic2.setId(epic2.getId());

        SubTask newSubTask1 = new SubTask("C счего начать?", "въехать в стройку",
                Status.DONE, 2);
        newSubTask1.setId(subTask1.getId());
        SubTask newSubTask2 = new SubTask("Дипломатия", "Убедить жену что все и так" +
                " ок", Status.DONE, 2);
        newSubTask2.setId(subTask2.getId());
        SubTask newSubTask3 = new SubTask("Купить лампочку", "дойти до магазина" +
                " и купить", Status.IN_PROGRESS, 3);
        newSubTask3.setId(subTask3.getId());

        //меняем  задачи
        taskManager.updateTask(newTask1);
        taskManager.updateTask(newTask2);

        //меняем подзадачи
        taskManager.updateSubTask(newSubTask1);
        taskManager.updateSubTask(newSubTask2);
        taskManager.updateSubTask(newSubTask3);

        //меняем описание в эпике
        taskManager.updateEpic(newEpic2);

        System.out.println(taskManager.getTaskList());
        System.out.println(taskManager.getEpicById(2));
        System.out.println(taskManager.getSubTasksForEpic(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubTasksForEpic(3));
        System.out.println();
        System.out.println();

        //удаление
        taskManager.deleteEpic(2);
        taskManager.deleteTask(1);
        System.out.println(taskManager.getTaskList());
        System.out.println(taskManager.getEpicList());
        System.out.println(taskManager.getSubTaskList());
    }
}
