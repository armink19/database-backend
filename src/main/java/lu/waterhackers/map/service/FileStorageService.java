package lu.waterhackers.map.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;



public interface FileStorageService {
    public void init();

    public void save(MultipartFile file);

    public void save(ByteArrayOutputStream file);

    public Resource load(String filename);

    public void deleteAll();

    public ByteArrayOutputStream createThumbnail(MultipartFile orginalFile, Integer width) throws IOException;

    public Stream<Path> loadAll();
}
