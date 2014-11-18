/**
 * 
 */
package com.example.bookshelf_initialpage.database;

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
	private String fileName;
	private String title;
	private String author;
	private long bookmark;


	/**
	 * Creates a Book object which stores information about a Book in the
	 * library
	 * 
	 * @param id
	 *            the unique primary key of the book in the database
	 * @param fileName
	 *            the file name used to access the book on the phone
	 * @param title
	 *            the title of the book used for sorting and display purposes
	 * @param author
	 *            the author of the book used for sorting and display purposes
	 */
	public Book(long id, String fileName, String title, String author) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.title = title;
		this.author = author;
		this.bookmark = -1;
	}

	/**
	 * Create a Book object which stores information about a Book in the
	 * library. The id is automatically assigned by the database.
	 * 
	 * @param fileName
	 *            the file name used to access the book on the phone
	 * @param title
	 *            the title of the book used for sorting and display purposes
	 * @param author
	 *            the author of the book used for sorting and display purposes
	 */
	public Book(String fileName, String title, String author) {
		super();
		this.id = -1;
		this.fileName = fileName;
		this.title = title;
		this.author = author;
		this.bookmark = -1;
	}

	private Book(Parcel in) {
		super();
		this.id = in.readLong();
		this.fileName = in.readString();
		this.title = in.readString();
		this.author = in.readString();
		this.bookmark = in.readLong();
	}

	public long getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		return "Book [id=" + id + ", fileName=" + fileName + ", title=" + title
				+ ", author=" + author + ", bookmark=" + bookmark + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(getFileName());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + (int) (bookmark ^ (bookmark >>> 32));
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookmark != other.bookmark)
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
