package com.bts.toDoList.service;

import com.bts.toDoList.model.Checklist;
import com.bts.toDoList.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    public Checklist createChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    public Optional<Checklist> getChecklistById(Long id) {
        return checklistRepository.findById(id);
    }

    public Checklist updateChecklist(Long id, Checklist checklist) {
        checklist.setId(id);
        return checklistRepository.save(checklist);
    }

    public void deleteChecklist(Long id) {
        checklistRepository.deleteById(id);
    }
}
