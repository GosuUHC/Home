from graph import Graph

#  var 2


def calc_dist(n1: str, n2: str, graph: Graph):
    start = graph.getVertex(n1)
    finish = graph.getVertex(n2)

    unvisited = {node: float("inf") for node in graph}
    visited = {}
    best_parents = {node: None for node in graph}
    unvisited[start] = 0

    while unvisited:
        min_vertex = min(unvisited, key=unvisited.get)

        for neighbour, distance in min_vertex.connectedTo.items():
            if neighbour not in unvisited:
                continue
            newDistance = unvisited[min_vertex] + distance
            if unvisited[neighbour] == float("inf") or unvisited[neighbour] > newDistance:
                unvisited[neighbour] = newDistance
                best_parents[neighbour] = min_vertex

        visited[min_vertex] = unvisited[min_vertex]
        del unvisited[min_vertex]
        if not unvisited or min_vertex == finish:
            break

    if visited[finish] == float("inf"):
        print(f"Пути между городами {start.getId()} и {finish.getId()} нет")
        return

    path = [finish]
    cost = [visited[finish]]
    while True:
        key = best_parents[path[0]]
        path.insert(0, key)
        cost.insert(0, visited[path[0]])
        if key == start:
            break

    for p, c in zip(path[:-1], cost[:-1]):
        print(f"{p.getId()}({c}) -> ", end="")

    print(f"{path[-1].getId()}({cost[-1]})")


if __name__ == "__main__":
    graph = Graph()
    data = '''
    Белово, Ленинск-Кузнецкий, 40, 
    Белово, Гурьевск, 35, 
    Ленинск-Кузнецкий, Гурьевск, 71, 
    Ленинск-Кузнецкий, Мыски, 204, 
    Ленинск-Кузнецкий, Кемерово, 112, 
    Ленинск-Кузнецкий, Новосибирск, 236, 
    Киселевск, Ленинск-Кузнецкий, 199, 
    Киселевск, Новосибирск, 328, 
    Гурьевск, Мыски, 206, 
    Мыски, Кемерово, 310, 
    Новосибирск, Кемерово, 260
    '''
    data = [i.strip() for i in data.strip().split(", ")]
    data = [data[i:i + 3] for i in range(0, len(data), 3)]

    for i in data:
        graph.addEdge(i[0], i[1], int(i[2]))

    calc_dist("Белово", "Новосибирск", graph)
    calc_dist("Белово", "Мыски", graph)
    calc_dist("Новосибирск", "Ленинск-Кузнецкий", graph)
