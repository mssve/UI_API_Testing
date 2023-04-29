package POJO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserPojo {

	private Integer id;
	private String name;
	private Integer age;
	private String description;
	private Date createdDate;
	private Date updatedDate;
}
