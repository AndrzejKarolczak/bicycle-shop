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
VALUES('j.ospaly', '$2y$12$97VD1ZbRZqv4CKA2mO7.r.3H7ywLL0SVJxUON.GmjCZYjxZ2u2pLa', 1);

INSERT INTO accounts(login, password, group_id)
VALUES('t.wyrobnik', '$2y$12$540T3gKGe3MeT8H/sKhqu.tzmRUgj09s7dGLtsnWpjrXlV8v9BeMa', 2);

INSERT INTO accounts(login, password, group_id)
VALUES('e.niebezpieczna', '$2y$12$dJdwSTRDoz698Zbxiq3mGuTTLsqXBxrXct0uFf0FBtArF34kvlpbe', 3);



--UPDATE accounts
--SET 	is_account_non_expired = true,
--    	is_account_non_locked = true,
--        is_credentials_non_expired = true,
--        is_enabled = true
--WHERE user_name IN ('j.ospaly', 'e.niebezpieczna', 't.wyrobnik2');

UPDATE accounts
SET 	is_account_non_expired = true,
    	is_account_non_locked = true,
        is_credentials_non_expired = true,
        is_enabled = true
WHERE login IN ('j.ospaly', 'e.niebezpieczna', 't.wyrobnik');
