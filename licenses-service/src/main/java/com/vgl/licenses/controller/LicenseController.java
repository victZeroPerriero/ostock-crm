package com.vgl.licenses.controller;

import com.vgl.licenses.model.License;
import com.vgl.licenses.service.ServiceLicense;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/{organizationId}/licenses")
public class LicenseController {
    private final ServiceLicense serviceLicense;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<License> getLicense(@PathVariable("licenseId") String licenseId,
                                             @PathVariable("organizationId") String organizationId){
       License fromDb = serviceLicense.getLicense(licenseId, organizationId);
       fromDb.add(
               linkTo(methodOn(LicenseController.class)
                       .getLicense(licenseId,organizationId))
                       .withSelfRel(),
               linkTo(methodOn(LicenseController.class)
                       .create(fromDb,organizationId,null))
                       .withSelfRel(),
               linkTo(methodOn(LicenseController.class)
                       .update(fromDb,organizationId))
                       .withSelfRel(),
               linkTo(methodOn(LicenseController.class)
                       .deleteLicense(licenseId,organizationId))
                       .withSelfRel());
        return ResponseEntity.ok(fromDb);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody License license,
                                         @PathVariable("organizationId") String organizationId,
                                         @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(serviceLicense.create(license,organizationId, locale));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody License license,
                                         @PathVariable("organizationId") String organizationId){
        return ResponseEntity.ok(serviceLicense.update(license, organizationId));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId,
                                                @PathVariable("organizationId") String organizationId){
        return ResponseEntity.ok(serviceLicense.deleteLicense(licenseId, organizationId));
    }

}
