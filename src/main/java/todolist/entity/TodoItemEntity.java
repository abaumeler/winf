package todolist.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class TodoItemEntity implements Serializable {

  private static  final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  protected Long id;

  @Version
  protected long version;

  protected String name;
  protected String details;
  protected boolean done;

  public boolean isDone() {
	return done;
}

public void setDone(boolean done) {
	this.done = done;
}

public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
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