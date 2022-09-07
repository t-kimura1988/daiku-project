insert into t_accounts (id, family_name, given_name, uid, email, del_flg)
values (nextval('t_accounts_id_seq'), 'test', 'aaaaaa', 'testuid_111111', 'test@aaa.aaa', '0');

insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_1', 'test', 'test', null, null, current_date , current_date , null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_2', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_3', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_4', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_5', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_6', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_7', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_8', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_9', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_10', 'test', 'test', null, null, current_date, current_date, null);
insert into t_goals (id, create_date, account_id, title, purpose, aim, due_date, created_by, created_at, updated_at, updated_by)
values (nextval('t_goals_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'test_title_11', 'test', 'test', null, null, current_date, current_date, null);


insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test1', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test2', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test3', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test4', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test5', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test6', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test7', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test8', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test9', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test10', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test11', '0', '0', null, current_date, current_date, null);
insert into t_goal_archives (id, archives_create_date, goal_id, thoughts, updating_flg, publish, created_by, created_at, updated_at, updated_by)
values (nextval('t_goal_archive_id_seq'), to_char(current_date , 'yyyy-MM-dd'), 1, 'thoughts test12', '0', '0', null, current_date, current_date, null);