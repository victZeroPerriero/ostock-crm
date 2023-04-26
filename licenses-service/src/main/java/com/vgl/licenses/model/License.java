package com.vgl.licenses.model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class License {
    private Integer id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
