package manager;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//история хранит копию задачи,копии эпиков должны хранить id позадач,а копии подзадачи id эпика!
// создать внутренний класс NodeTask
public class InMemoryHistoryManager implements HistoryManager {
    private Map<Integer, NodeTask> history;
    private static final int MAX_HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        history = new HashMap<>();
    }

    class NodeTask {
        private Task task;
        private NodeTask prev;
        private NodeTask next;

        public NodeTask(NodeTask prev, Task task, NodeTask next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    private NodeTask head;
    private NodeTask tail;

    private NodeTask linkLast(Task task) {
        NodeTask oldTail = tail;
        NodeTask newLastNode = new NodeTask(oldTail, task, null);
        tail = newLastNode;
        if (oldTail == null) {
            head = newLastNode;
        } else {
            oldTail.next = newLastNode;
        }
        return newLastNode;
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        NodeTask correntNode = head;
        while (correntNode != null) {
            tasks.add(correntNode.task);
            correntNode = correntNode.next;
        }
        return tasks;
    }

    private void removeNode(NodeTask node) {
        NodeTask prevNode = node.prev;
        NodeTask nextNode = node.next;
        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.next = nextNode;
        }
        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

    }


    @Override
    public void addToHistory(Task anyTask) {
        if (anyTask != null) {
            int anyTaskId = anyTask.getId();
            if (history.containsKey(anyTaskId)) {
                removeTask(anyTaskId);
            }
            history.put(anyTask.getId(), linkLast(anyTask));
        }
    }


    @Override
    public ArrayList<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void removeTask(int id) {
        if (history.containsKey(id)) {
            removeNode(history.get(id));
            history.remove(id);
        }
    }
}