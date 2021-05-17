DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS person;

CREATE TABLE person(
    legal_id VARCHAR(15) NOT NULL PRIMARY KEY,
    legal_id_type VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    birth_date DATE
);

CREATE TABLE card(
   card_number VARCHAR(16) NOT NULL PRIMARY KEY,
   expiration_date VARCHAR(5) NOT NULL,
   security_code VARCHAR(3) NOT NULL,
   cardholder_name VARCHAR(50) NOT NULL,
   person_id VARCHAR(15) NOT NULL,
   foreign key (person_id) references person(legal_id)
);

INSERT INTO person (legal_id, legal_id_type, name, last_name, phone, birth_date)
VALUES ('001', 'CC', 'testname', 'testlastname', '089880889', DATE '2015-12-17');

INSERT INTO card (card_number, expiration_date, security_code, cardholder_name, person_id)
VALUES ('1234567890123456', '05/28', '123', 'test card name', '001');