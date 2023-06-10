package com.project.trello.trelloProject.Controller;


import com.project.trello.trelloProject.Entity.Folders;
import com.project.trello.trelloProject.Entity.TaskCategories;
import com.project.trello.trelloProject.Entity.Tasks;
import com.project.trello.trelloProject.Services.FolderService;
import com.project.trello.trelloProject.Services.TaskCategoriesService;
import com.project.trello.trelloProject.Services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FolderService folderService;
    private final TaskCategoriesService taskCategoriesService;
    private final TasksService tasksService;


    @GetMapping(value = "/")
    public String getIndexPage(Model model) {
        List<Folders> foldersList = folderService.foldersList();
        model.addAttribute("papki", foldersList);
        return "index";
    }

    @PostMapping(value = "/add-folder")
    public String addFolder(Folders folders) {
        folderService.addFolder(folders);
        return "redirect:/";
    }

    @GetMapping(value = "/folder-details")
    public String getFolder(@RequestParam(name = "id") Long id, Model model) {
        Folders foldersList = folderService.getFolderById(id);
        model.addAttribute("papkalar", foldersList);

        List<TaskCategories> taskCategoriesList = taskCategoriesService.getCategories();
        taskCategoriesList.removeAll(foldersList.getCategoriesList());
        model.addAttribute("categoryalar", taskCategoriesList);

        List<Tasks> tasksList = tasksService.getTasksByFolderId(id);
        model.addAttribute("tasktar",tasksList);
        return "details";
    }

    @PostMapping(value = "/add-category")
    public String addCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "category_id") Long categoryId) {
        folderService.setCategory(folderId, categoryId);
        return "redirect:/folder-details?id=" + folderId;
    }

    @PostMapping(value = "/remove-category")
    public String removeCategory(@RequestParam(name = "folder_id") Long folderId,
                                 @RequestParam(name = "category_id") Long categoryId) {
        folderService.removeCategory(folderId, categoryId);
        return "redirect:/folder-details?id=" + folderId;
    }

    @PostMapping(value = "/add-task")
    public String addTask(@RequestParam("folder_id") Long folderId, @ModelAttribute Tasks task){
        Folders folder = folderService.getFolderById(folderId);
        task.setFolders(folder);
        tasksService.createTask(task);
        return "redirect:/folder-details?id=" + folderId;
    }

    @GetMapping(value = "/task-details")
    public String editTask(@RequestParam("id") Long taskId,
                           @RequestParam("folder_id") Long folderId, Model model) {

        Tasks task = tasksService.getTasksById(taskId);
        Folders folder = folderService.getFolderById(folderId);
        model.addAttribute("task", task);
        model.addAttribute("folderler", folder);
        return "detailsTask";
    }


    @PostMapping(value = "/update-task")
    public String updateTask(@ModelAttribute("updatedTasks") Tasks updatedTasks,
                             @RequestParam("folder_id") Long folderId) {
        Folders folder = folderService.getFolderById(folderId);
        updatedTasks.setFolders(folder);
        tasksService.updateTask(updatedTasks);
        return "redirect:/folder-details?id=" + folderId;
    }

    @PostMapping(value = "/delete-task")
    public String deleteTask(@RequestParam("id") Long taskId, @RequestParam ("folder_id") Long folderId){
        tasksService.deleteTask(taskId);
        return "redirect:/folder-details?id=" + folderId;
    }
}
