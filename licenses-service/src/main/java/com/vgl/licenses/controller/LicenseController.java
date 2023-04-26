package com.vgl.licenses.controller;

import com.vgl.licenses.model.License;
import com.vgl.licenses.service.ServiceLicense;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/{organizationId}/licenses")
public class LicenseController {
    private final ServiceLicense serviceLicense;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<License> getLicense(@PathVariable("licenseId") String licenseId,
                                             @PathVariable("organizationId") String organizationId){
        return ResponseEntity.ok(serviceLicense.getLicense(licenseId, organizationId));
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
