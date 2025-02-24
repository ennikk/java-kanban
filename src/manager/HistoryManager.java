package manager;

import model.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void addToHistory(Task anyTask);

    ArrayList<Task> getHistory();
}
