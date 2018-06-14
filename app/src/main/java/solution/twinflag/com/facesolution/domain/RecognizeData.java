package solution.twinflag.com.facesolution.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class RecognizeData implements Parcelable{

    private Data data;

    private PersonInfo person;

    private String error;

    @SerializedName("open_door")
    private boolean openDoor;

    private String type;

    protected RecognizeData(Parcel in) {
        person = in.readParcelable(PersonInfo.class.getClassLoader());
        error = in.readString();
        openDoor = in.readByte() != 0;
        type = in.readString();
    }

    public static final Creator<RecognizeData> CREATOR = new Creator<RecognizeData>() {
        @Override
        public RecognizeData createFromParcel(Parcel in) {
            return new RecognizeData(in);
        }

        @Override
        public RecognizeData[] newArray(int size) {
            return new RecognizeData[size];
        }
    };

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public PersonInfo getPerson() {
        return person;
    }

    public void setPerson(PersonInfo person) {
        this.person = person;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isOpenDoor() {
        return openDoor;
    }

    public void setOpenDoor(boolean openDoor) {
        this.openDoor = openDoor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(person, flags);
        dest.writeString(error);
        dest.writeByte((byte) (openDoor ? 1 : 0));
        dest.writeString(type);
    }

    @Override
    public String toString() {
        return "RecognizeData{" +
                "data=" + data +
                ", person=" + person +
                ", error='" + error + '\'' +
                ", openDoor=" + openDoor +
                ", type='" + type + '\'' +
                '}';
    }
}
