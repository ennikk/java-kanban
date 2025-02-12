import java.util.ArrayList;
import java.util.HashMap;

//будет хранить три мапы, задачи,эпики и сабы. присваивать id
//менеджер не выбирает статусы, они приходят от пользователя
public class TaskManager {
    private static int counter = 0;
    private HashMap<Integer, Task> taskMap;
    private HashMap<Integer, SubTask> subTaskMap;
    private HashMap<Integer, Epic> epicMap;

    public TaskManager() {
        this.taskMap = new HashMap<>();
        this.subTaskMap = new HashMap<>();
        this.epicMap = new HashMap<>();
    }

     private int getCounter() {
        return counter++;
    }

    //создать новую задачу
    public int newTask(Task task) {
        task.setId(this.getCounter());
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    //обновить задачу
    //подразумивается что на вход подается уже измененный обЪект с таким же id
    //поменять тест
    public Task updateTask(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
            return task;
        } else {
            return null;
        }
    }

    //получение списка задач
    public ArrayList<Task> getTaskList() {
        return  new ArrayList<>(taskMap.values());
    }

    //получение задачи по id
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    //удаление всех задач
    public void deleteAllTasks() {
        taskMap.clear();
    }

    //удаление задачи по id
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    //создать новую подзадачу
    public int newSubTask(SubTask subTask) {
        if (epicMap.containsKey(subTask.getEpicId())) {
            subTask.setId(this.getCounter());
            subTaskMap.put(subTask.getId(), subTask);
            epicMap.get(subTask.getEpicId()).addSubTasksId(subTask.getId());
            this.calculateStatusEpic(subTask.getEpicId());
            return subTask.getId();
        }
        return -1;
    }

    //обновить подзадачу
    public SubTask updateSubTask(SubTask subTask) {
        if (subTaskMap.containsKey(subTask.getId())) {
            subTaskMap.put(subTask.getId(), subTask);
            this.calculateStatusEpic(subTask.getEpicId());
            return subTask;
        } else {
            return null;
        }
    }

    //получение списка подзадач
    public ArrayList<SubTask> getSubTaskList() {
        return new ArrayList<>(subTaskMap.values());
    }

    //получение подзадачь определенного эпика
    public ArrayList<SubTask> getSubTasksForEpic(int epicId) {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        ArrayList<Integer> subTasksId = epicMap.get(epicId).getSubTasksId();
        for (int subTaskId : subTasksId) {
            subTasks.add(subTaskMap.get(subTaskId));
        }
        return subTasks;
    }

    //получение подзадачи по id
    public Task getSubTaskById(int id) {
        return subTaskMap.get(id);
    }

    //удаление всех подзадач
    public void deleteAllSubTask() {
        subTaskMap.clear();
        for(int idEpic : epicMap.keySet()){
            calculateStatusEpic(idEpic);
        }
    }

    //удаление подзадачи по id
    public void deleteSubTask(int id) {
        int idEpic = subTaskMap.get(id).getEpicId();
        subTaskMap.remove(id);
        calculateStatusEpic(idEpic);
    }

    //создать новый эпик
    public int newEpic(Epic epic) {
        epic.setId(this.getCounter());
        epicMap.put(epic.getId(), epic);
        return epic.getId();
    }

    //обновить эпик
    public Epic updateEpic(Epic epic) {
        if (epicMap.containsKey(epic.getId())) {
            Epic epicOnMap = epicMap.get(epic.getId());
            epicOnMap.setName(epic.getName());
            epicOnMap.setDescription(epic.getDescription());
            return epic;
        } else {
            return null;
        }
    }

    // получение списка эпиков
    public ArrayList<Epic> getEpicList() {
        return new ArrayList<>(epicMap.values());
    }

    //получение эпика по id
    public Task getEpicById(int id) {
        return epicMap.get(id);
    }

    //удаление всех эпиков
    public void deleteAllEpic() {
        epicMap.clear();
        subTaskMap.clear();
    }

    //удаление эпика по id
    public void deleteEpic(int id) {
        if (epicMap.containsKey(id)) {
            for (int subTaskId : epicMap.get(id).getSubTasksId()) {
                subTaskMap.remove(subTaskId);
            }
            epicMap.remove(id);
        }
    }

    //расчет статуса эпика
    private void calculateStatusEpic(int idEpic) {
        Epic epic = epicMap.get(idEpic);
        ArrayList<Integer> subTasksId = epic.getSubTasksId();
        if (subTasksId.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }
        int completedTasksCounter = 0;
        int newTasksCounter = 0;
        for (Integer subTaskId : subTasksId) {
            SubTask subTask = subTaskMap.get(subTaskId);
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            } else if (subTask.getStatus() == Status.DONE) {
                completedTasksCounter += 1;
            } else if (subTask.getStatus() == Status.NEW){
                newTasksCounter += 1;
            }
        }
        if (subTasksId.size() == completedTasksCounter) {
            epic.setStatus(Status.DONE);

        } else if (subTasksId.size() == newTasksCounter){
            epic.setStatus(Status.NEW);

        }
    }
}
