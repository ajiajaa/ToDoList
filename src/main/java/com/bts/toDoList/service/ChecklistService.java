package com.bts.toDoList.service;

import com.bts.toDoList.model.Checklist;
import com.bts.toDoList.model.User;
import com.bts.toDoList.repository.ChecklistRepository;
import com.bts.toDoList.repository.UserRepository;
import com.bts.toDoList.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Checklist createChecklist(Checklist checklist, String token) {
        String username = jwtTokenProvider.getUsername(token);
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(checklist::setUser);
        return checklistRepository.save(checklist);
    }

    public Optional<Checklist> getChecklistById(Long id) {
        return checklistRepository.findById(id);
    }
    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    public Checklist updateChecklist(Long id, Checklist checklist, String token) {
        String username = jwtTokenProvider.getUsername(token);
        Optional<User> user = userRepository.findByUsername(username);
        checklist.setId(id);
        user.ifPresent(checklist::setUser);
        return checklistRepository.save(checklist);
    }
    @Transactional
    public void deleteChecklist(Long id, String token) {
        checklistRepository.deleteById(id);
    }
}
