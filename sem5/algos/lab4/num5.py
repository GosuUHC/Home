from secrets import token_hex
from hashlib import scrypt
import sqlite3


def save_pass(login, password):

    salt = token_hex(16)
    print(f"generated {salt = }")
    hashed_pass = scrypt(password=password.encode(),
                         salt=salt.encode(), n=8, r=256, p=4, dklen=16).hex()

    print(f"{hashed_pass = }")

    conn = sqlite3.connect("users.db")
    c = conn.cursor()
    try:
        c.execute("INSERT INTO users (login, salt, hashed_pass) values (?, ?, ?)",
                  (login, salt, hashed_pass))
    except sqlite3.DatabaseError as error:
        print(f"error: {error}")
    print("login, salt, hashed password added")

    conn.commit()
    conn.close()


def check_password(login, password):
    conn = sqlite3.connect("users.db")
    c = conn.cursor()
    try:
        res = c.execute(
            "SELECT salt, hashed_pass from users where login=?", (login, ))
        salt, hashed_password_db = res.fetchone()
    except sqlite3.DatabaseError as error:
        print(f"\nerror: {error}\n")

    print(f"salt from DB = {salt}")
    print(f"hashed password from DB = {hashed_password_db}")

    print("\nhashing password...")
    hashed_password = scrypt(password=password.encode(
    ), salt=salt.encode(), n=8, r=256, p=4, dklen=16).hex()
    print(f"{hashed_password = }")

    print(f"\n{hashed_password_db == hashed_password = }")


def register():
    print("save password")
    login = input("Login:")
    password = input("Password:")
    save_pass(login, password)


def login():
    print("check password")
    login = input("Login:")
    password = input("Password:")
    check_password(login, password)


if __name__ == "__main__":
    actions = {1: register, 2: login}
    action = actions.get(int(input("Actions:\n1. Register \n2. Login\n")))
    action()
