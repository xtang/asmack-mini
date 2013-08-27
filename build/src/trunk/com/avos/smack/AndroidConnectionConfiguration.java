package com.avos.smack;

import java.io.File;

import android.os.Build;

/**
 * This class wraps DNS SRV lookups for a new ConnectionConfiguration in a 
 * new thread, since Android API >= 11 (Honeycomb) does not allow network 
 * activity in the main thread. 
 * 
 * @author Florian Schmaus fschmaus@gmail.com
 *
 */
public class AndroidConnectionConfiguration extends ConnectionConfiguration {
    private static final int DEFAULT_TIMEOUT = 10000;
    

    public AndroidConnectionConfiguration(String host, int port, String name) {
	super(host, port, name);
	AndroidInit();
    }

    private void AndroidInit() {
    	// API 14 is Ice Cream Sandwich
	if (Build.VERSION.SDK_INT >= 14) {
	    setTruststoreType("AndroidCAStore");
	    setTruststorePassword(null);
	    setTruststorePath(null);
	} else {
	    setTruststoreType("BKS");
	    String path = System.getProperty("javax.net.ssl.trustStore");
	    if (path == null)
		path = System.getProperty("java.home") + File.separator + "etc"
		    + File.separator + "security" + File.separator
		    + "cacerts.bks";
	    setTruststorePath(path);
	}
    }

}
