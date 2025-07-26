package com.zredi.wmrms.workermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zredi.wmrms.workermanagement.dto.CreateNewWorkmanRequestDTO;
import com.zredi.wmrms.workermanagement.dto.DuplicateWorkmanException;
import com.zredi.wmrms.workermanagement.dto.ModifyWorkmanRequestDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanCategoryResponseDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanDepartmentResponseDTO;
import com.zredi.wmrms.workermanagement.mapper.WorkMangementRecordMapper;
import com.zredi.wmrms.workermanagement.model.Workman;
import com.zredi.wmrms.workermanagement.repository.WMRMSCategoryRepository;
import com.zredi.wmrms.workermanagement.repository.WMRMSDepratmentRepository;
import com.zredi.wmrms.workermanagement.repository.WorkmanRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultWorkmanService {

  
  private final WMRMSCategoryRepository categoryRepository;
  private final WMRMSDepratmentRepository departmentRepository;
  private final WorkmanRepository workmapRepository;
  private final WorkMangementRecordMapper workerManagetRecordMapper;
  
  
  
  public void createNewWorkmanRecord(CreateNewWorkmanRequestDTO request) {
   boolean duplicateRecord= workmapRepository.existsById(request.getEmpNumber());
   if(duplicateRecord) {
     throw  new DuplicateWorkmanException("Workman already found with emp number")
   } 
   
   
   
   
    workerManagetRecordMapper.mapCreateWorkmanRequesDTOToEntity(request);
  }
  
  
  public void modifyNewWorkManRecord(ModifyWorkmanRequestDTO request) {
    
  }
  
 
  Workman getWorkmanById(Integer workmanId) {
    return workmapRepository.findById(workmanId).orElseThrow(()->new DuplicateWorkmanException("Workman already found with emp number"))
  }
  
  
  
  public List<WorkmanCategoryResponseDTO> getAllWorkerCategory(){
     var categoryList=categoryRepository.findAll();
     return workerManagetRecordMapper.mapWRMMSCategoryEntitiesToDTO(categoryList);
  }
  
  public List<WorkmanDepartmentResponseDTO> getAllWorkerDepartment(){
    var departmentList=departmentRepository.findAll();
    return workerManagetRecordMapper.mapWRMMSDepartmentEntitiesToDTO(departmentList);
  }
  
}
