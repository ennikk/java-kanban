package model;

//знает свой эпик
public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, Status status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        if (this.getId() != epicId) {
            this.epicId = epicId;
        } else {
            this.epicId = -1;
        }
    }

    @Override
    public void setId(int id) {
        if (epicId != id) {
            super.setId(id);
        } else {
            super.setId(-1);
        }
    }

    @Override
    public String toString() {
        return "model.SubTask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
