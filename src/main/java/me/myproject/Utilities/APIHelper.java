package me.myproject.Utilities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIHelper {
    private static final Gson gson = new Gson();

    // POST request và trả về object (thường dùng cho đăng nhập, đăng ký)
    public static <T> T postForObject(String apiUrl, Object requestBody, Class<T> responseType) throws IOException {
        String jsonInput = gson.toJson(requestBody);
        String responseJson = doPost(apiUrl, jsonInput);
        return gson.fromJson(responseJson, responseType);
    }

    // POST request trả về map (nhiều kiểu dữ liệu)
    public static Map<String, Object> postForMap(String apiUrl, Object requestBody) throws IOException {
        String jsonInput = gson.toJson(requestBody);
        String responseJson = doPost(apiUrl, jsonInput);
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseJson, type);
    }

    // GET request trả về danh sách
    public static <T> T fetchList(String apiUrl, Type typeOfT) throws IOException {
        String responseJson = doGet(apiUrl);
        return gson.fromJson(responseJson, typeOfT);
    }

    // ----------------- Internal methods --------------------

    private static String doPost(String apiUrl, String jsonInput) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return readResponse(conn);
    }

    private static String doGet(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return readResponse(conn);
    }

    private static String readResponse(HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        InputStream inputStream = null;
        try {
            if (conn.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conn.getErrorStream();
            } else {
                inputStream = conn.getInputStream();
            }

            if (inputStream == null) {
                return ""; // Hoặc xử lý trường hợp không có nội dung lỗi/thành công
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
                return response.toString();
            }
        } finally {
            // Đảm bảo đóng InputStream nếu nó đã được mở
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Xử lý lỗi đóng stream nếu cần
                    e.printStackTrace();
                }
            }
        }
    }
}