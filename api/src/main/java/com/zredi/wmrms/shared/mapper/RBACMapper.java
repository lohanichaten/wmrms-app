package com.zredi.wmrms.shared.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.zredi.wmrms.shared.dto.AppPermisionResponseDTO;
import com.zredi.wmrms.shared.dto.AppRoleResponseDTO;
import com.zredi.wmrms.shared.dto.AppUserResponseDTO;
import com.zredi.wmrms.shared.model.WMRMSAppPermission;
import com.zredi.wmrms.shared.model.WMRMSAppRole;
import com.zredi.wmrms.shared.model.WMRMSUser;

@Mapper(componentModel = "spring")
public interface RBACMapper {

  
      AppPermisionResponseDTO mapEntityToAppPermissionResponse(WMRMSAppPermission permission);
      List<AppPermisionResponseDTO> mapEntitiesToAppPermissions(List<WMRMSAppPermission> permisisons);
      
      
      AppRoleResponseDTO mapEntityToAppRoleResponse(WMRMSAppRole role);
      List<AppRoleResponseDTO> mapEntitiesToAppRoleResponse(List<WMRMSAppRole> roles);
      
      
      AppUserResponseDTO mapEntityToAppUser(WMRMSUser user);
  
}
