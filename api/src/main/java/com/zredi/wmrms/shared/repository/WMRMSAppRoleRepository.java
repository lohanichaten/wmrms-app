package com.zredi.wmrms.shared.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zredi.wmrms.shared.model.WMRMSAppRole;

public interface WMRMSAppRoleRepository extends JpaRepository<WMRMSAppRole,Integer>{

  Optional<WMRMSAppRole> findByRoleName(String roleName);

}
