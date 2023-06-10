package com.project.trello.trelloProject.Repository;

import com.project.trello.trelloProject.Entity.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoldersRepository extends JpaRepository<Folders, Long> {
}
