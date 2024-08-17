package com.bts.toDoList.repository;

import com.bts.toDoList.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByChecklistId(Long checklistId);
    Optional<Item> findByIdAndChecklistId(Long id, Long checklistId);
    void deleteByIdAndChecklistId(Long id, Long checklistId);
}
