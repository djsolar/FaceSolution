package solution.twinflag.com.facesolution.domain;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class Data {

    private String status;

    private long timestamp;

    private int track;

    private Face face;

    private float quality;

    private SimplePerson person;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public SimplePerson getPerson() {
        return person;
    }

    public void setPerson(SimplePerson person) {
        this.person = person;
    }
}
