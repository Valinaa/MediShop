CREATE TABLE `medicine`
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL UNIQUE,
    `category`     VARCHAR(255) NOT NULL COMMENT '药品类别，如退烧药等',
    `summary`      VARCHAR(255) COMMENT '药品简介',
    `price`        DECIMAL(10, 2) COMMENT '价格',
    `stock`        INT          NOT NULL         DEFAULT 0 COMMENT '库存',
    `status`       ENUM ('0','1','2','3')        DEFAULT '0'
        COMMENT '0: out of stock 没有库存,1: under review 药品审核中,
    2: available 可购买, 3: sell out 售罄',
    `gmt_created`  DATETIME COMMENT '创建时间',
    `gmt_modified` DATETIME COMMENT '更新时间',
    `creator`      VARCHAR(255) COMMENT '创建者',
    `modifier`     VARCHAR(255) COMMENT '更新者',
    `deleted`      TINYINT(1) COMMENT '是否删除' DEFAULT 0
);
# ALTER TABLE `medicine` ADD UNIQUE INDEX `name` (`name` ASC);
# ALTER TABLE `medicine` ADD COLUMN `stock` INT NOT NULL DEFAULT 0 COMMENT '库存' AFTER `price`;

CREATE TABLE `medicine_detail`
(
    `id`                     BIGINT PRIMARY KEY AUTO_INCREMENT,
    `medicineId`             BIGINT(20) NOT NULL COMMENT 'medicine 外键',
    `medicine_specification` VARCHAR(10) COMMENT '药品规格',
    `description`            TEXT COMMENT '药品描述',
    `sideEffects`            TEXT COMMENT '副作用',
    `ingredients`            TEXT COMMENT '药品成分',
    `image_url`              VARCHAR(255) COMMENT '药品图片URL',
    `manufacture_date`       DATETIME   NOT NULL COMMENT '生产日期',
    `validity_date`          DATETIME   NOT NULL COMMENT '有效期',
    `gmt_created`            DATETIME COMMENT '创建时间',
    `gmt_modified`           DATETIME COMMENT '更新时间',
    `creator`                VARCHAR(255) COMMENT '创建者',
    `modifier`               VARCHAR(255) COMMENT '更新者',
    `deleted`                TINYINT(1) COMMENT '是否删除' DEFAULT 0,
    FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`)
);
CREATE TABLE `user`
(
    `id`                BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username`          VARCHAR(255) UNIQUE             NOT NULL,
    `password`          VARCHAR(255)                    NOT NULL,
    `authorities`       VARCHAR(255) COMMENT '权限列表' NOT NULL,
    `full_name`         VARCHAR(255) COMMENT '姓名或负责人',
    `email`             VARCHAR(255) UNIQUE             NOT NULL,
    `phone_number`      VARCHAR(20),
    `address`           VARCHAR(255),
    `ip_address`        VARCHAR(255),
    `ip_region`         VARCHAR(255)                    NULL DEFAULT NULL,
    `user_type`         ENUM ('0','1','2','3')               DEFAULT '0' COMMENT '0: guest, 1: customer, 2: business, 3: admin',
    `license_image_url` VARCHAR(255) COMMENT '营业执照图片URL',
    `gmt_created`       DATETIME COMMENT '创建时间',
    `gmt_modified`      DATETIME COMMENT '更新时间',
    `creator`           VARCHAR(255) COMMENT '创建者',
    `modifier`          VARCHAR(255) COMMENT '更新者',
    `deleted`           TINYINT(1) COMMENT '是否删除'        DEFAULT 0
);
# ALTER TABLE `medicine`
#     ADD COLUMN `gmt_created` DATETIME COMMENT '创建时间' AFTER `status`,
#     ADD COLUMN `gmt_modified` DATETIME COMMENT '更新时间' AFTER `gmt_created`,
#     ADD COLUMN `creator` VARCHAR(255) COMMENT '创建者' AFTER `gmt_modified`,
#     ADD COLUMN `modifier` VARCHAR(255) COMMENT '更新者' AFTER `creator`,
#     ADD COLUMN `deleted` TINYINT(1) COMMENT '是否删除' AFTER `modifier`;
