package game.tanks;

import game.ActionField;
import game.field.BattleField;
import game.field.Rock;
import game.field.Water;
import game.interfaces.Direction;
import game.interfaces.Tank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AI {
    private BattleField bf;
    private ActionField af;
    private Tank tank;

    public AI() {
    }

    public AI(ActionField af, BattleField bf, Tank tank) {
        this.af = af;
        this.bf = bf;
        this.tank = tank;

    }

    private int[][] createlabirinth() {
        int[][] way = new int[9][9];
        int[] start = tank.getLocation();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((bf.scanObjectQuadrant(i, j) instanceof Rock) || (bf.scanObjectQuadrant(i, j) instanceof Water)) {
                    way[i][j] = 0;
                } else {
                    way[i][j] = 1;
                }
            }
        }
        return way;
    }

    public void attackHeadQuater() throws Exception {
        PathFinder pathFinder = new PathFinder(createlabirinth());
        Point start = new Point(tank.getLocation());// Hачальная точка
        Point end = new Point(new int[]{8, 4});//Конечная точка
        Point[] path = pathFinder.find(start, end); // Hайдем путь
        List<Direction> directionList = new ArrayList<>();
        for (int i = 0; i < path.length - 1; i++) {
            if (path[i + 1].getX() < path[i].getX()) {
                directionList.add(Direction.LEFT);
            } else if (path[i + 1].getX() > path[i].getX()) {
                directionList.add(Direction.RIGHT);
            } else if (path[i + 1].getY() < path[i].getY()) {
                directionList.add(Direction.UP);
            } else if (path[i + 1].getY() > path[i].getY()) {
                directionList.add(Direction.DOWN);
            }
        }
        for (Direction direction : directionList) {
            tank.turn(direction);
            tank.fire();
            tank.move();
        }

    }

    public void findDefender() throws Exception {
        T34 defender = af.getDefender();
        PathFinder pathFinder = new PathFinder(createlabirinth());
        Point start = new Point(tank.getLocation());// Hачальная точка
        Point end = new Point(defender.getLocation());//Конечная точка
        Point[] path = pathFinder.find(start, end); // Hайдем путь
        List<Direction> directionList = new ArrayList<>();
        for (int i = 0; i < path.length - 1; i++) {
            if (path[i + 1].getX() < path[i].getX()) {
                directionList.add(Direction.LEFT);
            } else if (path[i + 1].getX() > path[i].getX()) {
                directionList.add(Direction.RIGHT);
            } else if (path[i + 1].getY() < path[i].getY()) {
                directionList.add(Direction.UP);
            } else if (path[i + 1].getY() > path[i].getY()) {
                directionList.add(Direction.DOWN);
            }
        }

        for (Direction direction : directionList) {
            tank.turn(direction);
                tank.fire();
                tank.move();
            }

    }


    //__________________________________________________________________________________________________________
    private class Point {
        private int x;
        private int y;

        Point(int[] location) {
            this.x = location[1];
            this.y = location[0];
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            return (((Point) o).getX() == x) && (((Point) o).getY() == y);
        }


    }

    private class PathFinder {

        int[][] fillmap = new int[9][9];
        int[][] labyrinth;
        List buf = new ArrayList();

        PathFinder(int[][] labyrinth) {

            this.labyrinth = labyrinth;
        }


        void push(Point p, int n) {
            if (fillmap[p.getY()][p.getX()] <= n) return;
            fillmap[p.getY()][p.getX()] = n;
            buf.add(p);
        }


        Point pop() {
            if (buf.isEmpty()) {
                return null;
            }
            return (Point) buf.remove(0);
        }

        Point[] find(Point start, Point end) {
            int tx = 0, ty = 0, n = 0, t = 0;
            Point p;

            for (int i = 0; i < fillmap.length; i++) {
                Arrays.fill(fillmap[i], Integer.MAX_VALUE);
            }
            push(start, 0);
            while ((p = pop()) != null) {
                if (p.equals(end)) {
                    System.out.print("path length is: ");
                    System.out.println(n);
                }

                n = fillmap[p.getY()][p.getX()] + labyrinth[p.getY()][p.getX()];


                if ((p.getY() + 1 <= 8) && labyrinth[p.getY() + 1][p.getX()] != 0) {
                    push(new Point(p.getX(), p.getY() + 1), n);
                }
                if ((p.getY() - 1 >= 0) && (labyrinth[p.getY() - 1][p.getX()] != 0)) {
                    push(new Point(p.getX(), p.getY() - 1), n);
                }
                if ((p.getX() + 1 <= labyrinth[p.getY()].length - 1) && (labyrinth[p.getY()][p.getX() + 1] != 0)) {
                    push(new Point(p.getX() + 1, p.getY()), n);
                }
                if ((p.getX() - 1 >= 0) && (labyrinth[p.getY()][p.getX() - 1] != 0)) {
                    push(new Point(p.getX() - 1, p.getY()), n);
                }
            }
            if (fillmap[end.getY()][end.getX()] == Integer.MAX_VALUE) {
                throw new NullPointerException("Path does not exist !!!");
            } else {
                System.out.println("Search completed, ATTAAAAAAAAAAAACK !!!");
            }
            List path = new ArrayList();
            path.add(end);
            int x = end.getX();
            int y = end.getY();
            n = Integer.MAX_VALUE;
            while ((x != start.getX()) || (y != start.getY())) {

                if (y != 8 && fillmap[y + 1][x] < n) {
                    tx = x;
                    ty = y + 1;
                    t = fillmap[y + 1][x];
                } else if (y != 0 && fillmap[y - 1][x] < n) {
                    tx = x;
                    ty = y - 1;
                    t = fillmap[y - 1][x];
                } else if (x != 8 && fillmap[y][x + 1] < n) {
                    tx = x + 1;
                    ty = y;
                    t = fillmap[y][x + 1];
                } else if (x != 0 && fillmap[y][x - 1] < n) {
                    tx = x - 1;
                    ty = y;
                    t = fillmap[y][x - 1];
                }
                x = tx;
                y = ty;
                n = t;
                path.add(new Point(x, y));
            }

            Point[] result = new Point[path.size()];
            t = path.size();
            for (Object point : path) {
                result[--t] = (Point) point;
            }
            return result;
        }

    }
}
