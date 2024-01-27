CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    category_name   VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id           BIGINT NOT NULL,
    id                BIGINT NOT NULL,
    favourite_student VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id BIGINT NOT NULL,
    id      BIGINT NOT NULL,
    rating  VARCHAR(255) NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    id     BIGINT NOT NULL,
    email  VARCHAR(255) NULL,
    passwd VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE ma_instructor
(
    id                BIGINT NOT NULL,
    email             VARCHAR(255) NULL,
    passwd            VARCHAR(255) NULL,
    favourite_student VARCHAR(255) NULL,
    CONSTRAINT pk_ma_instructor PRIMARY KEY (id)
);

CREATE TABLE ma_mentor
(
    id     BIGINT NOT NULL,
    email  VARCHAR(255) NULL,
    passwd VARCHAR(255) NULL,
    rating VARCHAR(255) NULL,
    CONSTRAINT pk_ma_mentor PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    title           VARCHAR(255) NULL,
    price DOUBLE NULL,
    `description`   VARCHAR(255) NULL,
    category_id     BIGINT NULL,
    imageurl        VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id                BIGINT NOT NULL,
    user_type         INT NULL,
    email             VARCHAR(255) NULL,
    passwd            VARCHAR(255) NULL,
    favourite_student VARCHAR(255) NULL,
    rating            VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tbc_instructor
(
    id                BIGINT NOT NULL,
    email             VARCHAR(255) NULL,
    passwd            VARCHAR(255) NULL,
    favourite_student VARCHAR(255) NULL,
    CONSTRAINT pk_tbc_instructor PRIMARY KEY (id)
);

CREATE TABLE tbc_mentor
(
    id     BIGINT NOT NULL,
    email  VARCHAR(255) NULL,
    passwd VARCHAR(255) NULL,
    rating VARCHAR(255) NULL,
    CONSTRAINT pk_tbc_mentor PRIMARY KEY (id)
);

CREATE TABLE tbc_user
(
    id     BIGINT NOT NULL,
    email  VARCHAR(255) NULL,
    passwd VARCHAR(255) NULL,
    CONSTRAINT pk_tbc_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);