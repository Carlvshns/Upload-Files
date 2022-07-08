package carl.dev.demo.message;

import io.swagger.annotations.ApiModelProperty;

public class ResponseFile {
    @ApiModelProperty(notes = "File Name", example = "Dragon_Ball_Super_Broly.webm")
    private String name;
    @ApiModelProperty(notes = "File URL Download", example = "http://localhost:8080/medias/files/bfc2c392-0809-4c67-8231-8137e31458df")
    private String url;
    @ApiModelProperty(notes = "File Content-Type", example = "video/webm")
    private String type;
    @ApiModelProperty(notes = "File Size", example = "1232434")
    private long size;
    public ResponseFile(String name, String url, String type, long size) {
      this.name = name;
      this.url = url;
      this.type = type;
      this.size = size;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
    public long getSize() {
      return size;
    }
    public void setSize(long size) {
      this.size = size;
    }
  }
