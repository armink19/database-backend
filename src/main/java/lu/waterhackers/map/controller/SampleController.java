package lu.waterhackers.map.controller;

import lu.waterhackers.map.exception.ResourceNotFoundException;
import lu.waterhackers.map.model.Sample;
import lu.waterhackers.map.repository.SampleRepository;
import lu.waterhackers.map.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})


public class SampleController {
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private FileStorageService fileStorage;

    @GetMapping("/samples")
    public List<Sample> getAllSamples()
    {
        return sampleRepository.findAll();
    }

    @GetMapping("/samples/{id}")

    public ResponseEntity<Sample> getSampleById(@PathVariable(value = "id") Long sampleId)
            throws ResourceNotFoundException {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found for this id :: " + sampleId));
        return ResponseEntity.ok().body(sample);
    }

    @PostMapping(value="/samples", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Sample createSample(@Valid @RequestPart Sample sample, @RequestPart MultipartFile file) {
        Sample s = sampleRepository.save(sample);
        // TODO resize to reasonable size before saving
        fileStorage.save(file, s.getId() + ".jpg");
        // TODO save again as thumbnail (eg. 150px width) under name: s.getId() + "-thumb.jpg"
        return s;
    }

    @GetMapping("/samples/{sampleId}/photo")
    @ResponseBody
    public ResponseEntity<Resource> getPhoto(@PathVariable String sampleId) {
        String filename = sampleId + ".jpg";
        Resource file = fileStorage.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(file);
    }

    // TODO add another method for getting thumbnail

}
