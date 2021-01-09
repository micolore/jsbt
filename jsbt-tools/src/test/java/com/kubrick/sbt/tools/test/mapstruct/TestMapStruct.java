package com.kubrick.sbt.tools.test.mapstruct;

import com.kubrick.sbt.tools.mapstruct.User;
import com.kubrick.sbt.tools.mapstruct.UserMapper;
import com.kubrick.sbt.tools.mapstruct.UserVo;
import org.junit.Test;

/**
 * @author k
 * @version 1.0.0
 * @ClassName TestMapStruct
 * @description: TODO
 * @date 2020/12/29 下午11:00
 */
public class TestMapStruct {

	@Test
	public void tm() {
		User user = new User(1, "2", "3");
		UserVo userVo = UserMapper.INSTANCE.toCover(user);
		System.out.println("userVo:" + userVo);

		User castUser = UserMapper.INSTANCE.fromCoverEntity(userVo);
		System.out.println("castUser:" + castUser);

	}

}
