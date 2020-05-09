-- trying out the roll back and commit commands
-- final used sql query to be made in the project
use bookstore;
START transaction;
set autocommit = 0;
update book set copies = copies - 20 where ISBN = 123456;
update book set copies = copies - 20 where ISBN = 123457;
update book set copies = copies - 60 where ISBN = 123458;
commit;
set autocommit = 1;
