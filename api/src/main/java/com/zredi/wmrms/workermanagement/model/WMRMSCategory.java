package com.zredi.wmrms.workermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name="wmrms_category")
@Data
public class WMRMSCategory {

    @Id
    private Integer categoryId;
    
    
    @Column(name="category_name")
    private String categoryName;
}
