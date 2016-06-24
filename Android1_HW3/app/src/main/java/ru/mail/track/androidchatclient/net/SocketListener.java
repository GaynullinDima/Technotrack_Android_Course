package ru.mail.track.androidchatclient.net;

public interface SocketListener {
    void onConnected();
    void onConnectionFailed();

    void onDataReceived(String data);
}
