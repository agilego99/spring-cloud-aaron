package com.aaron.mqtt.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
 import java.nio.file.Files;
 import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
 import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
 import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.KeyManagerFactory;
 import javax.net.ssl.SSLContext;
 import javax.net.ssl.SSLSocketFactory;
 import javax.net.ssl.TrustManagerFactory;
 
 import org.bouncycastle.jce.provider.BouncyCastleProvider;
 import org.bouncycastle.openssl.*;
import org.bouncycastle.util.encoders.Base64;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
 public class SslUtil {
     public static SSLSocketFactory getSocketFactory(final String caCrtFile, final String crtFile, final String keyFile,
             final String password) throws Exception {
         Security.addProvider(new BouncyCastleProvider());
 
         // load CA certificate
         PEMReader reader = new PEMReader(
                 new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))));
         X509Certificate caCert = (X509Certificate) reader.readObject();
         reader.close();
 
         // load client certificate
         reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(crtFile)))));
         X509Certificate cert = (X509Certificate) reader.readObject();
         reader.close();
 
         // load client private key
         reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(keyFile)))),
                 new PasswordFinder() {
                     @Override
                     public char[] getPassword() {
                         return password.toCharArray();
                     }
                 });
         KeyPair key = (KeyPair) reader.readObject();
         reader.close();
         
         // CA certificate is used to authenticate server
         KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
         caKs.load(null, null);
         caKs.setCertificateEntry("ca-certificate", caCert);
         TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
         tmf.init(caKs);
 
         // client key and certificates are sent to server so it can authenticate us
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         ks.load(null, null);
         ks.setCertificateEntry("certificate", cert);
         

         
         
     	if (key != null) {	
			ks.setKeyEntry("private-key", key.getPrivate(), password.toCharArray(),
					new java.security.cert.Certificate[] { cert });
		} else {
	        PrivateKey privateKey = generateKeyFromPKCS8(keyFile);
			ks.setKeyEntry("private-key", privateKey, password.toCharArray(),
					new java.security.cert.Certificate[] { cert });
		}
     	
         KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
         kmf.init(ks, password.toCharArray());
 
         // finally, create SSL socket factory
         SSLContext context = SSLContext.getInstance("TLS");
         context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
 
         return context.getSocketFactory();
     }
     
     
     private static PrivateKey generateKeyFromPKCS8(String keyFile) throws Exception {
 		StringBuffer sb = new StringBuffer();
 		FileReader r = new FileReader(keyFile);
 		BufferedReader br = null;
 		try {
 			br = new BufferedReader(r);
 			for (String line; (line = br.readLine()) != null;) {
 				sb.append(line);
 			}
 		} catch (Exception ex) {
 		} finally {
 			if (br != null) {
 				br.close();
 			}
 		}
 		String unencrypted = sb.toString().replace("-----BEGIN PRIVATE KEY-----", "")
 				.replace("-----END PRIVATE KEY-----", "");
 		byte[] encoded = Base64.decode(unencrypted);
 		PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(encoded);
 		KeyFactory kf = KeyFactory.getInstance("RSA");
 		PrivateKey unencryptedPrivateKey = kf.generatePrivate(kspec);
 		return unencryptedPrivateKey;
 	}
 }