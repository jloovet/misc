import pygame
import gameOfLife
import numpy as np
import time

pygame.init()
screen_size = (500,500)
screen = pygame.display.set_mode(screen_size)

paused = False
grid = np.random.random(size = [100,100]) > 0.8 


#need amin and event loop
while True :
    for event in pygame.event.get() :
        #print(event)
        if event.type == pygame.QUIT :
            exit()
        if event.type == pygame.KEYDOWN and event.key == 32 :
            paused = not paused
        if event.type == pygame.MOUSEBUTTONDOWN :
            x = event.pos[0] // 5 #convert from pixel, using integer division
            y = event.pos[1] // 5 #convert from pixel, using integer division
            grid[x,y] = not grid[x,y]
    
    for x in range(grid.shape[0]) :
        for y in range(grid.shape[1]) :
            if grid[x,y] == 1 :
                pygame.draw.rect(screen, (255,255,255), pygame.Rect(x * 5, y * 5, 5, 5))
            else :    
                pygame.draw.rect(screen, (0,0,0), pygame.Rect(x * 5, y *5, 5, 5))
    #flip to update screen
    pygame.display.flip()
    
    #step 
    if not paused : 
        grid = gameOfLife.step(grid)
    time.sleep(0.1)
