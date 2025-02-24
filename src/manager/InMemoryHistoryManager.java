package manager;

import model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> history = new ArrayList<>();

    @Override
    public void addToHistory(Task anyTask){
        history.add(anyTask);
        if (history.size()> 10) {
            history.remove(0);
        }
    }

    @Override
    public ArrayList<Task> getHistory(){
        return history;
    }
}
