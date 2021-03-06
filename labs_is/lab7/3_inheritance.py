
from cmath import sqrt
from numpy import real


def num9():
    class Triangle:
        def __init__(self, a, b, c):
            self.a = a
            self.b = b
            self.c = c

        def perimeter(self):
            return self.a + self.b + self.c

    class EquiliteralTriangle(Triangle):
        def __init__(self, a):
            super().__init__(a, a, a)

    print(Triangle(5, 6, 7).perimeter())
    print(EquiliteralTriangle(5).perimeter())


def num10():
    class Summator:

        def transform(self, n):
            return n

        def sum(self, n):
            return sum([self.transform(i) for i in range(1, n + 1)])

    class SquareSummator(Summator):

        def transform(self, n):
            return n**2

    class CubeSummator(Summator):

        def transform(self, n):
            return n**3

    print(Summator().sum(5))
    print(SquareSummator().sum(5))
    print(CubeSummator().sum(5))


def num11():
    class Summator:

        def transform(self, n):
            return n

        def sum(self, n):
            return sum([self.transform(i) for i in range(1, n + 1)])

    class PowerSummator(Summator):

        def __init__(self, b):
            self.b = b

        def transform(self, n):
            return n**self.b

    class SquareSummator(PowerSummator):
        def __init__(self):
            super().__init__(2)

    class CubeSummator(PowerSummator):
        def __init__(self):
            super().__init__(3)

    print(PowerSummator(2.5).sum(2))
    print(SquareSummator().sum(2))
    print(CubeSummator().sum(2))


def num12():
    class A:

        def __str__(self):
            return "A.__str__method"

        def hello(self):
            print("Hello")

    class B:

        def __str__(self):
            return "B.__str__method"

        def good_evening(self):
            print("Good evening")

    class C(A, B):
        pass

    class D(B, A):
        pass

    c = C()
    c.hello()
    c.good_evening()
    d = D()
    c.hello()
    d.good_evening()
    print(c)
    print(d)


def num13():

    class BaseCharacter:

        def __init__(self, pos_x, pos_y, hp):
            self.pos_x = pos_x
            self.pos_y = pos_y
            self.hp = hp
            self.alive = True

        def move(self, delta_x, delta_y):
            if self.alive:
                self.pos_x += delta_x
                self.pos_y += delta_y

        def is_alive(self):
            if self.hp <= 0:
                self.alive = False
            return self.alive

        def get_damage(self, amount):
            if self.alive:
                self.hp -= amount

        def get_coords(self):
            if self.alive:
                return self.pos_x, self.pos_y

    class Weapon:

        def __init__(self, name, damage, range):
            self.name = name
            self.damage = damage
            self.range = range

        def hit(self, actor: BaseCharacter, target: BaseCharacter):
            if not target.is_alive():
                print("???????? ?????? ????????????????")
                return

            act_coords = actor.get_coords()
            targ_coords = target.get_coords()

            if self.range < (real(sqrt((targ_coords[0] - act_coords[0])**2 + (targ_coords[1] - act_coords[1])**2))):
                print(f"???????? ?????????????? ???????????? ?????? ???????????? {self.name}")
                return

            target.get_damage(self.damage)
            print(
                f"?????????? ?????????????? ???????? ?????????????? {self.name} ?? ?????????????? {self.damage}")

        def __str__(self):
            return str(self.name)

    class BaseEnemy(BaseCharacter):

        def __init__(self, pos_x, pos_y, weapon: Weapon, hp):
            super().__init__(pos_x, pos_y, hp)
            self.weapon = weapon

        def hit(self, target):
            if not isinstance(target, MainHero):
                print("???????? ?????????????? ???????????? ???????????????? ??????????")
                return
            target.get_damage(self.weapon.damage)

        def __str__(self):
            return f"???????? ???? ?????????????? ({self.pos_x}, {self.pos_y}) \
                    ?? ?????????????? {self.weapon}"

    class MainHero(BaseCharacter):

        def __init__(self, pos_x, pos_y, hp, name):
            super().__init__(pos_x, pos_y, hp)
            self.name = name

        def hit(self, target: BaseEnemy):
            if not hasattr(self, "weapon"):
                print("?? ??????????????????")
                return

            if not isinstance(target, BaseEnemy):
                print("???????? ?????????????? ???????????? ??????????")
                return
            self.weapon.hit(self, target)

        def add_weapon(self, weapon: Weapon):
            print(f"???????????????? {weapon}")

            if not isinstance(weapon, Weapon):
                print("?????? ???? ????????????")
                return
            if not hasattr(self, "inventory"):
                self.inventory = []

            self.inventory.append(weapon)

            if len(self.inventory) == 1:
                self.weapon = weapon

        def next_weapon(self):
            if not hasattr(self, "inventory"):
                print("?? ??????????????????")
                return

            if len(self.inventory) == 0:
                print("?? ??????????????????")
                return

            if len(self.inventory) == 1:
                print("?? ???????? ???????????? ???????? ????????????")
                return

            cur_weap_ind = self.inventory.index(self.weapon)
            self.weapon = self.inventory[cur_weap_ind + 1]
            print(f"???????????? ???????????? ???? {self.weapon}")

        def heal(self, amount):
            self.hp += amount
            if self.hp > 200:
                self.hp = 200
            print(f"??????????????????, ???????????? ????????????????{self.hp}")

    weapon1 = Weapon("???????????????? ??????", 5, 1)
    weapon2 = Weapon("?????????????? ??????", 7, 2)
    weapon3 = Weapon("??????", 3, 10)
    weapon4 = Weapon("???????????????? ?????????????????????? ??????????", 1000, 1000)

    princess = BaseCharacter(100, 100, 100)
    archer = BaseEnemy(50, 50, weapon3, 100)
    armored_swordsman = BaseEnemy(10, 10, weapon2, 500)

    archer.hit(armored_swordsman)
    armored_swordsman.move(10, 10)
    print(armored_swordsman.get_coords())

    main_hero = MainHero(0, 0, "???????????? ??????????", 200)
    
    main_hero.hit(armored_swordsman)
    main_hero.next_weapon()
    main_hero.add_weapon(weapon1)
    main_hero.hit(armored_swordsman)
    main_hero.add_weapon(weapon4)
    main_hero.hit(armored_swordsman)
    main_hero.next_weapon()
    main_hero.hit(princess)
    main_hero.hit(armored_swordsman)
    main_hero.hit(armored_swordsman)

