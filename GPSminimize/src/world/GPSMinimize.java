package world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GPSMinimize {
	
	private ArrayList<Venue> venues;
	private ArrayList<User> users;
	private File venueFile;
	
	public GPSMinimize(File routeFile)
	{
		venueFile = routeFile;	
		venues = new ArrayList<Venue>();
		users = new ArrayList<User>();
		loadVenues(venueFile);
	}

	public void loadVenues(File csvFile) {
		
		  
		  
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            line = br.readLine();
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] venueData = line.split(cvsSplitBy);
	                
	                String venueName = venueData[0];
	                double venueLat = Double.parseDouble(venueData[1]);
	                double venueLong = Double.parseDouble(venueData[2]);
	                
	                Venue venue = new Venue(venueName, venueLat, venueLong);
	                System.out.println(venue.toString());
	                venues.add(venue);

	                
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
		
	}
	
	public void loadUsers(File csvFile) {
		
		  
		  
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] userData = line.split(cvsSplitBy);
                
                int id = Integer.parseInt(userData[0]);
                String userId = userData[1];               
                double Lat = Double.parseDouble(userData[2]);
                double Long = Double.parseDouble(userData[3]);
                
                User user = new User(id,userId, Lat, Long);
                System.out.println(user.toString());
                users.add(user);

                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	
	
}
	
	public void outPutFile(String csvFile) throws FileNotFoundException {
		
		  
		  
		 PrintWriter pw = new PrintWriter(new File(csvFile));
	             
	   
	        
	        for(int i = 0; i < users.size(); i++)
	        {
	        	User actual = users.get(i);
	        	int f = users.size();
	        	String line = actual.getName() + "," + actual.getLatitude() + "," + actual.getLongitude() + "," + findNearestVenue(actual.getLatitude(), actual.getLongitude()).getName() + '\n';
	        	
	        	
	        		        	
	        	pw.write(line);
	        	System.out.println(i + " -of- " + f + "--" + line);
	        }
	       

	      
	        pw.close();
	        System.out.println("done!");
	
	
}
	
	
	public Venue findNearestVenue(double latitude, double longitude)
	{
		Venue res = null;
		double  minor = 99999999;
		
		for(int i = 0; i < venues.size(); i ++)
		{
			Venue actual = venues.get(i);
			
			double minLat = Math.abs(latitude - actual.getLatitude());
			double minLong = Math.abs(longitude - actual.getLongitude());
			double mad = minLat + minLong;
			if(mad < minor)
			{
				res = actual;
				minor = mad;
			}
		}
		
		
		
		
		return res;
	}

}
