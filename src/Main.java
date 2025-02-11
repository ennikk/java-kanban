public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        //создаем две задачи
        taskManager.newTask(new Task("Купить лего набор", "Малому нужен набор лего звездные войны" +
                " Явин-4", Status.NEW));
        taskManager.newTask(new Task("Закончить спринт", "надо бы поторопиться", Status.NEW));

        //создаем два эпика
        taskManager.newEpic(new Epic("Сделать ремонт","страшно, очень страшно, если бы мы знали что" +
                " это такое..."));
        taskManager.newEpic(new Epic("Поменять лампочку в авто","Надо бы это сделать"));

        //добавляем подзадачи
        taskManager.newSubTask(new SubTask("C счего начать?", "въехать в стройку", Status.NEW,
                2));
        taskManager.newSubTask(new SubTask("Дипломатия", "Убедить жену что все и так ок", Status.NEW,
                2));
        taskManager.newSubTask(new SubTask("Купить лампочку", "дойти до магазина и купить", Status.NEW,
                3));

        System.out.println(taskManager.listTask());
        System.out.println(taskManager.listEpic());
        System.out.println(taskManager.listSubTask());
        System.out.println();
        System.out.println();


        //меняем  задачи
        taskManager.updateTask(0, new Task("Купить лего набор", "Малому нужен набор лего" +
                " звездные войны Явин-4! Почти купил!!!", Status.IN_PROGRESS));
        taskManager.updateTask(1, new Task("Закончить спринт", "ускорился", Status.IN_PROGRESS));

        //меняем подзадачи
        taskManager.updateSubTask(4, new SubTask("C счего начать?", "въехать в стройку",
                Status.DONE, 2));
        taskManager.updateSubTask(5, new SubTask("Дипломатия", "Убедить жену что все и так" +
                " ок", Status.DONE, 2));
        taskManager.updateSubTask(6, new SubTask("Купить лампочку", "дойти до магазина" +
                " и купить", Status.IN_PROGRESS, 3));

        //меняем описание в эпике
        taskManager.updateEpic(3, new Epic("Поменять лампочку в авто","купил, но возможн не ту"));

        System.out.println(taskManager.listTask());
        System.out.println(taskManager.getEpicById(2));
        System.out.println(taskManager.getSubTasksForEpic(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubTasksForEpic(3));
        System.out.println();
        System.out.println();

        //удаление
        taskManager.deleteEpic(2);
        taskManager.deleteTask(1);
        System.out.println(taskManager.listTask());
        System.out.println(taskManager.listEpic());
        System.out.println(taskManager.listSubTask());



    }
}
