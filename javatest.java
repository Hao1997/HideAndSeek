import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class javatest {
	

		public static void main(String[] args) {
		    URL url;
		    InputStream is = null;
		    BufferedReader br;
		    String line;
		    Path newpath = Paths.get("C:\\Users\\turtle\\desktop");
		   
		    
		    
		    
		    
		    
		    
		    
		    
		    try {
		        url = new URL("file:///C:/Users/turtle/Videos/S%20noitarepo/Operation%20G/Operation_G_MainPage.html#");
		        is = url.openStream();  // throws an IOException
		        br = new BufferedReader(new InputStreamReader(is));
		    	
		      
		        	
		        	 try{
		        		File file = new File("C:\\Users\\turtle\\Videos\\S noitarepo\\Operation G\\temp1.html");
		          		file.createNewFile();
		  		        BufferedWriter write = new BufferedWriter(new FileWriter(file));
		  		      while ((line = br.readLine()) != null) {
		  		        write.write(line);
		 		        write.newLine();
		  		      }
		  		    write.close();
		 		    	
		 		    	
			            	
			            
		 		} catch (FileAlreadyExistsException x) {
		 		    System.err.format("file named %s" +
		 		        " already exists%n");
		 		} catch (IOException x) {
		 		    // Some other sort of failure, such as permissions.
		 		    System.err.format("createFile error: %s%n", x);
		 		}
		        	 
		        		
		        
		        
		    } catch (MalformedURLException mue) {
		         mue.printStackTrace();
		    } catch (IOException ioe) {
		         ioe.printStackTrace();
		    } finally {
		    	
		        try {
		            if (is != null) is.close();
		            
		            
		        } catch (IOException ioe) {
		            // nothing to see here
		        }
		    }
		   
		    
		}
		
		
}
