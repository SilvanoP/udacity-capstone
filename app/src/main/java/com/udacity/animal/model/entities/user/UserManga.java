package com.udacity.animal.model.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "myinfo")
public class UserManga extends BaseUser implements Parcelable {

    @Element(name = "user_reading", required = false)
    private Integer reading;
    @Element(name = "user_plantoread", required = false)
    private Integer planToRead;

    public UserManga() {
        super();
    }

    public UserManga(Parcel in) {
        super(in);
        reading = in.readInt();
        planToRead = in.readInt();
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    public Integer getPlanToRead() {
        return planToRead;
    }

    public void setPlanToRead(Integer planToRead) {
        this.planToRead = planToRead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(reading);
        dest.writeInt(planToRead);
    }

    public static final Parcelable.Creator<UserManga> CREATOR = new Parcelable.Creator<UserManga>(){
        @Override
        public UserManga createFromParcel(Parcel source) {
            return new UserManga(source);
        }

        @Override
        public UserManga[] newArray(int size) {
            return new UserManga[size];
        }
    };
}
