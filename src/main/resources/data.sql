CREATE SCHEMA IF NOT EXISTS `mini_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE
`mini_project` ;



-- -----------------------------------------------------

-- Table `mini_project`.`account`

-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mini_project`.`account`
(

    `account_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,

    `username`
    VARCHAR
(
    255
) NOT NULL,

    `password` VARCHAR
(
    255
) NOT NULL,

    `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,

    `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `created_by` INT NULL DEFAULT NULL,

    `updated_by` INT NULL DEFAULT NULL,
    PRIMARY KEY
(
    `account_id`
))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------

-- Table `mini_project`.`team`

-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mini_project`.`team`
(

    `team_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,

    `team_name`
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    `team_id`
))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------

-- Table `mini_project`.`project`

-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mini_project`.`project`
(

    `project_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,

    `project_name`
    VARCHAR
(
    255
) NOT NULL,

    `project_leader_id` INT NULL DEFAULT NULL,
    PRIMARY KEY
(
    `project_id`
),
    UNIQUE INDEX `project_leader_id`
(
    `project_leader_id`
    ASC
) VISIBLE,
    CONSTRAINT `project_ibfk_1`
    FOREIGN KEY
(
    `project_leader_id`
)
    REFERENCES `mini_project`.`employee`
(
    `employee_id`
))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------

-- Table `mini_project`.`employee`

-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mini_project`.`employee`
(

    `employee_id`
    INT
    NOT
    NULL,

    `name`
    VARCHAR
(
    255
) NOT NULL,

    `gender` VARCHAR
(
    10
) NULL DEFAULT NULL,

    `birthday` DATE NULL DEFAULT NULL,

    `phone` VARCHAR
(
    15
) NULL DEFAULT NULL,

    `email` VARCHAR
(
    255
) NULL DEFAULT NULL,

    `address` VARCHAR
(
    255
) NULL DEFAULT NULL,

    `team_id` INT NULL DEFAULT NULL,

    `project_id` INT NULL DEFAULT NULL,

    `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,

    `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `created_by` VARCHAR
(
    255
) NULL DEFAULT NULL,

    `updated_by` VARCHAR
(
    255
) NULL DEFAULT NULL,
    PRIMARY KEY
(
    `employee_id`
),
    INDEX `team_id`
(
    `team_id`
    ASC
) VISIBLE,
    INDEX `fk_project`
(
    `project_id`
    ASC
) VISIBLE,
    CONSTRAINT `employee_ibfk_1`
    FOREIGN KEY
(
    `team_id`
)
    REFERENCES `mini_project`.`team`
(
    `team_id`
),
    CONSTRAINT `fk_project`
    FOREIGN KEY
(
    `project_id`
)
    REFERENCES `mini_project`.`project`
(
    `project_id`
))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



USE
`mini_project`;



DELIMITER
$$

USE `mini_project`$$

CREATE

DEFINER=`root`@`localhost`

TRIGGER `mini_project`.`before_insert_employee`

BEFORE INSERT ON `mini_project`.`employee`

FOR EACH ROW

BEGIN

   SET
NEW.employee_id = (SELECT IFNULL(MAX(employee_id), 6319999) + 1 FROM employee);

END$$



DELIMITER ;