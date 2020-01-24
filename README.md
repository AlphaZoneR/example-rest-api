# Rest api android example

## Backend

A backenden egy Spring boot applikacio talalhato, amit a spring-boot plugin run command segitsegevel lehet elinditani
Elinditasa utan a http://localhost:5000 URL lehet elerni
Jelenleg egy entitas-t tud szolgaltatni: Task

- all tasks: `GET /tasks`
- task by id: `GET /tasks/{id}`
- create task: `POST /tasks + body`
- update task: `PUT /tasks + body`
- delete task: `DELETE /tasks + body`

Ezeket a funkcionallitasokat a `TaskController.java`-ban talalhatjuk.
Mindig mikor lekerunk egy endpointot, van egy Log is.

## Frontend

Frontended egy applikacionk van, amely jelenleg listazza a backendrol lekert taskokat.
Vigyazat, ki kell javitani az *IP-cimet* a sajat IP-cimre a `ServiceManager.java` file-ban.


