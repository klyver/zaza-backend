package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ErrorMessage {
    @ApiModelProperty(required = true)
    private String message;
    @ApiModelProperty(required = true)
    private String messageKey;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, String messageKey) {
        this.message = message;
        this.messageKey = messageKey;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
