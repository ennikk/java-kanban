package manager;

import model.Task;

import java.util.ArrayList;
//история хранит копию задачи,копии эпиков должны хранить id позадач,а копии подзадачи id эпика!
//чтобы при удалении оставалась возможность
public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> history;

    public InMemoryHistoryManager() {
        history = new ArrayList<>();
    }

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
