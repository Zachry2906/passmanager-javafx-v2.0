package id.dojo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.dojo.account.appmodel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String status;
    private String message;
    private List<appmodel> data;
    private int size;

    // Konstruktor
    public Response() {}

    // Getter dan setter
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<appmodel> getData() { return data; }
    public void setData(List<appmodel> data) { this.data = data; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}