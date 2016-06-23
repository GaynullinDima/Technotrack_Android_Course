package ru.mail.track.androidchatclient.net;

import java.util.List;

public interface DataProcessor {
    List<String> process(String data);
}