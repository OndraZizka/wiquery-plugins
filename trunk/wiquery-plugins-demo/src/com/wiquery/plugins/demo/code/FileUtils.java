package com.wiquery.plugins.demo.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class FileUtils {
	
	static int BUFFER_SIZE = 10*1024;    
    
    /**
     * Returns the location of the 
     * 
     * @param clazz
     * @return
     */
    public static String getBundlePath(Class<?> clazz) {
    	ProtectionDomain pd = clazz.getProtectionDomain();
		if (pd == null)
			return null;
		CodeSource cs = pd.getCodeSource();
		if (cs == null)
			return null;
		URL url = cs.getLocation();
		if (url == null)
			return null;
		String result = url.getFile();
		return result;
    }
    
    /**
     * Copies one stream into the other..
	 * @param is	Stream fuente
	 * @param os	Stream destino*/
	static public void copy(InputStream is, OutputStream os) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			int tam = is.read(buf);
			if (tam == -1) {
				return;
			}
			os.write(buf, 0, tam);
		}
	}
	
	public static  byte[] bytes(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(is, out);
		return out.toByteArray();
	}

}
