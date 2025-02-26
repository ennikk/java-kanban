package manager;

import model.Task;

import java.util.ArrayList;
//история хранит копию задачи,копии эпиков должны хранить id позадач,а копии подзадачи id эпика!

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> history;
    private static final int MAX_HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        history = new ArrayList<>();
    }

    @Override
    public void addToHistory(Task anyTask){
        if (anyTask != null) {
            history.add(anyTask);
            if (history.size() > MAX_HISTORY_SIZE) {
                history.removeFirst();
            }
        }
    }

    @Override
    public ArrayList<Task> getHistory(){
        return new ArrayList<>(history);
    }
}
