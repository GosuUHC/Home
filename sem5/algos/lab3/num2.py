from num1 import UnorderedList


def move_to_front(string, unord_list: UnorderedList):
    try:
        unord_list.remove(string)
    except:
        pass

    unord_list.add(string)


if __name__ == "__main__":
    mylist = UnorderedList()

    print("move_to_front(1):")
    move_to_front("1", mylist)
    print(mylist)

    print("move_to_front(2):")
    move_to_front("2", mylist)
    print(mylist)

    print("move_to_front(3):")
    move_to_front("3", mylist)
    print(mylist)

    print("move_to_front(1):")
    move_to_front("1", mylist)
    print(mylist)
