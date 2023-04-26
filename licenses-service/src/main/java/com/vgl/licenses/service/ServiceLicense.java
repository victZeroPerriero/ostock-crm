package com.vgl.licenses.service;

import com.vgl.licenses.model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceLicense {
    private final MessageSource messageSource;
    public License getLicense(String licenseId, String organizationId){
       return License.builder()
               .id(UUID.randomUUID().variant())
               .licenseId(licenseId)
               .organizationId(organizationId)
               .description("Software product")
               .productName("O-stock")
               .licenseType("full")
               .build();
    }
    public String create(License license, String organizationId, Locale locale){
        String responseMessage=null;
        if(license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.create.message",
                    null, locale), license.toString());
        }
        return responseMessage;
    }
    public String update(License license, String organizationId){
        String responseMessage = null;
        if(license!= null){
            license.setOrganizationId(organizationId);
            responseMessage =String.format(messageSource.getMessage("license.update.message",
                    null, null), license.toString());
        }
        return responseMessage;
    }
    public String deleteLicense(String licenseId, String organizationId){
        return String.format("Deleting license with id %s for organization %s", licenseId, organizationId);
    }
}
