package carl.dev.demo.service;

import java.io.IOException;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import carl.dev.demo.domain.FileDB;
import carl.dev.demo.repository.FileDBRepository;

@Service
public class FileStorageService {
  @Autowired
  private FileDBRepository fileDBRepository;

  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
    return fileDBRepository.save(FileDB);
  }
  
  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  public Stream<FileDB> getAllFiles(Pageable pageable) {
    return fileDBRepository.findAll(pageable).stream();
  }

  public void deleteById(String id) {
    fileDBRepository.deleteById(id);
  } 

}
