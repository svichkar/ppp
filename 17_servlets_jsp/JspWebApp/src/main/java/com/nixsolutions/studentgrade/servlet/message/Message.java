package com.nixsolutions.studentgrade.servlet.message;

/**
 * Created by svichkar on 2/22/2016.
 */
public class Message {

    private String messageText;
    private MessageType messageType;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}


