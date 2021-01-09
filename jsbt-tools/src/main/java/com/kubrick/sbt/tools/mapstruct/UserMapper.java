package com.kubrick.sbt.tools.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author k
 */
@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	/**
	 * toCover
	 * @param source
	 * @return
	 */
	@Mapping(source = "id", target = "userId")
	UserVo toCover(User source);

	/**
	 * fromCoverEntity
	 * @param userVo
	 * @return
	 */
	@Mapping(source = "userId", target = "id")
	User fromCoverEntity(UserVo userVo);

	/**
	 * toCoverUserVoTwo
	 * @param user
	 * @return
	 */
	UserVoTwo toCoverUserVoTwo(User user);

}
