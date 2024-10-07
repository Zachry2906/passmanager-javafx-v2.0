package id.dojo.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.dojo.account.handler.Handler;
import id.dojo.account.handler.Request;
import id.dojo.account.handler.VigenereCipher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private Button card;

    @FXML
    private Button save;

    @FXML
    private Button edit;

    @FXML
    private ImageView closeb;

    @FXML
    private ImageView editb;

    @FXML
    private Button favorites;

    @FXML
    private Button folder;

    @FXML
    private Button items;

    @FXML
    private ImageView logo;

    @FXML
    private Label note;

    @FXML
    private Label imgpath;

    @FXML
    private PasswordField passwordtf;

    @FXML
    private Button recover;

    @FXML
    private Button fav;

    @FXML
    private Pane panetrash;

    @FXML
    private ImageView logotrash;

    @FXML
    private ImageView favimg;

    @FXML
    private Label waktutrash;

    @FXML
    private Label textlogotrash;

    @FXML
    private Button deletepermanent;

    @FXML
    private Label websitetrash;

    @FXML
    private Label trashpassword;

    @FXML
    private Label trashusername;

    @FXML
    private TextField search;

    @FXML
    private Button security;

    @FXML
    private Label textlogo;

    @FXML
    private Label notetrash;


    @FXML
    private Label waktu;

    @FXML
    private Button trash;

    @FXML
    private Button cancle;

    @FXML
    private Button showpass;

    @FXML
    private Button delete;

    @FXML
    private TextField usernametf;

    @FXML
    private TextField passwordshow;


    @FXML
    private VBox vitems;

    @FXML
    private Label website;

    @FXML
    private Label id;

    @FXML
    private Pane panedetail;

    @FXML
    private Pane paneadd;

    @FXML
    private Button addaccount;

    @FXML
    private ImageView addimage;

    @FXML
    private TextField addnama;

    @FXML
    private TextField addnotes;

    @FXML
    private TextField addpassword;

    @FXML
    private TextField addusername;

    @FXML
    private TextField addwebsite;

    public NewController() throws IOException, InterruptedException {
    }

    @FXML
    void onClick(ActionEvent event) {

    }
    @FXML private TextField websiteField;
    @FXML private TextField notesField;

    private ObservableList<appmodel> allApps = FXCollections.observableArrayList();
    private FilteredList<appmodel> filteredApps;
    private boolean isshow = false;
    private String relativePath;
    Request req = new Request();
    String tempPath;
    int idSampah;
    String item = "data";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            panetrash.setVisible(false);
            paneadd.setVisible(false);
            panedetail.setVisible(true);
            websiteField.setVisible(false);
            notesField.setVisible(false);
            save.setVisible(false);
            delete.setVisible(false);
            imgpath.setVisible(false);

            edit.setOnAction(event -> toggleEditMode());
            trash.setOnAction(event -> {
                showTrash();
                System.out.println("Setelah showTrash(), panedetail visibility: " + panedetail.isVisible());
            });

            addimage.setOnMouseClicked(event -> setImage());
            delete.setOnAction(event -> {
                try {
                    addTrash();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            deletepermanent.setOnAction(event -> {
                try {
                    req.deleteDataInBackend(idSampah, "sampah");
                    fetchDataAndUpdateUI("http://localhost:188/sampah/");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            fav.setOnAction(event -> {
                try {
                    // Dapatkan URL dari gambar yang sedang ditampilkan
                    String currentImageUrl = favimg.getImage().getUrl();

                    // URL untuk gambar bintang berukuran 32 dan 48
                    String star32Url = String.valueOf(HelloApplication.class.getResource("icons/icons8-star-32.png"));
                    String star48Url = String.valueOf(HelloApplication.class.getResource("icons/icons8-star-48.png"));

                    // Bandingkan URL dan ubah gambar
                    if (currentImageUrl.equals(star32Url)) {
                        favimg.setImage(new Image(star48Url));
                        addToFavorite(true);
                    } else {
                        favimg.setImage(new Image(star32Url));
                        addToFavorite(false);
                        fetchDataAndUpdateUI("http://localhost:188/?fav=betul");
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });


            save.setOnAction(event -> {
                try {
                    saveChanges();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            recover.setOnAction(event -> {
                try {
                    recoverData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            showpass.setOnAction(event -> showPassword());
            add.setOnAction(event -> showAdd());
            items.setOnAction(event -> showItems());
            addaccount.setOnAction(event -> {
                try {
                    addAccount();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            favorites.setOnAction(event -> {
                setAllFalse();
                item = "data";
                System.out.println("Setelah showTrash(), panedetail visibility: " + panedetail.isVisible());
                fetchDataAndUpdateUI("http://localhost:188/?fav=betul");
            });

            cancle.setOnAction(event -> cancle());
            trash.setOnAction(event -> {
                item = "sampah";
                showTrash();
                System.out.println("Setelah showTrash(), panedetail visibility: " + panedetail.isVisible());
                    fetchDataAndUpdateUI("http://localhost:188/sampah/");
                    });
            fetchDataAndUpdateUI("http://localhost:188");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUI() {
        vitems.getChildren().clear();
        for (int i = 0; i < filteredApps.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(HelloApplication.class.getResource("component.fxml"));
                Node node = loader.load();
                final int h = i;

                ItemController controller = loader.getController();
                appmodel app = filteredApps.get(i);

                controller.setItemInfo(app.getName(), app.getEmail(), app.getImg());
                node.setOnMouseEntered(event -> node.setStyle("-fx-background-color:  #303250"));
                node.setOnMouseExited(event -> node.setStyle("-fx-background-color:   #101010FF"));
                node.setOnMousePressed(event -> {
                    System.out.println(item);
                    node.setStyle("-fx-background-color:   #101010FF");
                    if ((item.equals("data"))){
                        panedetail.setVisible(true);
                        item = "data";
                        website.setText(app.getWeb());
                        passwordtf.setText(VigenereCipher.decrypt(app.getPassword(), "ZACHRY"));
                        passwordshow.setText(VigenereCipher.decrypt(app.getPassword(), "ZACHRY"));
                        id.setText(String.valueOf(app.getId()));
                        note.setText(app.getNote());
                        logo.setImage(new Image(String.valueOf(HelloApplication.class.getResource(app.getImg()))));
                        usernametf.setText(app.getEmail());
                        textlogo.setText(app.getName());
                        websiteField.setText(app.getWeb());
                        notesField.setText(app.getNote());
                        waktu.setText(app.getWaktu());
                        imgpath.setText(app.getImg());
                        System.out.println(app.getFavorite());
                        if (app.getFavorite().equals("t")){
                            favimg.setImage(new Image(String.valueOf(HelloApplication.class.getResource("icons/icons8-star-48.png"))));
                        } else {
                            favimg.setImage(new Image(String.valueOf(HelloApplication.class.getResource("icons/icons8-star-32.png"))));
                        }
                    } else {
                        panetrash.setVisible(true);
                        waktutrash.setText(app.getWaktu());
                        trashpassword.setText(VigenereCipher.decrypt(app.getPassword(), "ZACHRY"));
                        trashusername.setText((app.getEmail()));
                        logotrash.setImage(new Image(String.valueOf(HelloApplication.class.getResource(app.getImg()))));
                        textlogotrash.setText(app.getName());
                        websitetrash.setText(app.getWeb());
                        notetrash.setText(app.getNote());
                        tempPath = app.getImg();
                        idSampah = app.getId();
                    }
                });
                vitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void toggleEditMode() {
            // Aktifkan mode edit

            website.setVisible(false);
            note.setVisible(false);

            websiteField.setVisible(true);
            notesField.setVisible(true);

            save.setVisible(true);
            delete.setVisible(true);

    }

    private void setAllFalse(){
        panedetail.setVisible(false);
        paneadd.setVisible(false);
        panetrash.setVisible(false);
    }

    private void cancle(){
        website.setVisible(true);
        note.setVisible(true);

        websiteField.setVisible(false);
        notesField.setVisible(false);

        save.setVisible(false);
        delete.setVisible(false);
    }

    private void saveChanges() throws IOException, InterruptedException {
        // Simpan perubaham
        String web = websiteField.getText();
        String note = notesField.getText();
        String pass;
        if (passwordtf.getText() == null && showpass.getText() != null) {
            pass = showpass.getText();
        } else {
            pass = passwordtf.getText();
        }
        LocalDate waktu = LocalDate.now();
        String ency = VigenereCipher.encrypt(pass, "ZACHRY");
        Integer idd = Integer.valueOf(id.getText());

        req.saveDataToBackend(idd, web, note, ency, waktu);

        // Kembali ke mode tampilan
        toggleEditMode();

//         Di sini Anda bisa menambahkan logika untuk menyimpan perubahan ke database atau sumber data lainnya
    }

    private void showPassword(){
        if (isshow == false){
            isshow = true;
            passwordtf.setVisible(false);
            passwordshow.setVisible(true);
        } else {
            isshow = false;
            passwordtf.setVisible(true);
            passwordshow.setVisible(false);
        }
    }

    private void showAdd(){
            panedetail.setVisible(false);
            panetrash.setVisible(false);
            paneadd.setVisible(true);
    }

    private void showTrash() {
        System.out.println("showTrash() dipanggil");
        setAllFalse();
        item = "sampah";
        System.out.println("panedetail visibility: " + panedetail.isVisible());
    }

    private void showItems(){
        fetchDataAndUpdateUI("http://localhost:188");
        setAllFalse();
        item = "data";
    }

    private void addAccount() throws IOException, InterruptedException {
        String web = addwebsite.getText();
        String note = addnotes.getText();
        String name = addnama.getText();
        String password = addpassword.getText();
        String username = addusername.getText();
        LocalDate waktu = LocalDate.now();
        String ency = VigenereCipher.encrypt(password, "ZACHRY");

        req.addDataToBackend(web, note, ency, waktu.toString(), username, name, relativePath, "add", false);
        fetchDataAndUpdateUI("http://localhost:188");
    }

    private void addTrash() throws IOException, InterruptedException {
        int idd = Integer.valueOf(id.getText());

        String web = websiteField.getText();
        String note = notesField.getText();
        String name = textlogo.getText();
        String password = passwordtf.getText();
        String username = usernametf.getText();
        LocalDate waktu = LocalDate.now();
        String ency = VigenereCipher.encrypt(password, "ZACHRY");

        req.deleteDataInBackend(idd, "data");
        req.addDataToBackend(web, note, ency, waktu.toString(), username, name, imgpath.getText(), "delete", false);
        fetchDataAndUpdateUI("http://localhost:188");
        panedetail.setVisible(false);
    }

    public void setImage() {
        // Membuat FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Filter untuk hanya menampilkan file gambar
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Membuka dialog pemilihan file
        File selectedFile = fileChooser.showOpenDialog(null);
        String fileName = selectedFile.getName();
        relativePath = "icons/" + fileName;
        System.out.println(relativePath);
        if (selectedFile != null) {
            // Menentukan direktori tujuan
            Path destinationDirectory = Path.of("/home/zachry/Documents/Ngoding/Java/account/src/main/resources/icons"); // Ubah ini ke path tujuan Anda
            // Membuat direktori tujuan jika belum ada
            try {
                Files.createDirectories(destinationDirectory);

                // Menentukan path file tujuan
                Path destinationPath = destinationDirectory.resolve(selectedFile.getName());

                // Menyalin file ke direktori tujuan
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File saved to: " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save file.");
            }
        } else {
            System.out.println("File selection was cancelled.");
        }
    }

    private void fetchDataAndUpdateUI(String url) {
        try {
            String jsonResponse = req.fetchDataFromBackend(url);
            ObjectMapper mapper = new ObjectMapper();

            try {
                Response response = mapper.readValue(jsonResponse, Response.class);
                if ("success".equals(response.getStatus())) {
                    allApps.clear(); // Bersihkan data sebelumnya
                    allApps.addAll(response.getData()); // Tambahkan data baru
                } else {
                    Handler.AlertInformation("Informasi", "Gagal Mengambil Json: " + response.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Handler.AlertInformation("Error", "Gagal memproses data: " + e.getMessage());
            }

            filteredApps = new FilteredList<>(allApps, p -> true);
            updateUI();

            // Setup listener untuk TextField search
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredApps.setPredicate(app -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return app.getName().toLowerCase().contains(lowerCaseFilter);
                });
                updateUI();
            });
        } catch (IOException | InterruptedException e) {
            Handler.getCatch(String.valueOf(e.getClass()));
            System.out.println(e.getClass());
        }
    }


    private void addToFavorite(boolean fav) throws IOException, InterruptedException {
        int idd = Integer.valueOf(id.getText());
        req.addToFavorite(idd, fav);
    }

    private void recoverData() throws IOException, InterruptedException {
        String password = trashpassword.getText();
        String username =  trashusername.getText();
        String path = tempPath;
        String name = textlogotrash.getText();
        String web = websitetrash.getText();
        String note = notetrash.getText();
        LocalDate sekarang = LocalDate.now();
        String ency = VigenereCipher.encrypt(password, "ZACHRY");

        req.deleteDataInBackend(idSampah, "trash");
        req.addDataToBackend(web, note, ency, sekarang.toString(), username, name, path, "add", false);
        fetchDataAndUpdateUI("http://localhost:188/?isi=sampah");
    }


}

