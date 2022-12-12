
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('raumerbas','2',TRUE,'top1delmundo@email.com',100,28,27,1);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('gonriblun','1',TRUE,'top2delmundo@email.com',100,25,25,0);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('lucantdel','soysemigod',TRUE,'top3delmundo@email.com',30,28,24,4);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('dancorfon','soysemigod',TRUE,'top4delmundo@email.com',150,24,21,3);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('jaigargar1','soysemigod',TRUE,'top5delmundo@email.com',80,25,20,5);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('player1','soysemigod',TRUE,'top5delmundo@email.com',100,25,20,5);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('player2','soysemigod',TRUE,'player2@email.com',0,0,0,0);

INSERT INTO USERS_FRIENDS VALUES ('raumerbas', 'dancorfon');
INSERT INTO USERS_FRIENDS VALUES ('raumerbas', 'gonriblun');
INSERT INTO USERS_FRIENDS VALUES ('gonriblun', 'raumerbas');
INSERT INTO USERS_FRIENDS VALUES ('dancorfon', 'raumerbas');

INSERT INTO invitacion_amigo(id,username1,username2) VALUES (0, 'dancorfon', 'player1');
INSERT INTO invitacion_amigo(id,username1,username2) VALUES (1, 'player2', 'dancorfon');

INSERT INTO users_friends(user_username, friends_username) VALUES ('dancorfon', 'raumerbas');
INSERT INTO users_friends(user_username, friends_username) VALUES ('dancorfon', 'gonriblun');

INSERT INTO authorities(id,username,authority) VALUES (1,'raumerbas','admin');
INSERT INTO authorities(id,username,authority) VALUES (2,'gonriblun','admin');
INSERT INTO authorities(id,username,authority) VALUES (3,'lucantdel','admin');
INSERT INTO authorities(id,username,authority) VALUES (4,'dancorfon','admin');
INSERT INTO authorities(id,username,authority) VALUES (5,'jaigargar1','admin');
INSERT INTO authorities(id,username,authority) VALUES (6,'player1','player');
INSERT INTO authorities(id,username,authority) VALUES (7,'player2','player');


INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'raumerbas');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'raumerbas');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'raumerbas');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'raumerbas');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'raumerbas');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'raumerbas');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'raumerbas');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'raumerbas');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'raumerbas');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'raumerbas');
INSERT INTO owners VALUES (12, 'Raul', 'Hernan', 'Ecija', 'Sevilla', '6085558767', 'raumerbas');
INSERT INTO owners VALUES (11, 'Gonzalo', 'Ribas', 'Espartinas', 'Sevilla', '6418176718', 'gonriblun');
INSERT INTO owners VALUES (13, 'Lucas', 'Antonanzas', 'Los Bermejales', 'Sevilla', '6013123720', 'raumerbas');
INSERT INTO owners VALUES (14, 'Daniel', 'Cortes', 'Tomares', 'Sevilla', '6714685968', 'raumerbas');
INSERT INTO owners VALUES (15, 'Jaime', 'Garcia', 'Santa Justa', 'Sevilla', '6714435395', 'raumerbas');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Hasbulla', '2015-04-05', 1, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Toby', '2013-04-01', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Roco', '2018-03-20', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Danie', '2019-08-03', 6, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Drogon', '2006-04-10', 3, 15);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO colours(id,name) VALUES (1, 'red');
INSERT INTO colours(id,name) VALUES (2, 'blue');
INSERT INTO colours(id,name) VALUES (3, 'yellow');


INSERT INTO token_types(id,name) VALUES (1, 'bacterium');
INSERT INTO token_types(id,name) VALUES (2, 'sarcina');


INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (1, TRUE , 1 , 0,0,0,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (2, FALSE , 2 , 0,0,0,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (3, FALSE , 2 , 0,0,0,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (4, FALSE , 2 , 0,0,0,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (5, FALSE , 2 , 0,0,0,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (6, FALSE , 2 , 0,0,0,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (7, FALSE , 2 , 0,0,0,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (8, FALSE , 2 , 0,0,0,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (9, FALSE , 2 , 0,0,0,0,'lucantdel');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (10, FALSE , 2 , 0,0,0,0,'dancorfon');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,points,user_id) VALUES (11, FALSE , 2 , 0,0,0,0,'jaigargar1');


INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (1,'A', 2, 15, 'B', 2, 1, 2, TRUE, TRUE, 'CoDE1');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (2,'B', 3, 30, 'A', 2, 3, 4, TRUE, FALSE, 'CoDE2');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (3,'A', 4, 25, 'B', 2, 5, 6, FALSE, TRUE, 'CoDE3');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (4,'B', 1, 45, 'A', 2, 7, 8, FALSE, FALSE, 'CoDE4');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (5,'B', 1, 45, 'A', 2, 9, null, TRUE, TRUE, 'CoDE5');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (6,'B', 1, 45, 'A', 2, 10, null, TRUE, TRUE, 'CoDE6');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (7,'B', 1, 45, 'A', 2, 11, null, TRUE, TRUE, 'CoDE7');


INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (1, 1, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (2, 2, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (3, 3, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (4, 4, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (5, 5, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (6, 6, 0,0,0,0,1);
INSERT INTO spaces(id, position, num_black_bacteria, num_red_bacteria, num_black_sarcinas, num_red_sarcinas, game_id) VALUES (7, 7, 0,0,0,0,1);


INSERT INTO statistics(id, init_time, end_time, final_round, winner, used_bacteries, used_sarcines, game_points, game_id) VALUES (1,'13:20:32','13:15:45', 2, TRUE, 16, 2, 13, 1);



INSERT INTO petris_board(id,height,width,background,game_id) VALUES (1, 1000, 1000,'../resources/images/fondo.png', 1);


INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (1, 1, 1, 3, 1, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (2, 1, 1, 3, 2, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (3, 1, 1, 3, 3, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (4, 1, 1, 3, 4, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (5, 1, 2, 3, 1, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (6, 1, 2, 3, 2, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (7, 1, 2, 3, 3, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (8, 1, 2, 3, 4, 1, 1, FALSE);

INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (9, 1, 1, 7, 1, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (10, 1, 1, 7, 2, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (11, 1, 1, 7, 3, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (12, 1, 1, 7, 4, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (13, 1, 2, 7, 1, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (14, 1, 2, 7, 2, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (15, 1, 2, 7, 3, 1, 1, FALSE);
INSERT INTO tokens(id, token_type_id, colour_id, space_id, position_in_space, player_id, petris_board_id, has_been_used) VALUES (16, 1, 2, 7, 4, 1, 1, FALSE);

INSERT INTO achievement(id,name,description,threshold,badge_image,metric) VALUES (1,'Petriplayer','¡Si juegas <THRESHOLD> partidas o más, consideramos que ya eres todo un campeón!',3.0,'https://bit.ly/proGamer','GAMES_PLAYED');
INSERT INTO achievement(id,name,description,threshold,badge_image, metric) VALUES (2,'Proplayer','¡Si ganas <THRESHOLD> o más partidas es que eres todo un pro en esto!',20.0,'https://bit.ly/proGamer', 'VICTORIES');
