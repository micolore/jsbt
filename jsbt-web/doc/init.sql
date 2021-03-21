# 用户
CREATE TABLE `sys_user` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
                            `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
                            `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
                            `organization` int DEFAULT '0' COMMENT '部门id',
                            `status` tinyint DEFAULT '0',
                            `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                            `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                            `update_by` bigint DEFAULT '0' COMMENT '修改人',
                            `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                            `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
# 用户角色表
CREATE TABLE `sys_user_role` (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色对照ID',
                                 `user_id` int DEFAULT NULL COMMENT '用户ID',
                                 `role_id` int DEFAULT NULL COMMENT '角色ID',
                                 `status` tinyint DEFAULT '0',
                                 `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                                 `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                                 `update_by` bigint DEFAULT '0' COMMENT '修改人',
                                 `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                 `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                                 `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
# 菜单表
CREATE TABLE `sys_menu` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
                            `menu_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'uri路径',
                            `menu_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编码',
                            `parent_id` int DEFAULT NULL COMMENT '父菜单ID',
                            `menu_type` int DEFAULT '0' COMMENT '菜单类型：0-菜单1-按钮',
                            `order_num` int DEFAULT '99' COMMENT '显示序号',
                            `status` tinyint DEFAULT '0',
                            `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                            `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                            `update_by` bigint DEFAULT '0' COMMENT '修改人',
                            `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                            `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
# 组织表
CREATE TABLE `sys_organization` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                    `pid` int DEFAULT '0',
                                    `deep` tinyint DEFAULT '0',
                                    `status` tinyint DEFAULT '0',
                                    `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                                    `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                                    `update_by` bigint DEFAULT '0' COMMENT '修改人',
                                    `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                    `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                                    `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
# 角色表
CREATE TABLE `sys_role` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
                            `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
                            `data_scope` int DEFAULT '0' COMMENT '1、全部 2、自己',
                            `status` tinyint DEFAULT '0',
                            `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                            `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                            `update_by` bigint DEFAULT '0' COMMENT '修改人',
                            `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                            `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# 角色菜单
CREATE TABLE `sys_role_menus` (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色菜单id',
                                  `role_id` int DEFAULT NULL COMMENT '角色id',
                                  `menu_id` int DEFAULT NULL COMMENT '菜单id',
                                  `status` tinyint DEFAULT '0',
                                  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建人',
                                  `create_by` bigint DEFAULT '0' COMMENT '创建时间',
                                  `update_by` bigint DEFAULT '0' COMMENT '修改人',
                                  `update_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                  `delete_by` bigint DEFAULT '0' COMMENT '删除人',
                                  `delete_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

