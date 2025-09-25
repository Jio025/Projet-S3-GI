package ca.usherbrooke.fgen.api.service;

// NOT FOR PROD
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
// NOT FOR PROD
import java.io.*;
import java.net.*;
import java.util.Base64;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DockerfileFetcherService {

    private static final String BASE_URL = "https://gitea.theseus-bob.duckdns.org/api/v1";
    private static final String USER = "theseus";
    static PrivateKeyGitea privateKey = new PrivateKeyGitea();
    private static final String TOKEN = privateKey.getToken();

    // Trust all certs (self-signed workaround)
    public static void trustAllCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        };
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // disable hostname verification
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }

    public String dockerfileFetch(String pathToFile) throws Exception {
        trustAllCertificates();

        String owner = "theseus";
        String repo = "test";

        // Build full API URL
        String apiUrl = String.format("%s/repos/%s/%s/contents/%s?ref=main", BASE_URL, owner, repo, pathToFile);

        // Open HTTPS connection
        URL url = new URL(apiUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Auth header
        String auth = USER + ":" + TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

        // Read response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.toString());

        String base64Content = node.get("content").asText();
        return new String(Base64.getDecoder().decode(base64Content));
    }
}
