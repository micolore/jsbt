package com.kubrick.sbt.tools.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author k
 */
@Mapper(componentModel = "spring")
public interface UserCoverBasic {

   UserCoverBasic INSTANCE =  Mappers.getMapper(UserCoverBasic.class);

   UserVo toCover(User source);

   User fromCoverEntity(UserVo userVo);

   UserVoTwo toCoverUserVoTwo(User user);
}
