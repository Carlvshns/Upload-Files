package carl.dev.demo.message;

import io.swagger.annotations.ApiModelProperty;

public class ResponseMessage {
    
    @ApiModelProperty(notes = "Message Response", example = "Uploaded File Successfully: 'File Name'!")
    private String message;
    public ResponseMessage(String message) {
      this.message = message;
    }
    public String getMessage() {
      return message;
    }
    public void setMessage(String message) {
      this.message = message;
    }
}
