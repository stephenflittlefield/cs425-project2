package edu.jsu.mcis.cs425.project2;

import java.util.Arrays;
import java.util.HashMap;

public class BeanApplicant {
    
    private String username;
    private String displayname;
    private int userid;
    
    private String[] skills;
    private String[] jobs;

    
    public void setSkillsList() {
        
       Database db = new Database();
       db.setSkillsList(userid, skills);
       System.err.println( Arrays.toString(skills) );
       
    }
    
    public String getJobsList() {
        
        Database db = new Database();
        return ( db.getJobsListAsHTML(userid) );
        
    }
    
    public void setJobsList() {
       Database db = new Database();
       db.setJobsList(userid, jobs);
    }
    
    public String getSkillsList() {
        
       Database db = new Database();
       return ( db.getSkillsListAsHTML(userid) );
       
    }
    
    public void setUserInfo() {
        
        Database db = new Database();
        HashMap<String, String> userinfo = db.getUserInfo(username);
        this.userid = Integer.parseInt(userinfo.get("userid"));
        this.displayname = userinfo.get("displayname");
        
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getJobs() {
        return jobs;
    }

    public void setJobs(String[] jobs) {
        this.jobs = jobs;
    }
    
    
    
}