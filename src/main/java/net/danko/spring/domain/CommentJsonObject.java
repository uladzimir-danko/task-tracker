package net.danko.spring.domain;

import java.util.List;

public class CommentJsonObject {
	
	int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<Comment> aaData;
    
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

    public List<Comment> getAaData() {
            return aaData;
    }

    public void setAaData(List<Comment> aaData) {
        	
        Comment comments = null;
    		
    	for(int i = 0; i < aaData.size(); ++i) {
    		comments = aaData.get(i);
    		comments.setTask(null);
    		aaData.set(i, comments);
    	}
        	
        this.aaData = aaData;
    }

}
