INSERT INTO poll (poll_id, question) VALUES (1, 'What is your favourite color ?');

INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (1, 'Blue', 1);
INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (2, 'Red', 1);
INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (3, 'Yellow', 1);


INSERT INTO poll (poll_id, question) VALUES (2, 'Do you love anime ?');

INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (4, 'Yes', 2);
INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (5, 'No', 2);
INSERT INTO poll_option (option_id, option_value, poll_id) VALUES (6, 'Sometimes', 2);


INSERT INTO vote (vote_id, option_id) VALUES(1, 1);
INSERT INTO vote (vote_id, option_id) VALUES(2, 2);
INSERT INTO vote (vote_id, option_id) VALUES(3, 2);


INSERT INTO vote (vote_id, option_id) VALUES(4, 4);
INSERT INTO vote (vote_id, option_id) VALUES(5, 4);
INSERT INTO vote (vote_id, option_id) VALUES(6, 6);
