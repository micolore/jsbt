package com.kubrick.sbt.tools.mapstruct;

import lombok.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName User
 * @description: TODO
 * @date 2020/12/24 上午12:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

	private Integer id;

	private String name;

	private String address;

}
