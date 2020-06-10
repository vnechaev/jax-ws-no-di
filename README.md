# jax-ws-no-di
WEB сервис, который слушает определенный URL, и принимает на него XML запросы,
отправленные в теле POST запроса.

Есть два вида сообщений:  
**1.Регистрация нового клиента**
```xml
<?xml version="1.0" encoding="utf-8"?>  
<request>  
<request-type>CREATE-AGT</request-type>    
<extra name="login">123456</extra>  
<extra name="password">pwd</extra>  
</request>
```
  
При этом сервер:
1. проверяет, что клиент с номером 123456 еще не зарегистрирован
2. регистрирует этого клиента в системе с нулевым балансом.
Ответное сообщение:
```xml
<?xml version="1.0" encoding="utf-8"?>
<response>
<result-code>0</result-code>
</response>
```

Возможные коды ошибок:  
0 — все ОК.  
1 — пользователь с таким логином уже существует  
2 — техническая ошибка

**2.Запрос клиентом своего баланса**
```xml  
<?xml version="1.0" encoding="utf-8"?>
<request>
<request-type>GET-BALANCE</request-type>
<extra name="login">123456</extra>
<extra name="password">pwd</extra>
</request>
```
Сервер сверяет логин и пароль, и если они верны возвращает баланс агента:
```xml
<?xml version="1.0" encoding="utf-8"?>
<response>
<result-code>0</result-code>
<extra name="balance">100.00</extra>
</response>
```
Возможные коды ошибок:  
0 — все ОК  
2 — техническая ошибка  
3 — пользователь не существует  
4 — пароль не верен  

Ps. Не использовать ORM и DI фреймворков. Приложение должно поддерживать возможность запуска на
нескольких серверах одновременно.
