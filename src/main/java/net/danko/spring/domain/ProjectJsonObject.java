package net.danko.spring.domain;

import java.util.List;

public class ProjectJsonObject {
	
	int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<Project> aaData;
    
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<Project> getAaData() {
            return aaData;
    }

    public void setAaData(List<Project> aaData) {
        	
        Project projects = null;
    		
    	for(int i = 0; i < aaData.size(); ++i) {
    		projects = aaData.get(i);
    		projects.setTasks(null);
    		aaData.set(i, projects);
    	}
        	
        this.aaData = aaData;
    }
}
