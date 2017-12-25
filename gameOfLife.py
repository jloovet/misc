import numpy as np
import matplotlib.pyplot as plt


def step(grid):
    #make copy with zeros 
    resultGrid = np.zeros_like(grid)
    #iterade grid
    for x in range(grid.shape[0]) :
        for y in range(grid.shape[1]) :
            #get neighbors, cut out rect 3x3
            neighbors = grid[x-1 : x+2, y-1 : y+2]
            #remove current and sum
            neighborsSum = neighbors.sum() - grid[x,y];
            #check if current is alive
            if grid[x,y] == 1 : 
                if neighborsSum in [2,3] :
                    resultGrid[x,y] = 1
            else :
                #cell was dead
                if neighborsSum == 3 :
                    resultGrid[x,y] = 1
    return resultGrid

#produce 1 for approx 20%
grid = np.random.random(size = [100,100]) > 0.8 
#grid = np.zeros([100,100])
#grid[3,3] = 1
#grid[5:20,4] = 1

while True :
    for i in range(10) :
        plt.imshow(grid)
        plt.show()
        grid = step(grid)
    if input("Continue=? ") == 'n' :
        break
    
