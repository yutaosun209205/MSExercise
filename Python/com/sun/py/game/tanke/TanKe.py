#!/usr/bin/python
# -*- coding:utf-8 -*-
import pygame
import sys
from pygame.locals import *


class TanKe(object):

    def startGame(self):
        pygame.init()
        self.screen = pygame.display.set_mode((300, 200), pygame.RESIZABLE, 32)
        pygame.display.set_caption("TanKe")
        pygame.mouse.set_visible(0)
        pygame.display.update()

        while True:
            pass



    def stopGame(self):
        sys.exit()


if __name__ == '__main__':
    game = TanKe()
    game.startGame()
