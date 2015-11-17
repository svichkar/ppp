package dao;

public interface DaoFactory<Context> {

	public interface CreateDAO<Context> {
		GenericDao<?> create(Context context) throws Exception;
	}

	public GenericDao<?> getDao(Context context, Class<?> doClass) throws Exception;

}
