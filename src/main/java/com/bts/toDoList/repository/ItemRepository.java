package com.bts.toDoList.repository;

import com.bts.toDoList.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByChecklistId(Long checklistId);
}