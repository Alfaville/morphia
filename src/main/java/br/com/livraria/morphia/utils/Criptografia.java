package br.com.livraria.morphia.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	private String pass;
	
	public Criptografia( String pass ){
		this.pass = convertStringToMD5( pass );
	}
	
	private String convertStringToMD5( String pass ) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance( "MD5" );
			byte[] valorMD5 = mDigest.digest( pass.getBytes( "UTF-8" ) );
			StringBuffer sb = new StringBuffer();
			for ( byte b : valorMD5 )
				sb.append( Integer.toHexString( ( b & 0xFF ) | 0x100 ).substring( 1, 3 ) );

			return sb.toString();
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace();
			return null;
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
			return null;
		}

	}

	public String toString(){
		return pass;
	}
	
}