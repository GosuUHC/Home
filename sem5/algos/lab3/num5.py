class Node:
    def __init__(self, initdata):
        self.data = initdata
        self.next = None
        self.prev = None

    def getData(self):
        return self.data

    def getNext(self):
        return self.next

    def getPrev(self):
        return self.prev

    def setData(self, newdata):
        self.data = newdata

    def setNext(self, newnext):
        self.next = newnext

    def setPrev(self, newprev):
        self.prev = newprev


class DoubleList:

    def __init__(self):
        self.head = None

    def isEmpty(self):
        if self.head:
            return False

        return True

    def search(self, item):
        current = self.head
        found = False
        while current != None and not found:
            if current.getData() == item:
                found = True
            else:
                current = current.getNext()

        return found

    def size(self):
        size = 0
        if self.head == None:
            return size

        current = self.head
        while current:
            size += 1
            current = current.getNext()

        return size

    def addFront(self, item):
        temp = Node(item)
        temp.setNext(self.head)
        if self.head:
            self.head.setPrev(temp)

        self.head = temp

    def append(self, item):
        current = self.head
        if current == None:
            self.addFront(item)
            return

        while current.getNext():
            current = current.getNext()

        temp = Node(item)
        current.setNext(temp)
        temp.setPrev(current)

    def popFirst(self):
        if self.head == None:
            return self.head

        head_data = self.head.getData()
        self.head = self.head.getNext()
        return head_data

    def popLast(self):
        if self.head == None:
            return self.head

        current = self.head
        while current.getNext():
            current = current.getNext()

        last_data = current.getData()
        current.getPrev().setNext(None)

        return last_data

    def insert_before(self, item, pos):
        if pos == 0:
            self.addFront(item)
            return

        temp = Node(item)
        current = self.head
        index = 0
        while index < pos:
            index += 1
            if current.getNext():
                current = current.getNext()

        prev = current.getPrev()

        prev.setNext(temp)
        temp.setNext(current)

    def insert_after(self, item, pos):
        temp = Node(item)
        current = self.head
        index = 0
        while index < pos:
            index += 1
            if current.getNext():
                current = current.getNext()

        next = current.getNext()
        current.setNext(temp)
        temp.setNext(next)

    def __str__(self):
        return_string = "["
        current = self.head
        if current == None:
            return "[]"

        while True:
            return_string += str(current.getData())
            current = current.getNext()
            if current == None:
                break

            return_string += ", "

        return return_string + "]"


if __name__ == "__main__":
    mylist = DoubleList()
    print("isEmpty()")
    print(mylist.isEmpty())

    print("addFront(1)")
    mylist.addFront(1)
    print(mylist)

    print("addFront(2)")
    mylist.addFront(2)
    print(mylist)

    print("append(10)")
    mylist.append(10)
    print(mylist)

    print("append(20)")
    mylist.append(20)
    print(mylist)

    print("isEmpty()")
    print(mylist.isEmpty())

    print("search(100)")
    print(mylist.search(100))

    print("search(2)")
    print(mylist.search(2))

    print("size()")
    print(mylist.size())

    print("popFirst()")
    print(mylist.popFirst())
    print(mylist)

    print("popLast()")
    print(mylist.popLast())
    print(mylist)

    print("insert_before(100, 1)")
    mylist.insert_before(100, 1)
    print(mylist)

    print("insert_before(200, 0)")
    mylist.insert_before(200, 0)
    print(mylist)

    print("insert_after(300, 1)")
    mylist.insert_after(300, 1)
    print(mylist)

    print("insert_after(400, 0)")
    mylist.insert_after(400, 0)
    print(mylist)
