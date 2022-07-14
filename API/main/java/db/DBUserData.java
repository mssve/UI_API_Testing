package db;

import art.UserData;

public class DBUserData implements UserData {

	private Integer id;
	private Integer age;
	private Integer houseId;
	private Long money;
	private String firstName;
	private String secondName;
	private String sex;

	public DBUserData() {
	}

	public DBUserData(Integer id, Integer age, Long money, String firstName, String secondName, Boolean sex) {
		this.id = id;
		this.age = age;
		this.money = money;
		this.firstName = firstName;
		this.secondName = secondName;
		if (sex != null) {
			this.sex = sex ? "MALE" : "FEMALE";
		}

	}

	public Integer getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public Long getMoney() {
		return money;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getSex() {
		return sex;
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
