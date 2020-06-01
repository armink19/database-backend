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

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")


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
    public Sample createSample(@Valid @RequestPart Sample sample, @RequestPart MultipartFile file) throws IOException {
        Sample s = sampleRepository.save(sample);
        ByteArrayOutputStream resizedimage = fileStorage.resize(file);
        fileStorage.saveThumbnail(resizedimage,s.getId() + ".jpg");


        ByteArrayOutputStream thumb =fileStorage.createThumbnail(file,150);
        fileStorage.saveThumbnail(thumb, s.getId() + "-thumb.jpg");
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


    @GetMapping("/samples/{sampleId}/thumbnail")
    @ResponseBody
    public ResponseEntity<Resource> getThumbnail(@PathVariable String sampleId) {
        String filename = sampleId + "-thumb.jpg";
        Resource file = fileStorage.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(file);
    }
}
