package manager;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;


public class InMemoryTaskManager implements TaskManager {
    private static int counter = 0;
    private HistoryManager historyManager;

    private HashMap<Integer, Task> taskMap;
    private HashMap<Integer, SubTask> subTaskMap;
    private HashMap<Integer, Epic> epicMap;

    public InMemoryTaskManager() {
        this.taskMap = new HashMap<>();
        this.subTaskMap = new HashMap<>();
        this.epicMap = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    private int getCounter() {
        return counter++;
    }


    @Override
    public int newTask(Task task) {
        task.setId(getCounter());
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public Task updateTask(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
            return task;
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }


    @Override
    public Task getTaskById(int id) {
        addToHistoryManager(taskMap.get(id));
        return taskMap.get(id);
    }


    @Override
    public void deleteAllTasks() {
        for (Integer id : taskMap.keySet()) {
            historyManager.removeTask(id);
        }
        taskMap.clear();
    }


    @Override
    public void deleteTask(int id) {
        historyManager.removeTask(id);
        taskMap.remove(id);
    }


    @Override
    public int newSubTask(SubTask subTask) {
        if (epicMap.containsKey(subTask.getEpicId())) {
            subTask.setId(getCounter());
            subTaskMap.put(subTask.getId(), subTask);
            epicMap.get(subTask.getEpicId()).addSubTasksId(subTask.getId());
            calculateStatusEpic(subTask.getEpicId());
            return subTask.getId();
        }
        return -1;
    }


    @Override
    public SubTask updateSubTask(SubTask subTask) {
        if (subTaskMap.containsKey(subTask.getId())) {
            subTaskMap.put(subTask.getId(), subTask);
            calculateStatusEpic(subTask.getEpicId());
            return subTask;
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<SubTask> getSubTaskList() {
        return new ArrayList<>(subTaskMap.values());
    }


    @Override
    public ArrayList<SubTask> getSubTasksForEpic(int epicId) {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        ArrayList<Integer> subTasksId = epicMap.get(epicId).getSubTasksId();
        for (int subTaskId : subTasksId) {
            subTasks.add(subTaskMap.get(subTaskId));
        }
        return subTasks;
    }


    @Override
    public SubTask getSubTaskById(int id) {
        addToHistoryManager(subTaskMap.get(id));
        return subTaskMap.get(id);
    }


    @Override
    public void deleteAllSubTask() {
        for (Integer id : subTaskMap.keySet()) {
            historyManager.removeTask(id);
        }
        subTaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.setStatus(Status.NEW);
            epic.getSubTasksId().clear();
        }
    }


    @Override
    public void deleteSubTask(int id) {
        int idEpic = subTaskMap.get(id).getEpicId();
        historyManager.removeTask(id);
        subTaskMap.remove(id);
        epicMap.get(idEpic).getSubTasksId().remove(Integer.valueOf(id));
        calculateStatusEpic(idEpic);
    }


    @Override
    public int newEpic(Epic epic) {
        epic.setId(getCounter());
        epicMap.put(epic.getId(), epic);
        return epic.getId();
    }


    @Override
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


    @Override
    public ArrayList<Epic> getEpicList() {
        return new ArrayList<>(epicMap.values());
    }


    @Override
    public Epic getEpicById(int id) {
        addToHistoryManager(epicMap.get(id));
        return epicMap.get(id);
    }


    @Override
    public void deleteAllEpic() {
        for (Integer id : epicMap.keySet() ){
            historyManager.removeTask(id);
        }
        for (Integer id : subTaskMap.keySet() ){
            historyManager.removeTask(id);
        }
        epicMap.clear();
        subTaskMap.clear();

    }


    @Override
    public void deleteEpic(int id) {
        if (epicMap.containsKey(id)) {
            for (int subTaskId : epicMap.get(id).getSubTasksId()) {
                subTaskMap.remove(subTaskId);
                historyManager.removeTask(subTaskId);
            }
            epicMap.remove(id);
            historyManager.removeTask(id);
        }
    }


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
            } else if (subTask.getStatus() == Status.NEW) {
                newTasksCounter += 1;
            }
        }
        if (subTasksId.size() == completedTasksCounter) {
            epic.setStatus(Status.DONE);

        } else if (subTasksId.size() == newTasksCounter) {
            epic.setStatus(Status.NEW);

        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    private void addToHistoryManager(Task anyTask){
        historyManager.addToHistory(copyTask(anyTask));
    }

@Override
    public ArrayList<Task> getHistory(){
        return historyManager.getHistory();
    }

    private static Task copyTask(Task task){

        if(task instanceof Epic epic){
            Epic newEpic = new Epic(epic.getName(), epic.getDescription());
            newEpic.setId(epic.getId());
            for(int subTaskId: epic.getSubTasksId()){
                newEpic.addSubTasksId(subTaskId);
            }
            return newEpic;
        } else return task;

    }
}



