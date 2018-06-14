package solution.twinflag.com.facesolution.domain;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class Face implements Parcelable{

    private String image;

    private Rect rect;

    protected Face(Parcel in) {
        image = in.readString();
        rect = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Creator<Face> CREATOR = new Creator<Face>() {
        @Override
        public Face createFromParcel(Parcel in) {
            return new Face(in);
        }

        @Override
        public Face[] newArray(int size) {
            return new Face[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeParcelable(rect, flags);
    }
}
