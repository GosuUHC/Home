from num1 import UnorderedList
from list_collections import Stack as StackList, Queue as QueueList, Deque as DequeList


class StackUnorderedList:
    def __init__(self):
        self.items = UnorderedList()

    def isEmpty(self):
        return self.items.isEmpty()

    def push(self, item):
        self.items.add(item)

    def pop(self):
        return self.items.pop(0)

    def peek(self):
        return self.items.head.getData()

    def size(self):
        return self.items.size()

    def __str__(self):
        return "Stack: \n" + self.items.__str__()


class QueueUnorderedList:
    def __init__(self):
        self.items = UnorderedList()

    def enqueue(self, item):
        self.items.add(item)

    def dequeue(self):
        self.items.pop()

    def isEmpty(self):
        return self.items.isEmpty()

    def size(self):
        return self.items.size()

    def __str__(self):
        return "Queue: \n" + self.items.__str__()


class DequeUnorderedList:
    def __init__(self):
        self.items = UnorderedList()

    def addFront(self, item):
        self.items.add(item)

    def addRear(self, item):
        self.items.append(item)

    def removeFront(self):
        return self.items.pop(0)

    def removeRear(self):
        return self.items.pop()

    def isEmpty(self):
        return self.items.isEmpty()

    def size(self):
        return self.items.size()

    def __str__(self):
        return "Deque: \n" + self.items.__str__()


if __name__ == "__main__":
    # STACK TEST
    print("STACK")
    stack = StackUnorderedList()

    print("isEmpty():")
    print(stack.isEmpty())

    for i in (1, 2, 3):
        print(f"push({i})")
        stack.push(i)
        print(stack)

    print("pop():")
    print(stack.pop())
    print(stack)

    print("peek():")
    print(stack.peek())
    print(stack)

    # QUEUE TEST
    print("QUEUE")
    q = QueueUnorderedList()

    print("isEmpty():")
    print(q.isEmpty())

    for i in (1, 2, 3):
        print(f"enqueue({i})")
        q.enqueue(i)
        print(q)

    print("dequeue():")
    q.dequeue()
    print(q)

    print("dequeue():")
    q.dequeue()
    print(q)

    # DEQUE TEST
    print("DEQUE")
    deque = DequeUnorderedList()

    print("isEmpty():")
    print(deque.isEmpty())

    for i in range(1, 4):
        print(f"addRear({i})")
        deque.addRear(i)
        print(deque)

    for i in range(4, 7):
        print(f"addFront({i})")
        deque.addFront(i)
        print(deque)

    print("removeFront():")
    print(deque.removeFront())
    print(deque)

    print("removeRear():")
    print(deque.removeRear())
    print(deque)
