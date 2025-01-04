
package com.example.productscatelogservice.models;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class SSLByPass {
    public static void disableSSLValidation() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null; // Accept any issuer
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            // Do nothing: bypass client cert checks
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            // Do nothing: bypass server cert checks
                        }
                    }
            };

            // Initialize the SSL context with the above trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Set the default socket factory to the one using the bypassed SSL context
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create an all-trusting hostname verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;

            // Set the default hostname verifier to the one that bypasses checks
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
