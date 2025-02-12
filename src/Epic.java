import java.util.ArrayList;

//должен хранить инфу о всех подзадачах,наверное списком id
//статус зависит от сабтасков о наверное лучше делать это в другом классе
public class Epic extends Task {
    private ArrayList<Integer> subTasksId = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
    }

    public void addSubTasksId(int subTasksId) {
        this.subTasksId.add(subTasksId);
    }

    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
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
