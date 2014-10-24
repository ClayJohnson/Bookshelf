/**
 * 
 */
package project.bookshelfnew;

/**
 * Model for a category which contains the data saved in the database and shown
 * in the user interface
 * 
 * @author Clay
 * 
 */
public class Category {
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
