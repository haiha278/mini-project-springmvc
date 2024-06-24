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



CREATE TABLE employee (
                          employee_id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          gender VARCHAR(10),
                          birthday DATE,
                          phone VARCHAR(15),
                          email VARCHAR(255),
                          address TEXT,
                          project_id INT,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          created_by VARCHAR(255),
                          updated_by VARCHAR(255)
);

DELIMITER //

CREATE TRIGGER before_insert_employee
    BEFORE INSERT ON employee
    FOR EACH ROW
BEGIN
    SET NEW.employee_id = (SELECT IFNULL(MAX(employee_id), 6319999) + 1 FROM employee);
END//

DELIMITER ;

CREATE TABLE project (
                         project_id INT PRIMARY KEY AUTO_INCREMENT,
                         project_name VARCHAR(255) NOT NULL,
                         project_leader_id INT UNIQUE,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         created_by VARCHAR(255),
                         updated_by VARCHAR(255),
                         FOREIGN KEY (project_leader_id) REFERENCES employee(employee_id)
);

ALTER TABLE employee
    ADD CONSTRAINT fk_project
        FOREIGN KEY (project_id) REFERENCES project(project_id);

ALTER TABLE employee
    ADD COLUMN team_id INT,
ADD CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES team(team_id);