package com.kubrick.sbt.tools.test.mapstruct;

import com.kubrick.sbt.tools.mapstruct.User;
import com.kubrick.sbt.tools.mapstruct.UserCoverBasic;
import com.kubrick.sbt.tools.mapstruct.UserVoTwo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName TestMapStruct
 * @description: TODO
 * @date 2020/12/24 上午12:12
 */
public class TestMapStruct {

	@Test
	public void coverEntity() {
		User user = User.builder()
		.id(1)
		.name("li")
		.address("sh")
		.build();
		List<Object> list = new ArrayList<>();
		list.add(user);

		UserVoTwo userVoTwo = UserCoverBasic.INSTANCE.toCoverUserVoTwo(user);
		System.out.println(userVoTwo);

	}

}
