
package esendex.sdk.http;

import esendex.sdk.service.auth.Authenticator;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Makes, writes to and reads from a URL connection with the
 * HTTP protocol.
 * 
 * @author Mike Whittaker
 */
public class HttpConnectorImpl implements HttpConnector {

	private static final String CONTENT_TYPE = "Content-Type";
	private static final String XML_CONTENT_TYPE = "text/xml";

    static {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException ignored) {
        } catch (KeyManagementException ignored) {
        }

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

	/**
	 * Connect.
	 * @param url the url
	 * @param method the method
	 * @param data the data
	 * @param authenticator the authenticator
	 * @return the http response
	 * @throws HttpException the http exception {@inheritDoc}
	 */
	public HttpResponse connect(URL url,
                                HttpRequestMethod method,
			                    String data,
                                Authenticator authenticator)
			throws HttpException {

		try {
            HttpURLConnection uc = createHttpRequest(url, method, authenticator, data);

            return executeHttpRequest(uc);
			
		} catch (IOException ex) {
			
			throw new HttpException("Failed connection to: " + url, ex);
		}

	}

    private HttpResponse executeHttpRequest(HttpURLConnection uc) throws IOException, HttpException {
        uc.connect();

        int respCode = uc.getResponseCode();
        String respMessage = uc.getResponseMessage();
        if (respCode < 200 || respCode > 299) {
            throw new HttpException("Http response code: "
                    + respCode + " (" + respMessage + ")");
        }

        String response = null;
        if (HttpURLConnection.HTTP_OK == respCode) {

            response = readHttpResponse(uc);
        }

        return new HttpResponse(respCode, respMessage, response);
    }

    private String readHttpResponse(HttpURLConnection uc) throws IOException {

        InputStream inputStream = uc.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        return readBufferToString(br);
    }

    private String readBufferToString(BufferedReader br) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        }
        finally {
            br.close();
        }
        return builder.toString();
    }

    private HttpURLConnection createHttpRequest(URL url,
                                                HttpRequestMethod method,
                                                Authenticator authenticator,
                                                String data)
            throws IOException {
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        authenticator.createHeader(uc);

        uc.setRequestProperty(CONTENT_TYPE, XML_CONTENT_TYPE);
        uc.setRequestMethod(method.toString());
        uc.setRequestProperty("Content-Length", Integer.toString(data == null ? 0 : data.length()));
        uc.setRequestProperty("User-Agent", "esendex-java-sdk/1.0.0 Java/" + System.getProperty("esendex.sdk.version"));

        if ( method != HttpRequestMethod.GET && method != HttpRequestMethod.DELETE && data == null ) {
            data = "";
        }

        if (data != null) {
            uc.setDoOutput(true);
            WriteDataToConnection(data, uc);
        }
        return uc;
    }

    private void WriteDataToConnection(String data, HttpURLConnection uc) throws IOException {
        Writer writer = null;
        try {
            OutputStream outputStream = uc.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            writer = new BufferedWriter(outputStreamWriter);

            writer.write(data);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
