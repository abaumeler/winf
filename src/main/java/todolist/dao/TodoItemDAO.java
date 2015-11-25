package todolist.dao;

public class TodoItemDAO {
	private String name;
	private String details;
	private boolean done;
	
	public TodoItemDAO() {
		
	}
	
	public TodoItemDAO(String name, String details,  boolean done) {
		this.name = name;
		this.details = details;
		this.done = done;
	}
	


	  public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getDetails() {
	    return details;
	  }

	  public void setDetails(String details) {
	    this.details = details;
	  }

}
