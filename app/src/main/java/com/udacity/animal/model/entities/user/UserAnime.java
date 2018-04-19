package com.udacity.animal.model.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "myinfo")
public class UserAnime extends BaseUser implements Parcelable {

    @Element(name = "user_watching", required = false)
    private Integer watching;
    @Element(name = "user_plantowatch", required = false)
    private Integer planToWatch;

    public UserAnime() {
        super();
    }

    public UserAnime(Parcel in) {
        super(in);
        watching = in.readInt();
        planToWatch = in.readInt();
    }

    public Integer getWatching() {
        return watching;
    }

    public void setWatching(Integer watching) {
        this.watching = watching;
    }

    public Integer getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(Integer planToWatch) {
        this.planToWatch = planToWatch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(watching);
        dest.writeInt(planToWatch);
    }

    public static final Parcelable.Creator<UserAnime> CREATOR = new Parcelable.Creator<UserAnime>() {
        @Override
        public UserAnime createFromParcel(Parcel source) {
            return new UserAnime(source);
        }

        @Override
        public UserAnime[] newArray(int size) {
            return new UserAnime[size];
        }
    };
}
