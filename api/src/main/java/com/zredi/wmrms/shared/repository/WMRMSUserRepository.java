package com.zredi.wmrms.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zredi.wmrms.shared.model.WMRMSUser;

public interface WMRMSUserRepository extends JpaRepository<WMRMSUser, Integer>{

}
