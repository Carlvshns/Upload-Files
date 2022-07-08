package carl.dev.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "files")
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @ApiModelProperty(notes = "File ID/UUID", example = "bfc2c392-0809-4c67-8231-8137e31458df", required = true)
  private String id;
  @ApiModelProperty(notes = "File Name", example = "Dragon_Ball_Super_Broly.webm")
  private String name;
  @ApiModelProperty(notes = "File Content-Type", example = "video/webm")
  private String type;
  @Lob
  @ApiModelProperty(notes = "File Data")
  private byte[] data;
  public FileDB() {
  }
  public FileDB(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public byte[] getData() {
    return data;
  }
  public void setData(byte[] data) {
    this.data = data;
  }
}
