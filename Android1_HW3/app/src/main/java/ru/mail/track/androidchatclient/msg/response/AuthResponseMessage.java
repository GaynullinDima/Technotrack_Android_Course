package ru.mail.track.androidchatclient.msg.response;

import android.os.Parcel;

import ru.mail.track.androidchatclient.msg.Action;
import ru.mail.track.androidchatclient.msg.BaseMessage;
import ru.mail.track.androidchatclient.msg.Status;


/*{
    "action":"auth",
    "data":{
        "status":[0-9]+,
        "error":"TEXT_OF_ERROR",
        "sid":"SESSION_ID",
        "cid":"USER_ID"
    }
}*/
public class AuthResponseMessage implements BaseMessage {
    public Status status;
    public String error;
    public String sid;  // session id
    public String cid;  // user id

    public AuthResponseMessage() {
    }

    public AuthResponseMessage(int status, String error, String sid, String cid) {
        this.status = Status.values()[status];
        this.error = error;
        this.sid = sid;
        this.cid = cid;
    }

    protected AuthResponseMessage(Parcel in) {
        status = Status.values()[in.readInt()];
        error = in.readString();
        sid = in.readString();
        cid = in.readString();
    }

    public static final Creator<AuthResponseMessage> CREATOR = new Creator<AuthResponseMessage>() {
        @Override
        public AuthResponseMessage createFromParcel(Parcel in) {
            return new AuthResponseMessage(in);
        }

        @Override
        public AuthResponseMessage[] newArray(int size) {
            return new AuthResponseMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(status.ordinal());
        out.writeString(error);
        out.writeString(sid);
        out.writeString(cid);
    }

    @Override
    public String getAction() {
        return Action.AUTH;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
