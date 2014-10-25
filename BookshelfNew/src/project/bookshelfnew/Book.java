/**
 * 
 */
package project.bookshelfnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for a book which contains the data saved in the database and shown in
 * the user interface
 * 
 * @author Clay
 * 
 */
public class Book implements Parcelable {
	private long id;
	private String title;
	private String author;
	private long bookmark;

	public Book() {
		super();
	}

	public Book(long id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookmark = -1;
	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.bookmark = -1;
	}

	private Book(Parcel in) {
		super();
		this.id = in.readLong();
		this.title = in.readString();
		this.author = in.readString();
		this.bookmark = in.readLong();
	}

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

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", bookmark=" + bookmark + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(getTitle());
		dest.writeString(getAuthor());
		dest.writeLong(getBookmark());
	}

	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}

		public Book[] newArray(int size) {
			return new Book[size];
		}
	};

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
