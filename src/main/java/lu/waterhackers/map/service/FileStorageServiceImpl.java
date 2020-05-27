package lu.waterhackers.map.service;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${photo_upload_location:uploads}")
    private String photo_upload_location;

    private Path getRoot() {
        try {
            return Files.createDirectories(Paths.get(this.photo_upload_location));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(MultipartFile file, String filename) {
        try {
            Files.copy(file.getInputStream(), this.getRoot().resolve(filename));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void saveThumbnail(ByteArrayOutputStream file,String filename) throws IOException {
        Files.createFile(this.getRoot().resolve(filename));
         Files.write(this.getRoot().resolve(filename),file.toByteArray());
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = this.getRoot().resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayOutputStream resize(MultipartFile file) throws IOException {
        ByteArrayOutputStream resizedOutput = new ByteArrayOutputStream();
        BufferedImage resizedImg = null;
        BufferedImage img = ImageIO.read(file.getInputStream());
        if(img.getWidth()<1000){
            resizedImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 1000, Scalr.OP_ANTIALIAS);
            ImageIO.write(resizedImg, file.getContentType().split("/")[1] , resizedOutput);
            return resizedOutput;
        }
        if(img.getWidth()>2000){
        resizedImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 2000, Scalr.OP_ANTIALIAS);
        ImageIO.write(resizedImg, file.getContentType().split("/")[1] , resizedOutput);}
        else {
            ImageIO.write(img, file.getContentType().split("/")[1] , resizedOutput);

        }
        return resizedOutput;
    }


    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.getRoot().toFile());
    }

    @Override
    public ByteArrayOutputStream createThumbnail(MultipartFile orginalFile, Integer width) throws IOException{
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
        BufferedImage img = ImageIO.read(orginalFile.getInputStream());
        thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1] , thumbOutput);
        return thumbOutput;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.getRoot(), 1).filter(path -> !path.equals(this.getRoot())).map(this.getRoot()::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
