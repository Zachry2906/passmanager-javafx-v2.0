package id.dojo.account.handler;

import javafx.scene.control.Alert;

public class Handler {
    public static void AlertInformation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    public static void getCatch(String exceptionType) {
        String errorMessage;
        switch (exceptionType) {
            case "class java.lang.IOException":
                errorMessage = "Terjadi kesalahan saat membaca atau menulis data. Silakan coba lagi.";
                break;
            case "class java.lang.RuntimeException":
                errorMessage = "Terjadi kesalahan saat menjalankan program. Silakan hubungi admin.";
                break;
            case "class java.lang.NullPointerException":
                errorMessage = "Terjadi kesalahan karena data yang diakses tidak ada. Pastikan semua field terisi.";
                break;
            case "class java.lang.NumberFormatException":
                errorMessage = "Format angka tidak valid. Pastikan Anda memasukkan angka yang benar.";
                break;
            case "class java.lang.ClassCastException":
                errorMessage = "Terjadi kesalahan dalam konversi tipe data. Silakan hubungi admin.";
                break;
            case "class java.lang.IndexOutOfBoundsException":
                errorMessage = "Data yang diminta tidak ditemukan dalam database. Pastikan ID yang dimasukkan valid.";
                break;
            default:
                errorMessage = "Terjadi kesalahan yang tidak diketahui. Silakan coba lagi atau hubungi admin.";
        }
    }
}
