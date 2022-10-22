from num4 import StackUnorderedList


def htmlChecker(html):
    stack = StackUnorderedList()
    index = 0
    while index < len(html):
        symbol = html[index]
        if symbol == "<":
            if html[index + 1] != "/":
                while symbol != ">":
                    symbol = html[index]
                    index += 1
                    stack.push(symbol)

            else:
                index += 1
                while symbol != ">":
                    index += 1
                    symbol = html[index]
                    top = stack.peek()
                    if top == symbol:
                        elem = stack.pop()
                        while elem != "<":
                            elem = stack.pop()

        else:
            index += 1

    return stack.isEmpty()


html = """<html>
            <head>
                <title>
                    Example
                </title>
            </head>
            
            <body>
                <h1>Hello, world</h1>
            </body>
          </html>
        """

# missing </html>
html2 = """<html>
            <head>
                <title>
                    Example
                </title>
            </head>
            
            <body>
                <h1>Hello, world</h1>
            </body>
          
        """

print(htmlChecker(html))
print(htmlChecker(html2))
