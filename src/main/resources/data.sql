INSERT INTO authdb.permission (id, name) VALUES
 (1,'create_account'),
 (2,'create_lead'),
 (3,'create_opportunity'),
 (4,'create_event'),
 
 (5,'read_account'),
 (6,'read_lead'),
 (7,'read_opportunity'),
 (8,'read_event'),
 
 (9,'update_account'),
 (10,'update_lead'),
 (11,'update_opportunity'),
 (12,'update_event'),
 
 (13,'delete_account'),
 (14,'delete_lead'),
 (15,'delete_opportunity');
 (16,'delete_event'),
 

INSERT INTO authdb.role  (id,name) VALUES
    (1,'ROLE_superAdmin'),
    (2,'ROLE_admin'),
    (3,'ROLE_modirators'),
    (4,'ROLE_user');

INSERT INTO authdb.permission_role (permission_id, role_id) VALUES
     (1,1),  /*superAdmin */
     (2,1),  /*superAdmin */
     (3,1),  /*superAdmin */
     (4,1),  /*superAdmin */
     (5,1),  /*superAdmin */
     (6,1),  /*superAdmin */
     (7,1),  /*superAdmin */
     (8,1),  /*superAdmin */
     (9,1),  /*superAdmin */
     (10,1),  /*superAdmin */
     (11,1),  /*superAdmin */
     (12,1);  /*superAdmin */
     (13,1);  /*superAdmin */
     (14,1);  /*superAdmin */
     (15,1);  /*superAdmin */
     (16,1);  /*superAdmin */

INSERT INTO authdb.user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES 
    ('1', 'batman','{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'batman@gmail.com', '1', '1', '1', '1');

--  /*
--  passowrds:
--  batman - kpass
--  */

INSERT INTO authdb.role_user (ROLE_ID, USER_ID)
    VALUES
    (1, 1) /* batman-admin */;

-- INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/login', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

