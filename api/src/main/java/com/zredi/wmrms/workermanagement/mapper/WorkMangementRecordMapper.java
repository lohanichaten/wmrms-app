package com.zredi.wmrms.workermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.zredi.wmrms.workermanagement.dto.CreateNewWorkmanRequestDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanCategoryResponseDTO;
import com.zredi.wmrms.workermanagement.dto.WorkmanDepartmentResponseDTO;
import com.zredi.wmrms.workermanagement.model.WMRMSCategory;
import com.zredi.wmrms.workermanagement.model.WMRMSDepartment;
import com.zredi.wmrms.workermanagement.model.Workman;

@Mapper(componentModel = "spring")
public interface WorkMangementRecordMapper {

  WorkmanCategoryResponseDTO mapWRMMSCategoryEntityToDTO(WMRMSCategory category);
  List<WorkmanCategoryResponseDTO> mapWRMMSCategoryEntitiesToDTO(List<WMRMSCategory> categories);
  
  
  WorkmanDepartmentResponseDTO mapWRMMSDepartmentToDTO(WMRMSDepartment department);
  List<WorkmanDepartmentResponseDTO> mapWRMMSDepartmentEntitiesToDTO(List<WMRMSDepartment> departments);


  Workman  mapCreateWorkmanRequesDTOToEntity(CreateNewWorkmanRequestDTO request);

}
