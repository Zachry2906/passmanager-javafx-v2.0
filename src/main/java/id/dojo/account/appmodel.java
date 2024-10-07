package id.dojo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class appmodel {
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String img;
    @JsonProperty
    private String web;
    @JsonProperty
    private String note;
    @JsonProperty
    private int id;
    @JsonProperty
    private String password;
    @JsonProperty
    private String waktu;
    @JsonProperty
    private String favorite;
    private Boolean favBool;

    // Konstruktor default
    public appmodel() {
    }



    // Konstruktor dengan parameter
    public appmodel(int id, String name, String email, String img, String web, String note, String password, String Waktu, String favorite) {
        this.name = name;
        this.email = email;
        this.img = img;
        this.web = web;
        this.note = note;
        this.id = id;
        this.password = password;
        this.waktu = Waktu;
        this.favorite = favorite;
    }

    // Getter dan setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }


    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public Boolean getFavBool() {
        return favBool;
    }

    public void setFavBool(Boolean favBool) {
        this.favBool = favBool;
    }
}