import pygame
from random import choice

WIDTH, HEIGHT = 800, 600
TILE = int(input("Размер клетки:"))
cols, rows = WIDTH // TILE, HEIGHT // TILE

pygame.init()
window = pygame.display.set_mode((WIDTH, HEIGHT))
clock = pygame.time.Clock()


class Cell:
    def __init__(self, x, y):
        self.x, self.y = x, y
        self.walls = {"top": True, "right": True, "bottom": True, "left": True}
        self.visited = False
        self.end = False
        self.start = False

    def draw(self):
        x, y = self.x * TILE, self.y * TILE

        if self.visited:
            pygame.draw.rect(window, pygame.Color("white"), (x, y, TILE, TILE))

        if self.end:
            pygame.draw.rect(window, pygame.Color("green"), (x, y, TILE, TILE))

        if self.start:
            pygame.draw.rect(window, pygame.Color("red"), (x, y, TILE, TILE))

        if self.walls["top"]:
            pygame.draw.line(window, pygame.Color("black"),
                             (x, y), (x + TILE, y), 3)
        if self.walls["right"]:
            pygame.draw.line(window, pygame.Color("black"),
                             (x + TILE, y), (x + TILE, y + TILE), 3)
        if self.walls["bottom"]:
            pygame.draw.line(window, pygame.Color("black"),
                             (x + TILE, y + TILE), (x, y + TILE), 3)
        if self.walls["left"]:
            pygame.draw.line(window, pygame.Color("black"),
                             (x, y + TILE), (x, y), 3)

    def make_end(self):
        self.end = True

    def make_start(self):
        self.start = True

    def check_cell(self, x, y):
        if x < 0 or x > cols - 1 or y < 0 or y > rows - 1:
            return False

        return grid_cells[x + y * cols]

    def check_neighbors(self):
        neighbors = []
        top = self.check_cell(self.x, self.y - 1)
        right = self.check_cell(self.x + 1, self.y)
        bottom = self.check_cell(self.x, self.y + 1)
        left = self.check_cell(self.x - 1, self.y)
        if top and not top.visited:
            neighbors.append(top)
        if right and not right.visited:
            neighbors.append(right)
        if bottom and not bottom.visited:
            neighbors.append(bottom)
        if left and not left.visited:
            neighbors.append(left)
        return choice(neighbors) if neighbors else False


def remove_walls(current, next):
    dx = current.x - next.x
    if dx == 1:
        current.walls["left"] = False
        next.walls["right"] = False
    elif dx == -1:
        current.walls["right"] = False
        next.walls["left"] = False

    dy = current.y - next.y
    if dy == 1:
        current.walls["top"] = False
        next.walls["bottom"] = False
    elif dy == -1:
        current.walls["bottom"] = False
        next.walls["top"] = False


grid_cells = [Cell(col, row) for row in range(rows) for col in range(cols)]
current_cell = grid_cells[0]
current_cell.make_start()
grid_cells[-1].make_end()

stack = []

while True:

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()

    for cell in grid_cells:
        cell.draw()

    for i, cell in enumerate(stack):
        pygame.draw.rect(window, "white", (cell.x * TILE + 2, cell.y * TILE + 2,
                                           TILE - 4, TILE - 4))
    current_cell.visited = True

    next_cell = current_cell.check_neighbors()
    if next_cell:
        next_cell.visited = True
        stack.append(current_cell)
        remove_walls(current_cell, next_cell)
        current_cell = next_cell
    elif stack:
        current_cell = stack.pop()

    pygame.display.flip()
    clock.tick(200)
