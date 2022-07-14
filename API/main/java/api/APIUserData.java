package api;

import art.UserData;

public class APIUserData implements UserData {

	private Integer id;
	private String firstName;
	private String secondName;
	private Integer age;
	private String sex;
	private Long money;

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public Integer getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public Long getMoney() {
		return money;
	}

}
