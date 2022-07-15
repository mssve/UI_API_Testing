package ObjectModels;

public class UserModel {

	private Integer id;
	private Integer age;
	private Long money;
	private String firstName;
	private String secondName;
	private String sex;

	public UserModel(Integer id, Integer age, Long money, String firstName, String secondName, Boolean sex) {
		this.id = id;
		this.age = age;
		this.money = money;
		this.firstName = firstName;
		this.secondName = secondName;
		if (sex != null) {
			this.sex = sex ? "MALE" : "FEMALE";
		}
	}

	public UserModel(){
	}

	public Integer getId() {
		return id;
	}

	public Integer getAge() {
		return age;
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

	public boolean equals(UserModel um) {
		if (!this.id.equals(um.getId()))
			return false;
		if (!this.age.equals(um.getAge()))
			return false;
		if (!this.money.equals(um.getMoney()))
			return false;
		if (!this.firstName.equals(um.getFirstName()))
			return false;
		if (!this.secondName.equals(um.getSecondName()))
			return false;
		if (!this.sex.equals(um.getSex()))
			return false;

		return true;
	}

}
