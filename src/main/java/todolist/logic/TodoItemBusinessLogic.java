package todolist.logic;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import todolist.dao.TodoItemDAO;
import todolist.entity.TodoItemEntity;

@Stateless
@Named
public class TodoItemBusinessLogic {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	private static Logger LOGGER = Logger.getLogger(TodoItemBusinessLogic.class.getName());

	public long persistTodo(TodoItemDAO item) {
		TodoItemEntity ie = new TodoItemEntity();
		ie.setName(item.getName());
		ie.setDetails(item.getDetails());
		ie.setDone(false);
		
		entityManager.persist(ie);
		entityManager.flush();
		
		return ie.getId();
	}

	public TodoItemDAO getTodoItemDAO(long itemId) {
		TodoItemEntity ie = getItem(itemId);
		TodoItemDAO dao = new TodoItemDAO(ie.getId(), ie.getName(), ie.getDetails(), ie.isDone());
		return dao;
	}

	public void setItemAsDone(long itemId) {
		TodoItemEntity ie = getItem(itemId);
		ie.setDone(true);
		entityManager.merge(ie);
	}

	public boolean isItemDone(long itemId) {
		TodoItemEntity ie = getItem(itemId);
		return ie.isDone();
	}


	@SuppressWarnings("unchecked")
	public List<TodoItemEntity> getAllTodoItem(){
		List<TodoItemEntity>allItems = entityManager.createQuery("SELECT t FROM TodoItemEntity t WHERE t.done=false").getResultList();
		return  allItems;
	}
	  
	private TodoItemEntity getItem(Long itemId) {
		return entityManager.find(TodoItemEntity.class, itemId);
	}
}
