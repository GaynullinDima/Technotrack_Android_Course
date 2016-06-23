package ru.mail.track.androidchatclient.msg;

import android.os.Parcelable;

public interface BaseMessage extends Parcelable {
    String getAction();

    Status getStatus();
}
