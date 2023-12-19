package ApiDemo.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
public class FilesProcessor {
    private FilesProcessor() {
    }

    private static final Path pathDir;
    private static final Path pathDirInSrc;

    static {
        try {
            pathDir = ResourceUtils.getFile("classpath:static/").toPath();
            pathDirInSrc = Paths.get(String.valueOf(pathDir).replace("target\\classes", "src\\main\\resources"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static String saveFileByMultiPart(MultipartFile multipartFile, String dir) throws Exception {
        Files.createDirectories(Paths.get(String.valueOf(pathDir), dir));
        Files.createDirectories(Paths.get(String.valueOf(pathDirInSrc), dir));
        if (multipartFile.isEmpty()) {
            throw new Exception("MultipartFile is empty!");
        }
        String fileName = multipartFile.getOriginalFilename().replaceAll(".*\\.", UUID.randomUUID() + ".");
        Path path = Paths.get(String.valueOf(pathDir), dir, fileName);
        Path pathInSrc = Paths.get(String.valueOf(pathDirInSrc), dir, fileName);
        multipartFile.transferTo(path);
        multipartFile.transferTo(pathInSrc);
        return dir + "/" + fileName;
    }

    public static void deleteFile(String path) throws IOException {
        if (path != null) {
            Files.deleteIfExists(Paths.get(String.valueOf(pathDir), path));
            Files.deleteIfExists(Paths.get(String.valueOf(pathDirInSrc), path));
        }

    }
}
