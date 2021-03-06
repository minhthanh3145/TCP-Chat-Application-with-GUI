# Multi-threaded TCP Chat-Blog Application

## Prerequisites:
- [Eclipse Neon 3](https://www.eclipse.org/downloads/packages/release/neon/3/eclipse-ide-eclipse-committers)
- [Maven](https://maven.apache.org/download.cgi)

## Development build:
- Clone this repository, then in Eclipse: **File -> Import -> General -> Existing projects into work space**.
- Open **Server.java** in Server project and hit start.
- Open **Main.java** in Client project and hit start.

## Structure of the project:
- **Server project** represents the server, the default local host is *4445*. Starting the server project is equivalent to starting the server. The server handles all information by writing them down to text files. Authentication (sign-in or sign-up) is handled by writing down the registered information and check those against user's input. The server also receives messages sent from the clients by writing them down to local text files. On another thread running parallelly, the server continuously read the local text files, and send the unsent messages, finally marking them as sent. 

- **Client project** represents a client, the default local host is also *4445*. Matching local hosts are essential for communication, as well as the start-up of server before the start-up of any client. Client has two functional components: view and model. View component contains Java classes for displaying shells. Each shell was built from Window Builder (came along with Eclipse) and the controls can listen to events (clicking, hitting enter). Model component contains sender and receiver, whose functionalities are self-explanatory.

## List:
- [X] Sign-up and sign-in.
- [X] Connect and chat to an online user.
- [X] Multi-thread.
- [X] Personal page with avatar upload capability.
