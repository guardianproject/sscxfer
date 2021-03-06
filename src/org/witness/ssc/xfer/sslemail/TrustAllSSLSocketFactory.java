package org.witness.ssc.xfer.sslemail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TrustAllSSLSocketFactory extends SSLSocketFactory {
	private SSLSocketFactory factory;

	public TrustAllSSLSocketFactory() {
		try {
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null,
					new TrustManager[] { new TrustAllTrustManager() }, null);

			factory = (SSLSocketFactory) sslcontext.getSocketFactory();
		} catch (Exception ex) {
			// ignore
		}
	}

	public static SocketFactory getDefault() {
		return new TrustAllSSLSocketFactory();
	}

	@Override
	public Socket createSocket(Socket socket, String s, int i, boolean flag)
			throws IOException {
		return factory.createSocket(socket, s, i, flag);
	}

	@Override
	public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1,
			int j) throws IOException {
		return factory.createSocket(inaddr, i, inaddr1, j);
	}

	@Override
	public Socket createSocket(InetAddress inaddr, int i) throws IOException {
		return factory.createSocket(inaddr, i);
	}

	@Override
	public Socket createSocket(String s, int i, InetAddress inaddr, int j)
			throws IOException {
		return factory.createSocket(s, i, inaddr, j);
	}

	@Override
	public Socket createSocket(String s, int i) throws IOException {
		return factory.createSocket(s, i);
	}

	@Override
	public Socket createSocket() throws IOException {
		// log.debug( "createSocket 0");
		return factory.createSocket();
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return factory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
	}
}
