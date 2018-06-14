package solution.twinflag.com.facesolution.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouyiran on 2018/4/4.
 */

public class VideoMeta implements Parcelable{

    private String path;

    private long duration;

    public VideoMeta(String path, long duration) {
        this.path = path;
        this.duration = duration;
    }

    protected VideoMeta(Parcel in) {
        path = in.readString();
        duration = in.readLong();
    }

    public static final Creator<VideoMeta> CREATOR = new Creator<VideoMeta>() {
        @Override
        public VideoMeta createFromParcel(Parcel in) {
            return new VideoMeta(in);
        }

        @Override
        public VideoMeta[] newArray(int size) {
            return new VideoMeta[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeLong(duration);
    }
}
