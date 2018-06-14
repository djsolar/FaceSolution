package solution.twinflag.com.facesolution.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class PersonInfo implements Parcelable{

    private int id;

    private String src;

    private String remark;

    private String description;

    private String title;

    private long timestamp;

    @SerializedName("start_time")
    private long startTime;

    @SerializedName("end_time")
    private long endTime;

    @SerializedName("origin_photo_id")
    private int originPhotoId;

    private String name;

    private String avatar;

    private String department;

    @SerializedName("job_number")
    private String jobNumber;

    @SerializedName("subject_type")
    private int subjectType;

    public PersonInfo() {
    }

    protected PersonInfo(Parcel in) {
        id = in.readInt();
        src = in.readString();
        remark = in.readString();
        description = in.readString();
        title = in.readString();
        timestamp = in.readLong();
        startTime = in.readLong();
        endTime = in.readLong();
        name = in.readString();
        avatar = in.readString();
        department = in.readString();
        jobNumber = in.readString();
        subjectType = in.readInt();
    }


    public static final Creator<PersonInfo> CREATOR = new Creator<PersonInfo>() {
        @Override
        public PersonInfo createFromParcel(Parcel in) {
            return new PersonInfo(in);
        }

        @Override
        public PersonInfo[] newArray(int size) {
            return new PersonInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public int getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(int subjectType) {
        this.subjectType = subjectType;
    }

    public int getOriginPhotoId() {
        return originPhotoId;
    }

    public void setOriginPhotoId(int originPhotoId) {
        this.originPhotoId = originPhotoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(src);
        dest.writeString(remark);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeLong(timestamp);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeInt(originPhotoId);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeString(department);
        dest.writeString(jobNumber);
        dest.writeInt(subjectType);
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id=" + id +
                ", remark='" + remark + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", timestamp=" + timestamp +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", originPhotoId=" + originPhotoId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", department='" + department + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", subjectType=" + subjectType +
                '}';
    }
}
