package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.udacity.animal.data.entities.Image;

@Entity
public class User implements Parcelable {

    @PrimaryKey
    private Long id;
    private String name;
    private String username;
    private String password;
    @Embedded(prefix = "avatar_")
    private Image avatar;
    @Embedded(prefix = "cover_")
    private Image coverImage;
    private Integer current;
    private Integer planned;
    private Integer completed;
    @ColumnInfo(name = "on_hold")
    private Integer onHold;
    private Integer dropped;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPlanned() {
        return planned;
    }

    public void setPlanned(Integer planned) {
        this.planned = planned;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getOnHold() {
        return onHold;
    }

    public void setOnHold(Integer onHold) {
        this.onHold = onHold;
    }

    public Integer getDropped() {
        return dropped;
    }

    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeParcelable(this.avatar, flags);
        dest.writeParcelable(this.coverImage, flags);
        dest.writeValue(this.current);
        dest.writeValue(this.planned);
        dest.writeValue(this.completed);
        dest.writeValue(this.onHold);
        dest.writeValue(this.dropped);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.avatar = in.readParcelable(Image.class.getClassLoader());
        this.coverImage = in.readParcelable(Image.class.getClassLoader());
        this.current = (Integer) in.readValue(Integer.class.getClassLoader());
        this.planned = (Integer) in.readValue(Integer.class.getClassLoader());
        this.completed = (Integer) in.readValue(Integer.class.getClassLoader());
        this.onHold = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dropped = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
