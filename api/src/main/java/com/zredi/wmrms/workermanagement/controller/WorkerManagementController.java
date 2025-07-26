package com.zredi.wmrms.workermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zredi.wmrms.workermanagement.dto.CreateNewWorkmanRequestDTO;
import com.zredi.wmrms.workermanagement.dto.ModifyWorkmanRequestDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanCategoryResponseDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanDepartmentResponseDTO;
import com.zredi.wmrms.workermanagement.service.DefaultWorkmanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/v1/workermanagement")
@RequiredArgsConstructor
public class WorkerManagementController {

  
  private DefaultWorkmanService workmanService;
    
  
  @GetMapping("/category")
  public List<WorkmanCategoryResponseDTO> getAllWorkerCategories(){
    return workmanService.getAllWorkerCategory();
  }
  
  
  @GetMapping("/department")
  public List<WorkmanDepartmentResponseDTO> getAllWorkerDepartment(){
    return workmanService.getAllWorkerDepartment();
  }
  
  
  @PostMapping("/workman")
  public void createWorkmanRecord(@Valid @RequestBody CreateNewWorkmanRequestDTO request) {
    
  }
  
  @PutMapping("/workman/{empNo}")
  public void updateWorkmanRecord(@PathVariable("empNo") Integer empNo,@Valid @RequestBody ModifyWorkmanRequestDTO request) {
  
  }
  
}
