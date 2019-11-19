
package edu.jsu.mcis.cs425.project2;

import java.sql.Connection;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 *
 * @author sfl41
 */

public class Database {
    
    private Connection getConnection() {
        
        Connection conn = null;
        
        try {
            
            Context envContext = new InitialContext();
            Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/db_pool");
            conn = ds.getConnection();
            
        }        
        catch (Exception e) { e.printStackTrace(); }
        
        return conn; 
    }
    
    public String getJobsListAsHTML(int userid) {
        
        StringBuilder s = new StringBuilder();
    
        try {
            
            // (Complete this query, then generate the HTML as in the "getSkillsListAsHTML()" method)
            
            // SELECT * FROM (applicants_to_skills a JOIN skills_to_jobs s ON a.skillsid = s.skillsid)
            // JOIN jobs ON jobs.id = s.jobsid
            // ORDER BY name;
            
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return s.toString();
        
    }

    
    
    public String getSkillsListAsHTML(int userid) {
        
        StringBuilder s = new StringBuilder();
    
        try {
            
            String query = "SELECT skills.*, a.userid FROM skills"
                    + " LEFT JOIN (SELECT * FROM applicants_to_skills WHERE userid = ?) AS a "
                    + "ON skills.id = a.skillsid ORDER BY description";
            
            Connection conn = getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userid);
            boolean hasresults = pstmt.execute();
            
            if (hasresults) {
                
                ResultSet resultset = pstmt.getResultSet();
                
                while (resultset.next()) {

                    String description = resultset.getString("description");
                    int id = resultset.getInt("id");
                    int user = resultset.getInt("userid");

                    s.append("<input type=\"checkbox\" name=\"skills\" value=\"");
                    s.append(id);
                    s.append("\" id=\"skills_").append(id).append("\" ");

                    if (user != 0)
                        s.append("checked");

                    s.append(">\n");
                    
                    s.append("<label for=\"skills_").append(id).append("\">");
                    s.append(description);
                    s.append("<label><br/>\n\n");

                }
                
                
            }

        }
        catch (Exception e) { e.printStackTrace(); }
        

        //<input type="checkbox" name="skills" value="1" id="skills_id" checked>
        //<label for="skills_id">Changing bed linens.</label><br />
        //the rest of the code goes here
        
        
        //SELECT skills.*, 
        //a.userid FROM skills LEFT JOIN (SELECT * FROM applicants_to_skills WHERE userid = 1) 
        //AS a ON skills.id = a.skillsid;
        
      
        return s.toString();
        
    }

    public HashMap getUserInfo(String username) {
        
        HashMap<String, String> results = null;
        
        try {
            String query = "SELECT * FROM user WHERE username = ?";

            Connection conn = getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            boolean hasresults = pstmt.execute();
           
            if ( hasresults) { // were any results returned?
                
                ResultSet resultset = pstmt.getResultSet();
                
                if (resultset.next()) { // is the result set non-empty?
                    
                    results = new HashMap<>();
                    
                    String id = String.valueOf(resultset.getInt("id"));
                    String displayname = resultset.getString("displayname");
                    
                    results.put("userid", id);
                    results.put("displayname", displayname);
                    
                }
                
            }

           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return results;
    }
}
