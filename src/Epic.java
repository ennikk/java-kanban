import java.util.ArrayList;
import java.util.HashMap;

//должен хранить инфу о всех подзадачах,наверное списком id
//статус зависит от сабтасков о наверное лучше делать это в другом классе
public class Epic extends Task {
    private ArrayList<Integer> subTasksId;

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
        this.subTasksId = new ArrayList<>();
    }

    public void addSubTasksId(int subTasksId) {
        this.subTasksId.add(subTasksId);
    }

    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
    }


    public void calculateStatus(HashMap<Integer, SubTask> subTasks) {
        if (subTasksId.isEmpty()) {
            this.setStatus(Status.NEW);
            return;
        }
        int completedTasksCounter = 0;
        int newTasksCounter = 0;
        for (Integer subTaskId : subTasksId) {
            SubTask subTask = subTasks.get(subTaskId);
            if (subTask.getStatus() == Status.DONE) {
                completedTasksCounter += 1;
            } else if (subTask.getStatus() == Status.NEW){
                newTasksCounter += 1;
            }
        }
        if (subTasksId.size() == completedTasksCounter) {
            this.setStatus(Status.DONE);

        } else if (subTasksId.size() == newTasksCounter){
            this.setStatus(Status.NEW);

        } else {
            this.setStatus(Status.IN_PROGRESS);

        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subTasksId=" + subTasksId +
                '}';
    }
}
