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