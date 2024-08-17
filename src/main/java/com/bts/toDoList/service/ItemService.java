package com.bts.toDoList.service;

import com.bts.toDoList.model.Checklist;
import com.bts.toDoList.model.Item;
import com.bts.toDoList.repository.ChecklistRepository;
import com.bts.toDoList.repository.ItemRepository;
import com.bts.toDoList.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Item createItem(Long checklistId, Item item, String token) {
        Checklist checklist = checklistRepository.findById(checklistId)
                .orElseThrow(() -> new RuntimeException("Checklist not found"));
        item.setChecklist(checklist);
        return itemRepository.save(item);
    }

    public List<Item> getItemsByChecklistId(Long checklistId, String token) {
        return itemRepository.findByChecklistId(checklistId);
    }

    public Optional<Item> getItemById(Long checklistId, Long itemId, String token) {
        return itemRepository.findByIdAndChecklistId(itemId, checklistId);
    }

    @Transactional
    public Item markItemAsCompleted(Long checklistId, Long itemId, String token) {
        Checklist checklist = checklistRepository.findById(checklistId)
                .orElseThrow(() -> new RuntimeException("Checklist not found"));
        Item existingItem = itemRepository.findByIdAndChecklistId(itemId, checklistId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setCompleted(true);
        return itemRepository.save(existingItem);
    }
    @Transactional
    public void deleteItem(Long checklistId, Long itemId, String token) {
        itemRepository.deleteByIdAndChecklistId(itemId, checklistId);
    }

    public Item renameItem(Long checklistId, Long itemId, Item item, String token) {
        Checklist checklist = checklistRepository.findById(checklistId)
                .orElseThrow(() -> new RuntimeException("Checklist not found"));
        Item existingItem = itemRepository.findByIdAndChecklistId(itemId, checklistId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(item.getName());
        return itemRepository.save(existingItem);
    }
}
