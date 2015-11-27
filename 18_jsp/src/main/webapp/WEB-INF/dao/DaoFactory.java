package dao;

public interface DaoFactory {

	public BookDao getBookDao();
	public BookInstanceDao getBookInstanceDao();
	public CategoryDao getCategoryDao();
	public JournalDao getJournalDao();
	public ReaderDao getReaderDao();
	public UsersDao getUsersDao();
	
}
