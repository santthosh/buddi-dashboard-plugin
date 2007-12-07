/**
 * 
 */
package org.homeunix.thecave.plugins;
import java.util.*;

import org.homeunix.thecave.buddi.model.prefs.PrefsModel;
import org.homeunix.thecave.buddi.plugin.api.exception.PluginException;
import org.homeunix.thecave.buddi.util.BuddiCryptoFactory;
/**
 * @author Santthosh
 *
 */
public class PreferencesHandler {

	
}
/*A Buddi plugin which will be loaded into the Preferences screen. If your plugin needs user configuration, it is highly recommended to use this class to perform that. It is quite simple to do this; you need to create a JPanel of the component layout in getPreferencesPanel(), and provide the logic to load and save these values in the load() and save() methods.
See the documentation for BuddiPluginPreference to see how to access the preferences file.
org.homeunix.thecave.buddi.plugin.api.BuddiPreferencePlugin
http://devel.buddi.thecave.homeunix.org/javadocs/
All that I need to implement is 

java.util.List<java.lang.String> 	getListPreference(java.lang.String key)
          Returns the list associated with the given key.     LIST
 java.lang.String 	getPreference(java.lang.String key)
          Loads the value which is associated with the given key.
 java.lang.String 	getSecurePreference(java.lang.String key, char[] password)
          Loads the value which is associated with the given key, after decrypting the value using the given password.
 void 	putListPreference(java.lang.String key, java.util.List<java.lang.String> values)
          Sets the given list of strings in the preferences map, using the given key.
 void 	putPreference(java.lang.String key, java.lang.String value)
          Stores the value associated with a given key.
 void 	putSecurePreference(java.lang.String key, java.lang.String value, char[] password)
          Stores the value, encrypted using the given password, into the preferences file using the given key.
 


abstract  void 	load()
          Loads the preferences which this panel is responsible for.
abstract  boolean 	save()
          Saves the preferences which this panel is responsible for.
 

*/

/* To extract key value from text file

import java.util.*;
import java.io.*;

public class Token {

	public static void main(String args[])
	{
		StringTokenizer st;
		String key=null;
		String value=null;
		Hashtable ht = new Hashtable();
		String line;
		try {
		  BufferedReader br = new BufferedReader(new FileReader(new File("buddi.txt")));
		  while ((line = br.readLine()) != null) 
		  {
		    st = new StringTokenizer(line, ":");
		    		      	      
		      key=st.nextToken();
		      System.out.println(key);
		      value=st.nextToken();
		      System.out.println(value);
		      
		      
		      
		      ht.put(key,value);
		      		      
			  System.out.println(ht.get(key));
		    
		    
		  }
		  br.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
}

 */





/*

public abstract class Preference {
	/**
	 * Loads the value which is associated with the given key.
	 * @param key The key to read.
	 * @return The value associated with the given key, or null if there was no 
	 * such value set.
	 *
	public String getPreference(String key){
		return PrefsModel.getInstance().getPluginPreference(key);
	}
	
	/**
	 * Stores the value associated with a given key.
	 * @param key The key to save.  In order to ensure that you do not
	 * overwrite the preferences for another plugin, you must use
	 * the key format as follows:
	 * 
	 * "package.Plugin.property"
	 * 
	 * For instance, use the key "com.example.buddi.ImportFoo.LAST_EXECUTE_DAY" 
	 * instead of the key "LAST_EXECUTE_DAY".
	 * @param value The value to store.  Can be any valid String.
	 *
	public void putPreference(String key, String value){
		PrefsModel.getInstance().putPluginPreference(key, value);
	}
	
	/**
	 * Sets the given list of strings in the preferences map, using the given key.
	 * 
	 * @param key The key to save.  In order to ensure that you do not
	 * overwrite the preferences for another plugin, you must use
	 * the key format as follows:
	 * 
	 * "package.Plugin.property"
	 * 
	 * For instance, use the key "com.example.buddi.ImportFoo.LAST_EXECUTE_DAY" 
	 * instead of the key "LAST_EXECUTE_DAY".
	 * @param values The list of values to store
	 *
	public void putListPreference(String key, List<String> values){
		PrefsModel.getInstance().putPluginListPreference(key, values);
	}
	
	/**
	 * Returns the list associated with the given key.
	 * @param key The key to save.  In order to ensure that you do not
	 * overwrite the preferences for another plugin, you must use
	 * the key format as follows:
	 * 
	 * "package.Plugin.property"
	 * 
	 * For instance, use the key "com.example.buddi.ImportFoo.LAST_EXECUTE_DAY" 
	 * instead of the key "LAST_EXECUTE_DAY".

	 * @return
	 *
	public List<String> getListPreference(String key){
		return PrefsModel.getInstance().getPluginListPreference(key);
	}
	
	/**
	 * Loads the value which is associated with the given key, after decrypting 
	 * the value using the given password.
	 * @param key
	 * @param password
	 * @return
	 *
	public String getSecurePreference(String key, char[] password) throws PluginException {
		try {
			BuddiCryptoFactory crypto = new BuddiCryptoFactory();
			String ciphertext = PrefsModel.getInstance().getPluginPreference(key);
			if (ciphertext == null)
				return null;
			return crypto.getDecryptedString(ciphertext, password);
			
		}
		catch (Exception e){
			throw new PluginException("Error getting encrypted preference from key " + key, e);
		}
	}
	
	/**
	 * Stores the value, encrypted using the given password, into the preferences
	 * file using the given key.
	 * @param key
	 * @param value
	 * @param password
	 *
	public void putSecurePreference(String key, String value, char[] password) throws PluginException {
		try {
			BuddiCryptoFactory crypto = new BuddiCryptoFactory();
			String encrypted = crypto.getEncryptedString(value, password);
			PrefsModel.getInstance().putPluginPreference(key, encrypted);
		}
		catch (Exception e){
			throw new PluginException("Error putting encrypted preference to key " + key, e);
		}
	}
}
*/