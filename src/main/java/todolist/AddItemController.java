package todolist;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

import todolist.dao.TodoItemDAO;
import todolist.entity.TodoItemEntity;
import todolist.logic.TodoItemBusinessLogic;

@ManagedBean(name="ItemContorller")
@Named
@ConversationScoped
public class AddItemController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	TodoItemBusinessLogic todoItemBusinessLogic;

	// Inject task form available through the camunda cdi artifact
	@Inject
	private TaskForm taskForm;

	// the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;
	
	private TodoItemDAO todoItemDAO = new TodoItemDAO();
	
	public void submitForm()  {
		long newItemID = todoItemBusinessLogic.persistTodo(todoItemDAO);
		businessProcess.setVariable("itemId", newItemID);
	}
	
	public void endForm() {
		try {
			// Complete user task from
			taskForm.completeProcessInstanceForm();
			
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

	public TodoItemDAO getTodoItemDAO() {
		return todoItemDAO;
	}
	

	public void setTodoItemDAO(TodoItemDAO todoItemDAO) {
		this.todoItemDAO = todoItemDAO;
	}
	
	public List<TodoItemEntity> getAllTodoItems(){
		List<TodoItemEntity> allTodoItems = todoItemBusinessLogic.getAllTodoItem();
		return allTodoItems;
	}
	
	public void setItemAsDone(long id){
		todoItemBusinessLogic.setItemAsDone(id);
	}

}