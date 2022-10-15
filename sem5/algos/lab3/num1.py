class Node:
    def __init__(self, initdata):
        self.data = initdata
        self.next = None

    def getData(self):
        return self.data

    def getNext(self):
        return self.next

    def setData(self, newdata):
        self.data = newdata

    def setNext(self, newnext):
        self.next = newnext


class UnorderedList:

    def __init__(self):
        self.head = None

    def isEmpty(self):
        return self.head == None

    def add(self, item):
        temp = Node(item)
        temp.setNext(self.head)
        self.head = temp

    def size(self):
        current = self.head
        count = 0
        while current != None:
            count = count + 1
            current = current.getNext()

        return count

    def search(self, item):
        current = self.head
        found = False
        while current != None and not found:
            if current.getData() == item:
                found = True
            else:
                current = current.getNext()

        return found

    def remove(self, item):
        current = self.head
        previous = None
        found = False
        while not found:
            if current.getData() == item:
                found = True
            else:
                previous = current
                current = current.getNext()

        if previous == None:
            self.head = current.getNext()
        else:
            previous.setNext(current.getNext())

    def append(self, item):
        current = self.head
        if not current:
            self.head = Node(item)
            return

        while current.getNext():
            current = current.getNext()

        current.setNext(Node(item))

    def index(self, item):
        current = self.head
        index = 0
        while current != None:
            if current.getData() == item:
                return index
            else:
                current = current.getNext()
                index += 1

        return None

    def insert(self, pos, item):
        current = self.head
        ind = 0

        while ind < pos - 1:
            ind += 1
            if current:
                current = current.getNext()

        next = current.getNext()
        insert_value = Node(item)
        insert_value.setNext(next)
        current.setNext(insert_value)

    def pop(self, pos=-1):
        index = 0
        current = self.head
        previous = None

        if pos == 0:
            previous = self.head
            self.head = self.head.getNext()
            return previous.getData()

        if pos == -1:
            while current:
                if current.getNext() == None:
                    previous.setNext(current.getNext())

                previous = current
                current = current.getNext()

            return previous.getData()

        while index < pos:
            index += 1
            previous = current
            current = current.getNext()

        previous.setNext(current.getNext())
        return current.getData()

    def slice(self, start, stop):
        current = self.head
        index = 0
        new_list = UnorderedList()
        while index < start:
            index += 1
            current = current.getNext()

        new_list.head = Node(current.getData())

        temp = new_list.head

        while start < stop - 1:
            start += 1
            current = current.getNext()
            if not current:
                break

            next = Node(current.getData())

            temp.setNext(next)
            temp = temp.getNext()

        temp.setNext(None)

        return new_list

    def __str__(self):
        return_string = "["
        current = self.head

        while True:
            return_string += str(current.getData())
            current = current.getNext()

            if not current:
                break

            return_string += ", "

        return return_string + "]"


if __name__ == "__main__":

    mylist = UnorderedList()

    mylist.add(31)
    mylist.add(77)
    mylist.add(17)
    mylist.add(93)
    mylist.add(26)
    mylist.add(54)
    mylist.add(100)

    print("list:")
    print(mylist)

    print("index 100:")
    print(mylist.index(100))

    print("list:")
    print(mylist)

    print("append 111:")
    mylist.append(111)
    print(mylist)

    print("index 100")
    print(mylist.index(100))

    print("index 77")
    print(mylist.index(77))

    mylist.insert(1, 1111)
    print("insert 1111 on pos 1:")
    print(mylist)

    mylist.insert(4, 4444)
    print("insert 4444 on pos 4:")
    print(mylist)

    print("pop():")
    print(mylist.pop())
    print(mylist)

    print("pop(2):")
    print(mylist.pop(2))
    print(mylist)

    print("pop(0):")
    print(mylist.pop(0))
    print(mylist)

    print("orig list:")
    print(mylist)

    print("slice(1, 3):")
    print(mylist.slice(1, 3))

    print("slice(0, 3):")
    print(mylist.slice(0, 3))

    print("slice(2, 3):")
    print(mylist.slice(2, 3))

    print("slice(1, 5):")
    print(mylist.slice(1, 5))

    print("slice(1, 13):")
    print(mylist.slice(1, 13))
