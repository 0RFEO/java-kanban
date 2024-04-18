
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager TaskManager = new TaskManager();

        Task task1 = new Task("Уборка", "Помыть пол");
        Task task2 = new Task("Гулять", "Погулять с собакой");
        TaskManager.createNewTask(task1);
        TaskManager.createNewTask(task2);

        Epic epic3 = new Epic("Эпик1", "готовка ужина");
        Epic epic4 = new Epic("Эпик2", "подготовка к др");
        TaskManager.createNewEpic(epic3);
        TaskManager.createNewEpic(epic4);

        Subtask subtask5 = new Subtask("Подзадача1", "попрыгать", 3);
        Subtask subtask6 = new Subtask("Подзадача2", "купить ноут", 3);
        Subtask subtask7 = new Subtask("Подзадача3", "купить снеков", 4);
        TaskManager.createNewSubtask(subtask5);
        TaskManager.createNewSubtask(subtask6);
        TaskManager.createNewSubtask(subtask7);

        TaskManager.getTaskById(task1.getId());
        TaskManager.getEpicById(epic4.getId());
        TaskManager.getSubtaskById(subtask7.getId());

        System.out.println(TaskManager.getHistory());

        TaskManager.getListOfSubtasksByOneEpic(epic3.getId());

        Task updateTask3 = new Task("Зарядка", "Штанга", task1.getId(), TaskStatus.IN_PROGRESS);
        TaskManager.updateTheTask(updateTask3);

        Epic updateEpic3 = new Epic("Эпик1.1", "готовка ужина", epic3.getId(), epic3.getStatus(), epic3.getSubtaskIds());
        TaskManager.updateTheEpic(updateEpic3);

        Subtask updateSubtask5 = new Subtask("Подзадача1",
                "попрыгать", subtask5.getId(), TaskStatus.IN_PROGRESS, 3);
        TaskManager.updateTheSubtask(updateSubtask5);
        Subtask updateSubtask7 = new Subtask("Подзадача3",
                "купить снеков", subtask7.getId(), TaskStatus.DONE, 4);
        TaskManager.updateTheSubtask(updateSubtask7);

        TaskManager.getListOfTasks();
        TaskManager.getListOfEpics();
        TaskManager.getListOfSubtasks();

        TaskManager.removeTaskById(task1.getId());
        TaskManager.removeEpicById(epic4.getId());
        TaskManager.removeSubtaskById(subtask6.getId());

        TaskManager.removeAllTasks();
        TaskManager.removeAllSubtasks();
        TaskManager.removeAllEpics();
    }
}
