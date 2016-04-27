package ru.mail.track.k33p.androidcourse1_homework2;


import android.database.Cursor;

public class Item {
    public int id;
    public String picture;
    public String title;
    public String info;

    public Item() {}

    public Item(Cursor cursor) {
        id = cursor.getInt(DbHelper.INDEX_ID);
        picture = cursor.getString(DbHelper.INDEX_PICTURE);
        title = cursor.getString(DbHelper.INDEX_TITLE);
        info = cursor.getString(DbHelper.INDEX_INFO);
    }

    public String getPictureUrl() {
        return picture != null ? R.string.BASE_URL + picture : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
