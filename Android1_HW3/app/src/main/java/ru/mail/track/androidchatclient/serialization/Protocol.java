package ru.mail.track.androidchatclient.serialization;

import java.net.ProtocolException;

import ru.mail.track.androidchatclient.msg.BaseMessage;

public interface Protocol {

    String encode(BaseMessage msg) throws ProtocolException;

    BaseMessage decode(String data) throws ProtocolException;
}
