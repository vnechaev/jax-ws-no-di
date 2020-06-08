CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE demodb.balance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    balance Decimal(19,4) DEFAULT 0
);

insert into demodb.balance (login, balance) values ('mylogin', 100);
select * from demodb.balance;
select balance from demodb.balance where login = ?;
insert into demodb.users values (?, ?, ?)

select case
when login is null then 'error1'
when login is not null then insert into demodb.users values (null, 'mylogin', 'mypass')
end
from demodb.users as users
where users.login = 'mylogin';



SELECT COUNT(1)
FROM demodb.users
WHERE login = ?

SELECT COUNT(1) FROM demodb.users WHERE login = 'mylogin'

