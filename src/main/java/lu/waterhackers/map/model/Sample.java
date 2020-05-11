package lu.waterhackers.map.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "samples")
public class Sample {

    private long id;
    private String picture;
    private Date date;
    private Date time;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;



    private String watersource;
    private Double temperature;
    private Double ph;
    private Double dissolvedoxygen;
    private Double turbidity;


    Sample() {
    }

    public Sample(String picture, Date date, Date time, String description, String address, String watersource, Double temperature, Double ph, Double dissolvedoxygen, Double turbidity) {
        this.picture = picture;
        this.date = date;
        this.time = time;
        this.description = description;
        this.address = address;
        this.watersource = watersource;
        this.temperature = temperature;
        this.ph = ph;
        this.dissolvedoxygen = dissolvedoxygen;
        this.turbidity = turbidity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "picture", nullable = false)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name= "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    @Column(name= "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    @Column(name = "watersource", nullable = false)
    public String getWatersource() {
        return watersource;
    }

    public void setWatersource(String watersource) {
        this.watersource = watersource;
    }

    @Column(name = "temperature", nullable = false)
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Column(name = "ph", nullable = false)
    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    @Column(name = "dissolvedoxygen", nullable = false)
    public Double getDissolvedoxygen() {
        return dissolvedoxygen;
    }

    public void setDissolvedoxygen(Double dissolvedoxygen) {
        this.dissolvedoxygen = dissolvedoxygen;
    }

    @Column(name = "turbidity", nullable = false)
    public Double getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(Double turbidity) {
        this.turbidity = turbidity;
    }

    @Override
    public String toString() {
        return "Sample [id=" + id + ", date=" + date + ", time=" + time + ", descriptiom=" + description + ", " +
                "address=" + address + ", watersource=" + watersource + ", temperature=" + temperature
                + ", ph=" + ph + ", dissolvedoxygen=" + dissolvedoxygen + ", turbidity=" + turbidity +
                 "]";
    }
}
