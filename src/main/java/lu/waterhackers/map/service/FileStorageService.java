package lu.waterhackers.map.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;



public interface FileStorageService {
    public void save(MultipartFile file, String filename);

    public void saveThumbnail(ByteArrayOutputStream file, String filename) throws IOException;

    public Resource load(String filename);

    public  ByteArrayOutputStream resize(MultipartFile file) throws IOException;

    public void deleteAll();

    public ByteArrayOutputStream createThumbnail(MultipartFile orginalFile, Integer width) throws IOException;

    public Stream<Path> loadAll();
}
