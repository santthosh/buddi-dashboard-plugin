/**
 * 
 */
package org.homeunix.thecave.plugins;
import java.util.*;
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