package databases;

public interface DatabaseExtend<T> {
	T findOneById(String id);
}
