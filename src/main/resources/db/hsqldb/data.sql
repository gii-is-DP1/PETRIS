
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('raumerbas','2',TRUE,'top1delmundo@email.com',100,28,27,1);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('gonriblun','1',TRUE,'top2delmundo@email.com',100,25,25,0);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('lucantdel','soysemigod',TRUE,'top3delmundo@email.com',30,28,24,4);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('dancorfon','soysemigod',TRUE,'top4delmundo@email.com',150,24,21,3);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('jaigargar1','soysemigod',TRUE,'top5delmundo@email.com',0,2,0,2);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('user1','soysemigod',TRUE,'top5delmundo@email.com',100,25,20,5);
INSERT INTO users(username,password,enabled,email,points,played_games,won_games,lost_games) VALUES ('user2','soysemigod',TRUE,'player2@email.com',0,0,0,0);



INSERT INTO USERS_FRIENDS VALUES ('raumerbas', 'dancorfon');
INSERT INTO USERS_FRIENDS VALUES ('raumerbas', 'gonriblun');
INSERT INTO USERS_FRIENDS VALUES ('gonriblun', 'raumerbas');
INSERT INTO USERS_FRIENDS VALUES ('dancorfon', 'raumerbas');

INSERT INTO invitacion_amigo(id,username1,username2) VALUES (0, 'dancorfon', 'user1');
INSERT INTO invitacion_amigo(id,username1,username2) VALUES (1, 'user2', 'dancorfon');

INSERT INTO users_friends(user_username, friends_username) VALUES ('dancorfon', 'raumerbas');
INSERT INTO users_friends(user_username, friends_username) VALUES ('dancorfon', 'gonriblun');

INSERT INTO authorities(id,username,authority) VALUES (1,'raumerbas','admin');
INSERT INTO authorities(id,username,authority) VALUES (2,'gonriblun','admin');
INSERT INTO authorities(id,username,authority) VALUES (3,'lucantdel','admin');
INSERT INTO authorities(id,username,authority) VALUES (4,'dancorfon','admin');
INSERT INTO authorities(id,username,authority) VALUES (5,'jaigargar1','admin');
INSERT INTO authorities(id,username,authority) VALUES (6,'user1','user');
INSERT INTO authorities(id,username,authority) VALUES (7,'user2','user');

INSERT INTO colours(id,name) VALUES (1, 'red');
INSERT INTO colours(id,name) VALUES (2, 'blue');
INSERT INTO colours(id,name) VALUES (3, 'yellow');

INSERT INTO token_types(id,name) VALUES (1, 'bacterium');
INSERT INTO token_types(id,name) VALUES (2, 'sarcina');

INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (1, TRUE , 1 , 0,0,0,FALSE,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (2, FALSE , 2 , 0,0,0,FALSE,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (3, FALSE , 1 , 0,0,0,FALSE,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (4, FALSE , 2 , 0,0,0,FALSE,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (5, FALSE , 1 , 0,0,0,FALSE,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (6, FALSE , 2 , 0,0,0,FALSE,0,'gonriblun');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (7, FALSE , 1 , 0,0,0,FALSE,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (8, FALSE , 2 , 0,0,0,FALSE,0,'raumerbas');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (9, FALSE , 1 , 0,0,0,FALSE,0,'lucantdel');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (10, FALSE , 1 , 0,0,0,FALSE,0,'dancorfon');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (11, FALSE , 1 , 0,0,0,FALSE,0,'jaigargar1');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (12, FALSE , 2 , 0,0,0,FALSE,0,'jaigargar1');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (13, FALSE , 2 , 0,0,0,FALSE,0,'jaigargar1');
INSERT INTO players(id, is_turn , colour_id, used_bacteria, used_sarcinas, contamination_points,has_moved,points,user_id) VALUES (14, FALSE , 2 , 0,0,0,FALSE,0,'jaigargar1');



INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (1,'red', 2, 15, 'blue', 2, 1, 2, TRUE, TRUE, 'CoDE1');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (2,'blue', 3, 30, 'red', 2, 3, 4, TRUE, FALSE, 'CoDE2');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (3,'red', 4, 25, 'B', 2, 5, 6, FALSE, TRUE, 'CoDE3');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (4,'B', 1, 45, 'red', 2, 7, 8, FALSE, FALSE, 'CoDE4');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (5,'B', 1, 45, 'red', 2, 9, 12, TRUE, FALSE, 'CoDE5');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (6,'B', 1, 45, 'red', 2, 10, 13, TRUE, FALSE, 'CoDE6');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (7,'B', 1, 45, 'red', 2, 11, 14, TRUE, FALSE, 'CoDE7');
/*
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (8,'A', 2, 15, 'B', 2, 12, 13, TRUE, TRUE, 'CoDE8');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (9,'A', 2, 15, 'B', 2, 14, 15, TRUE, TRUE, 'CoDE9');
INSERT INTO games(id, loser, round, time, winner, phase, player1_id, player2_id, is_active, is_public, code) VALUES (10,'A', 2, 15, 'B', 2, 16, 17, TRUE, TRUE, 'CoDE10');
*/

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


INSERT INTO chat(id, user_id, game_id, text) VALUES (1, 'raumerbas', 1, 'Aprende a jugar fenómeno, jajajajjaja. Te faltan bacterias');

INSERT INTO USERS_ACHIEVEMENTS VALUES ('raumerbas', 1);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('gonriblun', 1);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('lucantdel', 1);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('user1', 1);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('dancorfon', 1);

INSERT INTO USERS_ACHIEVEMENTS VALUES ('raumerbas', 2);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('gonriblun', 2);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('lucantdel', 2);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('user1', 2);
INSERT INTO USERS_ACHIEVEMENTS VALUES ('dancorfon', 2);