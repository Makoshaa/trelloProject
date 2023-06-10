package com.project.trello.trelloProject.Services;


import com.project.trello.trelloProject.Entity.Folders;
import com.project.trello.trelloProject.Entity.TaskCategories;
import com.project.trello.trelloProject.Repository.FoldersRepository;
import com.project.trello.trelloProject.Repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.tools.ForwardingFileObject;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FoldersRepository foldersRepository;
    private final TaskCategoriesService taskCategoriesService;
    private final TasksRepository tasksRepository;

    public List<Folders> foldersList (){
        List <Folders> folders=foldersRepository.findAll();
        return folders;
    }

    public void addFolder (Folders folders){
        foldersRepository.save(folders);
    }

    public Folders getFolderById (Long id){
        return foldersRepository.findById(id).orElse(null);
    }

    public void setCategory(Long folderId, Long categoryId){
        Folders folders=getFolderById(folderId);
        TaskCategories taskCategories=taskCategoriesService.getCategoryById(categoryId);

        if (folders.getCategoriesList()!=null && folders.getCategoriesList().size()>0){
            if (!folders.getCategoriesList().contains(taskCategories)){
                folders.getCategoriesList().add(taskCategories);
            }
        } else {
            List <TaskCategories> taskCategoriesList=new ArrayList<>();
            taskCategoriesList.add(taskCategories);
            folders.setCategoriesList(taskCategoriesList);
        }
        foldersRepository.save(folders);
    }

    public void removeCategory(Long folderId, Long categoryId){
        Folders folders=foldersRepository.findById(folderId).orElse(null);
        TaskCategories taskCategories=taskCategoriesService.getCategoryById(categoryId);

        if (folders.getCategoriesList()!=null && folders.getCategoriesList().size()>0){
                folders.getCategoriesList().remove(taskCategories);
        }
        foldersRepository.save(folders);
    }

}
