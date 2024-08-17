package com.bts.toDoList.controller;

import com.bts.toDoList.model.Item;
import com.bts.toDoList.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checklists/{checklistId}/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@PathVariable Long checklistId, @RequestBody Item item, @RequestHeader("Authorization") String token) {
        Item createdItem = itemService.createItem(checklistId, item, token);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItemsByChecklistId(@PathVariable Long checklistId, @RequestHeader("Authorization") String token) {
        List<Item> items = itemService.getItemsByChecklistId(checklistId, token);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long checklistId, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        Optional<Item> item = itemService.getItemById(checklistId, id, token);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> markItemAsCompleted(@PathVariable Long checklistId, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        Item updatedItem = itemService.markItemAsCompleted(checklistId, id, token);
        return ResponseEntity.ok(updatedItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long checklistId, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        itemService.deleteItem(checklistId, id, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rename/{id}")
    public ResponseEntity<Item> renameItem(@PathVariable Long checklistId, @PathVariable Long id, @RequestBody Item item, @RequestHeader("Authorization") String token) {
        Item renamedItem = itemService.renameItem(checklistId, id, item, token);
        return ResponseEntity.ok(renamedItem);
    }
}
