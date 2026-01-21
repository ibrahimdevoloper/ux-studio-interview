CREATE TABLE IF NOT EXISTS contact(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    phone_number VARCHAR(40) DEFAULT NULL,
    email VARCHAR(5000) DEFAULT NULL,
    image_path VARCHAR(5000) DEFAULT NULL,
    image_reference VARCHAR(5000) DEFAULT NULL,
    storage_provider VARCHAR(5000) DEFAULT NULL
);

INSERT INTO contact VALUES(1,'Ibrahim','Shaglil','+963959504146','ibrah@ss.com',null,null,null);