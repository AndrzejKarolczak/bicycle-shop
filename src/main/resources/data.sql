INSERT INTO authority_groups(group_id, group_name) VALUES(1, 'ADMINISTRATORS');
INSERT INTO authority_groups(group_id, group_name) VALUES(2, 'EMPLOYEES');
INSERT INTO authority_groups(group_id, group_name) VALUES(3, 'CLIENTS');

INSERT INTO authorities(authority_id, authority_name) VALUES(1, 'ROLE_ADMINISTRATOR');
INSERT INTO authorities(authority_id, authority_name) VALUES(2, 'ROLE_EMPLOYEE');
INSERT INTO authorities(authority_id, authority_name) VALUES(3, 'ROLE_CLIENT');

INSERT INTO authority_in_group(group_id, authority_id) VALUES(1, 1);
INSERT INTO authority_in_group(group_id, authority_id) VALUES(1, 2);
INSERT INTO authority_in_group(group_id, authority_id) VALUES(1, 3);

INSERT INTO authority_in_group(group_id, authority_id) VALUES(2, 2);
INSERT INTO authority_in_group(group_id, authority_id) VALUES(2, 3);
INSERT INTO authority_in_group(group_id, authority_id) VALUES(3, 3);

INSERT INTO accounts(login, password, group_id)
VALUES('j.ospaly', '$2y$12$YYI5qR0qR6M6PewVZul2wuwWQOdqOj051AfFqR0MDkwTYVRnf97pu', 1);

INSERT INTO accounts(login, password, group_id)
VALUES('t.wyrobnik', '$2y$12$D5V0kjxr0E9uke6LD38eSOFYcUaBARQ6h73FXAuUzl0Z.XBREy7eW', 2);

INSERT INTO accounts(login, password, group_id)
VALUES('e.niebezpieczna', '$2y$12$QTqD1JdWnggxJiG5SMxHguQvVZPGgutB/UnHzTlrzbbyxfk0nQ4GK', 3);

UPDATE accounts
SET 	is_account_non_expired = true,
    	is_account_non_locked = true,
        is_credentials_non_expired = true,
        is_enabled = true
WHERE login IN ('j.ospaly', 'e.niebezpieczna', 't.wyrobnik');
