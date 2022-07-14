package interfaces;

/* Предоставляет возможность использования метода equals для объектов имплементирующих интерфейс */

public interface UserData {

	Integer getId();

	String getFirstName();

	String getSecondName();

	Integer getAge();

	String getSex();

	Long getMoney();
}
