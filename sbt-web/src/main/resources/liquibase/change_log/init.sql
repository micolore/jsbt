#
# SQL Export
# Created by Querious (201067)
# Created: 2020年12月20日 GMT+8 下午5:43:33
# Encoding: Unicode (UTF-8)
#


CREATE DATABASE IF NOT EXISTS `spring-security` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE `spring-security`;




SET @PREVIOUS_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;


CREATE TABLE `t_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Controller路径',
  `menu_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编码',
  `parent_id` int DEFAULT NULL COMMENT '父菜单ID',
  `menu_type` int DEFAULT '0' COMMENT '菜单类型：0-菜单1-按钮',
  `order_num` int DEFAULT '99' COMMENT '显示序号',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` int DEFAULT '0' COMMENT '删除状态：0-存在1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_organization` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pid` int DEFAULT '0',
  `deep` tinyint DEFAULT '0',
  `status` tinyint DEFAULT '0',
  `create_at` timestamp NULL DEFAULT NULL,
  `create_by` bigint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `data_scope` int DEFAULT '0' COMMENT '1、全部 2、自己',
  `create_at` timestamp NULL DEFAULT NULL,
  `create_by` bigint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_role_menus` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色菜单id',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `menu_id` int DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `organization` int DEFAULT '0' COMMENT '部门id',
  `status` tinyint DEFAULT '0',
  `create_by` bigint DEFAULT '0',
  `create_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_user_roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色对照ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;




SET FOREIGN_KEY_CHECKS = @PREVIOUS_FOREIGN_KEY_CHECKS;


SET @PREVIOUS_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;


LOCK TABLES `t_menu` WRITE;
ALTER TABLE `t_menu` DISABLE KEYS;
INSERT INTO `t_menu` (`id`, `menu_name`, `menu_url`, `menu_code`, `parent_id`, `menu_type`, `order_num`, `creator`, `create_time`, `updator`, `update_time`, `deleted`) VALUES 
	(1,'公共资源','/publicResource',NULL,0,0,99,NULL,NULL,NULL,NULL,0),
	(2,'VIP资源','/vipResource',NULL,0,0,99,NULL,NULL,NULL,NULL,0),
	(3,'主页','/home',NULL,0,0,0,NULL,NULL,NULL,NULL,0),
	(4,'公共权限请求按钮','/test/public',NULL,0,1,99,NULL,NULL,NULL,NULL,0),
	(5,'VIP权限请求按钮','/test/vip',NULL,0,1,99,NULL,NULL,NULL,NULL,0),
	(6,'mian','/main',NULL,0,0,99,NULL,NULL,NULL,NULL,0),
	(7,'获取用户列表按钮','/user/list',NULL,NULL,1,99,NULL,NULL,NULL,NULL,0);
ALTER TABLE `t_menu` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `t_organization` WRITE;
ALTER TABLE `t_organization` DISABLE KEYS;
INSERT INTO `t_organization` (`id`, `name`, `pid`, `deep`, `status`, `create_at`, `create_by`) VALUES 
	(1,'google',0,0,1,NULL,1),
	(2,'google_1',1,1,0,NULL,1),
	(3,'google_2',1,1,0,NULL,1),
	(4,'google_1_1',2,2,0,NULL,1),
	(5,'google_2_1',3,2,0,NULL,1);
ALTER TABLE `t_organization` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `t_role` WRITE;
ALTER TABLE `t_role` DISABLE KEYS;
INSERT INTO `t_role` (`id`, `role_name`, `data_scope`, `create_at`, `create_by`) VALUES 
	(1,'ROLE_USER',2,NULL,0),
	(2,'ROLE_ADMIN',1,NULL,0);
ALTER TABLE `t_role` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `t_role_menus` WRITE;
ALTER TABLE `t_role_menus` DISABLE KEYS;
INSERT INTO `t_role_menus` (`id`, `role_id`, `menu_id`) VALUES 
	(1,1,1),
	(2,2,1),
	(3,2,2),
	(4,1,3),
	(5,2,3),
	(6,1,4),
	(7,2,4),
	(8,2,5),
	(9,1,6),
	(10,2,6),
	(11,1,7),
	(12,2,7);
ALTER TABLE `t_role_menus` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `t_user` WRITE;
ALTER TABLE `t_user` DISABLE KEYS;
INSERT INTO `t_user` (`id`, `username`, `password`, `organization`, `status`, `create_by`, `create_at`) VALUES 
	(1,'user','$2a$10$D5E9lza7z8uea6fP/oNOJeuRq/a/y8RXQWslTDONsqxQTPlgW7Hr6',0,0,1,NULL),
	(2,'admin','$2a$10$on7jUGJN.4CyjPZzyroZce0ugjCQFzA6dRuOTcEFTBLLhe3oYe5Gu',1,0,0,NULL),
	(3,'1',NULL,3,0,1,NULL),
	(4,'2',NULL,4,0,1,NULL),
	(5,'3',NULL,5,0,1,NULL);
ALTER TABLE `t_user` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `t_user_roles` WRITE;
ALTER TABLE `t_user_roles` DISABLE KEYS;
INSERT INTO `t_user_roles` (`id`, `user_id`, `role_id`) VALUES 
	(1,1,1),
	(2,2,2);
ALTER TABLE `t_user_roles` ENABLE KEYS;
UNLOCK TABLES;




SET FOREIGN_KEY_CHECKS = @PREVIOUS_FOREIGN_KEY_CHECKS;


