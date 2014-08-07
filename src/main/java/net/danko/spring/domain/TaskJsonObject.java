package net.danko.spring.domain;

import java.util.List;

public class TaskJsonObject {
	
	int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<Task> aaData;
    
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

    public List<Task> getAaData() {
        return aaData;
    }

    public void setAaData(List<Task> aaData) {
    	
      	Task tasks = null;
    	
    	for(int i = 0; i < aaData.size(); ++i) {
    		tasks = aaData.get(i);
    		tasks.setProject(null);
    		tasks.setComments(null);
    		aaData.set(i, tasks);
    	}
       	
        this.aaData = aaData;
    }

}
