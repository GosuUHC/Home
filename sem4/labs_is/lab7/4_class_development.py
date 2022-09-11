

def num14():

    class User:
        def __init__(self, login, password):
            self.login = login
            self.password = password

        def __str__(self):
            return f"User \"{self.login}\""

    class Server:
        def __init__(self):
            self.user_mails = {}  # key = user, value = [message1, message2, ]

        def add_user(self, user: User):
            if f"{user}" not in self.user_mails.keys():
                self.user_mails[f"{user}"] = []
                print("Added " + f"{user}")
                return
            print("User already added")

        def add_mail(self, message, user: User):
            if not self.check_user(user):
                print(f"{user} not connected to server")
                return

            self.user_mails[f"{user}"].append(message)
            print(f"Message for {user} added")

        def pop_mail(self, user):
            if not self.check_user(user):
                return

            if len(self.user_mails[f"{user}"]) == 0:
                print("No mails")
                return

            return self.user_mails[f"{user}"].pop()

        def check_user(self, user: User):
            if f"{user}" not in self.user_mails.keys():
                print("No such a user")
                return False

            return True

    class MailClient:
        def __init__(self, server: Server, user: User):
            self.server = server
            self.user = user
            self.server.add_user(self.user)

        def receive_mail(self):
            print(f"Message for {self.user} received:",
                  self.server.pop_mail(self.user))

        def send_mail(self, server: Server, user: User, message):
            server.add_mail(message, user)

    user1 = User("user1", "12345")
    user2 = User("user2", "54321")
    server1 = Server()

    client1 = MailClient(server1, user1)
    client1.receive_mail()
    client1.send_mail(server1, user2, "Hello user2 from user1!")

    client2 = MailClient(server1, user2)
    client2.receive_mail()
    print()
    client2.send_mail(server1, user1, "Hello user1 from user2")

    client1.receive_mail()
    client1.send_mail(server1, user2, "Hello again user2 from user1")
    client1.send_mail(server1, user2, "Goodbye user2 from user1")

    client2.receive_mail()
    client2.receive_mail()

    print()
    print()
    server2 = Server()

    client1.receive_mail()
    client1.send_mail(server2, user2, "Hello user2 from user1 on server2!")
    client2.receive_mail()
    print()
    client2.send_mail(server2, user1, "Hello user1 from user2 on server2")
    client1.receive_mail()
    client1.send_mail(
        server2, user2, "Hello again user2 from user1 on server2")
    client1.send_mail(server2, user2, "Goodbye user2 from user1 on server2")

    client2.receive_mail()
    client2.receive_mail()


def num15():
    class Commands:

        def __init__(self, values: str):
            self.values = [int(value) for value in values.split()]

        def make_negative(self):
            self.values = list(
                map(lambda x: -x if x > 0 else x, self.values))

        def square(self):
            self.values = list(map(lambda x: x**2, self.values))

        def strange_command(self):
            self.values = list(
                map(lambda x: x + 1 if x % 5 == 0 else x, self.values))

        def enter_commands(self):
            for _ in range(int(input("How many commands:"))):
                command = input("Enter command:")
                if command in self.commands_dict:
                    Commands.commands_dict[command](self)

        commands_dict = {"strange_command": strange_command,
                         "make_negative": make_negative,
                         "square": square}

    commands = Commands("1 0 -2 30 -4")
    commands.enter_commands()
    print(commands.values)

    commands2 = Commands("1 5 -2 30 -4")
    commands2.enter_commands()
    print(commands2.values)

    commands3 = Commands("1 5 -2 30 -4")
    commands3.enter_commands()
    print(commands3.values)

num15()


