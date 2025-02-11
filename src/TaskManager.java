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

    public static int getCounter() {
        return counter;
    }

    //создать новую задачу
    public int newTask(Task task){
        task.setId(counter);
        taskMap.put(counter,task);
        int taskId = counter;
        counter++;
        return taskId;
    }
    //обновить задачу
    public Task updateTask(int idTask, Task task){
        if (taskMap.containsKey(idTask)) {
            task.setId(idTask);
            taskMap.put(idTask, task);
            return task;
        } else {
            return null;
        }
    }

    //получение списка задач
    public ArrayList<String> listTask(){
        ArrayList<String> listTasks = new ArrayList<>();
        for (Task task: taskMap.values()){
            listTasks.add(task.toString());
        }
        return listTasks;
    }

//геттер задач
    public HashMap<Integer, Task> getTaskMap() {
        return taskMap;
    }

    //получение задачи по id
    public Task getTaskById(int id){
        return taskMap.get(id);
    }

    //удаление всех задач
    public HashMap<Integer, Task> deleteAllTasks(){
        taskMap.clear();
        return taskMap;
    }

    //удаление задачи по id
    public HashMap<Integer, Task> deleteTask(int id){
        taskMap.remove(id);
        return taskMap;
    }

    //создать новую подзадачу
    public int newSubTask(SubTask subTask){
        if(epicMap.containsKey(subTask.getEpicId())) {
            int subTaskId = counter;
            subTask.setId(subTaskId);
            subTaskMap.put(subTaskId, subTask);
            epicMap.get(subTask.getEpicId()).addSubTasksId(subTaskId);
            this.calculateStatusEpic(subTask.getEpicId());
            counter++;
            return subTaskId;
        }
        return -1;
    }
    //обновить подзадачу
    public SubTask updateSubTask(int idSubTask, SubTask subTask){
        if (subTaskMap.containsKey(idSubTask)) {
            subTask.setId(idSubTask);
            subTaskMap.put(idSubTask, subTask);
            this.calculateStatusEpic(subTask.getEpicId());
            return subTask;
        } else {
            return null;
        }
    }
    //получение списка подзадач
    public ArrayList<String> listSubTask(){
        ArrayList<String> listSubTasks = new ArrayList<>();
        for (SubTask subTask: subTaskMap.values()){
            listSubTasks.add(subTask.toString());
        }
        return listSubTasks;
    }

    //получение подзадачь определенного эпика
public ArrayList<SubTask> getSubTasksForEpic(int epicId){
    ArrayList<SubTask> subTasks = new ArrayList<>();
    ArrayList<Integer> subTasksId = epicMap.get(epicId).getSubTasksId();
    for(int subTaskId: subTasksId){
        subTasks.add(subTaskMap.get(subTaskId));
    }
    return subTasks;
}

    // гтр подзадач
    public HashMap<Integer, SubTask> getSubTaskMap() {
        return subTaskMap;
    }

    //получение подзадачи по id
    public Task getSubTaskById(int id){
        return subTaskMap.get(id);
    }

    //удаление всех подзадач
    public HashMap<Integer, SubTask> deleteAllSubTask(){
        subTaskMap.clear();
        return subTaskMap;
    }

    //удаление подзадачи по id
    public HashMap<Integer, SubTask> deleteSubTask(int id){
        subTaskMap.remove(id);
        return subTaskMap;
    }

    //создать новый эпик
    public int newEpic(Epic epic){
        epic.setId(counter);
        epicMap.put(counter,epic);
        int epicId = counter;
        counter++;
        return epicId;
    }

    //обновить эпик
    public Epic updateEpic(int idEpic, Epic epic){
        if (epicMap.containsKey(idEpic)) {
            epic.setId(idEpic);
            for(SubTask subTask: subTaskMap.values()){
               if (subTask.getEpicId() == idEpic){
                   epic.addSubTasksId(subTask.getId());
               }
            }
            epicMap.put(idEpic, epic);
            this.calculateStatusEpic(idEpic);
            return epic;

        } else {
            return null;
        }
    }
    // получение списка эпиков
    public ArrayList<String> listEpic(){
        ArrayList<String> listEpics = new ArrayList<>();
        for (Epic epic: epicMap.values()){
            listEpics.add(epic.toString());
        }
        return listEpics;
    }

    // гтр эпиков
    public HashMap<Integer, Epic> getEpicMap() {
        return epicMap;
    }

    //получение эпика по id
    public Task getEpicById(int id){
        return epicMap.get(id);
    }

    //удаление всех эпиков
    public HashMap<Integer, Epic> deleteAllEpic(){
        epicMap.clear();
        return epicMap;
    }

    //удаление эпика по id
    public HashMap<Integer, Epic> deleteEpic(int id){
      if (epicMap.containsKey(id)) {
          for (int subTaskId : epicMap.get(id).getSubTasksId()) {
              this.deleteSubTask(subTaskId);
          }
          epicMap.remove(id);
      }
        return epicMap;
    }

    //расчет статуса эпика
    public void calculateStatusEpic(int idEpic){
       epicMap.get(idEpic).calculateStatus(subTaskMap);
    }
}
