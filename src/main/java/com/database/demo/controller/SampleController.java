package com.database.demo.controller;

import com.database.demo.exception.ResourceNotFoundException;
import com.database.demo.model.Sample;
import com.database.demo.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackageClasses = { JpaBaseConfiguration.class})

public class SampleController {
    @Autowired
    private  SampleRepository sampleRepository;




    @GetMapping("/samples")
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    @GetMapping("/samples/{id}")

    public ResponseEntity<Sample> getSampleById(@PathVariable(value = "id") Long sampleId)
            throws ResourceNotFoundException {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found for this id :: " + sampleId));
        return ResponseEntity.ok().body(sample);
    }

    @PostMapping("/samples")
    public Sample createSample(@Valid @RequestBody Sample sample) {
        return sampleRepository.save(sample);
    }
}
