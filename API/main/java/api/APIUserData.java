package api;

import interfaces.UserData;

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

	public boolean equals(UserData ud) {
		if (!this.id.equals(ud.getId()))
			return false;
		if (!this.age.equals(ud.getAge()))
			return false;
		if (!this.money.equals(ud.getMoney()))
			return false;
		if (!this.firstName.equals(ud.getFirstName()))
			return false;
		if (!this.secondName.equals(ud.getSecondName()))
			return false;
		if (!this.sex.equals(ud.getSex()))
			return false;

		return true;
	}

}
