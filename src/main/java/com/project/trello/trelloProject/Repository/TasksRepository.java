package com.project.trello.trelloProject.Repository;

import com.project.trello.trelloProject.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

        List<Tasks> findByFoldersId(Long folderId);
}
