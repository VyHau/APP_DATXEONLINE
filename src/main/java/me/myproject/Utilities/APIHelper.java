package me.myproject.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        // Kiểm tra xem phản hồi có phải là đối tượng JSON không
        if (!responseJson.trim().startsWith("{")) {
            throw new IOException("API trả về phản hồi không phải JSON object: " + responseJson);
        }
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseJson, type);
    }

    // GET request trả về danh sách
    public static <T> T fetchList(String apiUrl, Type typeOfT) throws IOException {
        String responseJson = doGet(apiUrl);
        return gson.fromJson(responseJson, typeOfT);
    }

    // GET request trả về map (nhiều kiểu dữ liệu)
    public static Map<String, Object> getForMap(String apiUrl) throws IOException {
        String responseJson = doGet(apiUrl);
        // Kiểm tra xem phản hồi có phải là đối tượng JSON không
        if (!responseJson.trim().startsWith("{")) {
            throw new IOException("API trả về phản hồi không phải JSON object: " + responseJson);
        }
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseJson, type);
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
    public static Map<String, Object> putForMap(String apiUrl, Object requestBody) throws IOException {
        String jsonInput = gson.toJson(requestBody);
        String responseJson = doPut(apiUrl, jsonInput);
        if (responseJson.trim().isEmpty()) {
            return new HashMap<>();
        }
        if (!responseJson.trim().startsWith("{")) {
            throw new IOException("API trả về phản hồi không phải JSON object: " + responseJson);
        }
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(responseJson, type);
    }
    private static String doPut(String apiUrl, String jsonInput) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        return readResponse(conn);
    }
}
