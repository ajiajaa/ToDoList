package com.bts.toDoList.controller;

import com.bts.toDoList.model.Checklist;
import com.bts.toDoList.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @PostMapping
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist, @RequestHeader("Authorization") String token) {
        Checklist createdChecklist = checklistService.createChecklist(checklist, token);
        return ResponseEntity.ok(createdChecklist);
    }
    @GetMapping
    public ResponseEntity<List<Checklist>> getChecklist(@RequestHeader("Authorization") String token) {
        List<Checklist> checklist= checklistService.getAllChecklists();
        return ResponseEntity.ok(checklist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> getChecklistById(@PathVariable Long id) {
        Optional<Checklist> checklist = checklistService.getChecklistById(id);
        return checklist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checklist> updateChecklist(@PathVariable Long id, @RequestBody Checklist checklist, @RequestHeader("Authorization") String token) {
        Checklist updatedChecklist = checklistService.updateChecklist(id, checklist, token);
        return ResponseEntity.ok(updatedChecklist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        checklistService.deleteChecklist(id, token);
        return ResponseEntity.noContent().build();
    }
}
