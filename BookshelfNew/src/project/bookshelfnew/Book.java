/**
 * 
 */
package project.bookshelfnew;

/**
 * Model for a book which contains the data saved in the database and shown in the user interface
 * @author Clay
 *
 */
public class Book {
	private long id;
	private String title;
	private String author;
	private long bookmark;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getBookmark() {
		return bookmark;
	}

	public void setBookmark(long bookmark) {
		this.bookmark = bookmark;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", bookmark=" + bookmark + "]";
	}

}
