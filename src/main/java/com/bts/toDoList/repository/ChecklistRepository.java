package com.bts.toDoList.repository;

import com.bts.toDoList.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    List<Checklist> findByUserId(Long userId);
}
