package carl.dev.demo.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import carl.dev.demo.domain.FileDB;
import carl.dev.demo.message.ResponseFile;
import carl.dev.demo.message.ResponseMessage;
import carl.dev.demo.service.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/medias")
@Api(value = "Endpoints to manage Files")
public class FileController {
  
  private FileStorageService storageService;

  public FileController(FileStorageService storageService) {
  this.storageService = storageService;
  }

  @ApiOperation(value = "Upload one File")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Uploaded File Successfully: 'File Name'!", response = ResponseFile[].class), 
  @ApiResponse(code = 417, message = "Could Not Upload This File: 'File Name'!", response = ResponseMessage[].class)})
  @PostMapping(path = "/upload-file", consumes = "File/MultipartFile", produces = "application/json")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);
      message = "Uploaded File Successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
    }catch (Exception e) {
      message = "Could Not Upload This File: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @ApiOperation(value = "List all available Files")
  @ApiResponse(code = 200, message = "OK", response = ResponseFile[].class)
  @GetMapping(path = "/all-files", produces = "application/json")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("medias/files/")
          .path(dbFile.getId())
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @ApiOperation(value = "Download a File based on ID")
  @ApiResponse(code = 200, message = "OK/Save FileName.FileExtension? 'dialog'", response = byte[].class)
  @GetMapping(path = "/files/{id}", produces = "file-type/extension-file")
  public ResponseEntity<byte[]> getFileDownload(@PathVariable("id") String id) {
    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }

  @ApiOperation(value = "Delete a File based on ID")
  @ApiResponse(code = 204, message = "No Content/Deleted", response = Void[].class)
  @DeleteMapping(path = "/delete-file/{id}", produces = "application/empty-json")
  public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
    storageService.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  } 
}
