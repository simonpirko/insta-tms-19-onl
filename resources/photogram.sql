create table Users (user_id serial  not null primary key unique  , username varchar unique not null, password varchar not null , name varchar not null, photo varchar not null, email varchar );
create table Posts (post_id serial not null primary key unique , user_id  integer not null , image varchar not null , created_at timestamp not null , description varchar, foreign key (user_id) references Users (user_id));
create table Comments (comment_id serial not null primary key unique, message varchar not null , post_id integer not null , user_id integer not null , created_at timestamp not null , foreign key (post_id) references Posts (post_id), foreign key (user_id) references Users (user_id));
create table likes (user_id serial not null , post_id integer not null );
create table followers (parent_id serial not null, child_id integer not null, foreign key (parent_id) references Users(user_id), foreign key (child_id) references Users (user_id));