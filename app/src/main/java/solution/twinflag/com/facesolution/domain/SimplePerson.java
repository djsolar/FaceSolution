package solution.twinflag.com.facesolution.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class SimplePerson implements Parcelable{

    @SerializedName("feature_id")
    private int featureId;

    private float confidence;

    private String tag;

    private String id;


    protected SimplePerson(Parcel in) {
        featureId = in.readInt();
        confidence = in.readFloat();
        tag = in.readString();
        id = in.readString();
    }

    public static final Creator<SimplePerson> CREATOR = new Creator<SimplePerson>() {
        @Override
        public SimplePerson createFromParcel(Parcel in) {
            return new SimplePerson(in);
        }

        @Override
        public SimplePerson[] newArray(int size) {
            return new SimplePerson[size];
        }
    };

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(featureId);
        dest.writeFloat(confidence);
        dest.writeString(tag);
        dest.writeString(id);
    }
}
