from num1 import UnorderedList, Node


def func(head: Node):

    tail = None

    while head:
        temp = head.getNext()
        head.setNext(tail)
        tail = head
        head = temp

    head = tail

    return head


if __name__ == "__main__":

    mylist = UnorderedList()

    mylist.add(100)
    mylist.add(200)
    mylist.add(300)
    print("orig list:")
    print(mylist)

    new_head = func(mylist.head)

    mylist2 = UnorderedList()
    mylist2.head = new_head
    print("new list:")
    print(mylist2)
