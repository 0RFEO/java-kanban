package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private int counter = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();


    public void createNewTask(Task newTask) {
        int taskId = ++counter;
        newTask.setId(taskId);
        tasks.put(taskId, newTask);
    }


    public void createNewSubtask(Subtask newSubtask) {
        int newSubtaskId = ++counter;
        newSubtask.setId(newSubtaskId);
        subtasks.put(newSubtaskId, newSubtask);
        int epicId = newSubtask.getEpicId();
        ArrayList<Integer> subtaskIds = epics.get(epicId).getSubtaskIds();
        subtaskIds.add(newSubtaskId);
        checkEpicStatus(epicId);
    }


    public void createNewEpic(Epic newEpic) {
        int epicId = ++counter;
        newEpic.setId(epicId);
        epics.put(epicId, newEpic);
    }


    public void removeAllTasks() {
        tasks.clear();
    }


    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            checkEpicStatus(epic.getId());
        }
        subtasks.clear();
    }


    public void removeAllEpics() {
        for (Epic epic : epics.values()) {
            int epicId = epic.getId();
            ArrayList<Integer> subtaskIds = epics.get(epicId).getSubtaskIds();
            for (Integer subtaskId : subtaskIds) {
                subtasks.remove(subtaskId);
            }
        }
        epics.clear();
    }


    public ArrayList<Task> getListOfTasks() {
        return new ArrayList<>(tasks.values());
    }


    public ArrayList<Epic> getListOfEpics() {
        return new ArrayList<>(epics.values());
    }


    public ArrayList<Subtask> getListOfSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }


    public Epic getEpicById(int id) {
        return epics.get(id);
    }


    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }


    public ArrayList<Subtask> getListOfSubtasksByOneEpic(int id) {
        ArrayList<Integer> subtaskIds = epics.get(id).getSubtaskIds();
        ArrayList<Subtask> subtasksByOneEpic = new ArrayList<>();
        for (int subtaskId : subtaskIds) {
            subtasksByOneEpic.add(subtasks.get(subtaskId));
        }
        return subtasksByOneEpic;
    }


    public void removeTaskById(int taskId) {
        tasks.remove(taskId);
    }


    public void removeEpicById(int epicId) {
        ArrayList<Integer> subtaskIds = epics.get(epicId).getSubtaskIds();
        for (Integer subtaskId : subtaskIds) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }


    public void removeSubtaskById(int subtaskIdForRemove) {
        int epicId = subtasks.get(subtaskIdForRemove).getEpicId();
        ArrayList<Integer> subtaskIds = epics.get(epicId).getSubtaskIds();
        subtaskIds.remove((Integer) subtaskIdForRemove);
        subtasks.remove(subtaskIdForRemove);
        checkEpicStatus(epicId);
    }


    public void updateTheTask(Task updateTask) {
        tasks.put(updateTask.getId(), updateTask);
    }


    public void updateTheEpic(Epic updateEpic) {
        epics.put(updateEpic.getId(), updateEpic);
    }


    public void updateTheSubtask(Subtask updateSubtask) {
        subtasks.put(updateSubtask.getId(), updateSubtask);
        int epicId = subtasks.get(updateSubtask.getId()).getEpicId();
        checkEpicStatus(epicId);
    }


    public void checkEpicStatus(int epicId) {
        int counterNEW = 0;
        int counterDONE = 0;
        ArrayList<Integer> subtaskIds = epics.get(epicId).getSubtaskIds();
        for (Integer subtaskId : subtaskIds) {
            if (subtasks.get(subtaskId).getStatus().equals(TaskStatus.NEW)) {
                counterNEW++;
            } else if (subtasks.get(subtaskId).getStatus().equals(TaskStatus.DONE)) {
                counterDONE++;
            }
        }
        if (subtaskIds.size() == counterNEW || subtaskIds.isEmpty()) {
            epics.get(epicId).setStatus(TaskStatus.NEW);
        } else if (subtaskIds.size() == counterDONE) {
            epics.get(epicId).setStatus(TaskStatus.DONE);
        } else {
            epics.get(epicId).setStatus(TaskStatus.IN_PROGRESS);
        }
    }





}
