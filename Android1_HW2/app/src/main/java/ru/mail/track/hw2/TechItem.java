package ru.mail.track.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class TechItem implements Parcelable {
    public int id;
    public String picture;
    public String title;
    public String info;

    public TechItem() {}

    public String getPictureUrl() {
        return picture != null ? Api.BASE_URL + picture : null;
    }

    public TechItem(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<TechItem> CREATOR = new Parcelable.Creator<TechItem>() {
        public TechItem createFromParcel(Parcel in) {
            return new TechItem(in);
        }

        public TechItem[] newArray(int size) {

            return new TechItem[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.picture);
        dest.writeString(this.title);
        dest.writeString(this.info);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.picture = in.readString();
        this.title = in.readString();
        this.info = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechItem that = (TechItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TechItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
