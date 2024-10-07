package id.dojo.account.handler;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Request {


    public Request() throws IOException, InterruptedException {
        fetchDataFromBackend("http://localhost:188");
    }

    public void deleteDataInBackend(int idd, String table) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:188/?id=" + idd +"&table=" + table + "&method=DELETE"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


    public String fetchDataFromBackend(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public void saveDataToBackend(Integer id, String web, String note, String pass, LocalDate waktu) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String encodedNote = URLEncoder.encode(note, StandardCharsets.UTF_8);
        String encodedWeb = URLEncoder.encode(web, StandardCharsets.UTF_8);
        String encodedPass = URLEncoder.encode(pass, StandardCharsets.UTF_8);
        String waktu2 = URLEncoder.encode(waktu.toString(), StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:188/?id=" + id + "&web=" + encodedWeb + "&note=" + encodedNote + "&pass=" + encodedPass + "&waktu=" + waktu2 + "&method=PUT"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void addDataToBackend(String web, String note, String pass, String waktu, String un, String name, String relativePath, String Type, boolean fav) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // Tambahkan pengecekan null dan encoding untuk semua parameter
        String encodedNote = (note != null) ? URLEncoder.encode(note, StandardCharsets.UTF_8) : "";
        String encodedUn = (un != null) ? URLEncoder.encode(un, StandardCharsets.UTF_8) : "";
        String encodedWeb = (web != null) ? URLEncoder.encode(web, StandardCharsets.UTF_8) : "";
        String encodedPass = (pass != null) ? URLEncoder.encode(pass, StandardCharsets.UTF_8) : "";
        String encodedName = (name != null) ? URLEncoder.encode(name, StandardCharsets.UTF_8) : "";
        String waktu2 = (waktu != null) ? URLEncoder.encode(waktu, StandardCharsets.UTF_8) : "";
        String rp = (relativePath != null) ? URLEncoder.encode(relativePath, StandardCharsets.UTF_8) : "";
        String encodedType = (Type != null) ? URLEncoder.encode(Type, StandardCharsets.UTF_8) : "";

        String url = "http://localhost:188/";

        String requestBody = "web=" + encodedWeb
                + "&note=" + encodedNote
                + "&password=" + encodedPass
                + "&email=" + encodedUn
                + "&name=" + encodedName
                + "&waktu=" + waktu2
                + "&img=" + rp
                + "&type=" + encodedType
                + "&favorite=" + fav;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void addToFavorite(int id, boolean fav) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:188/?id=" + id + "&favorite=" + fav + "&method=FAVORITE"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
