package com.kubrick.sbt.tools.mapstruct;

import lombok.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UserVo
 * @description: TODO
 * @date 2020/12/24 上午12:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserVo {

	private Integer userId;

	private String name;

	private String address;

}
