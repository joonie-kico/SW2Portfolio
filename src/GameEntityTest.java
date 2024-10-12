import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of parts of Kernel methods in GmaeEntity.
 *
 * @author Joonie Kim
 *
 */
public final class GameEntityTest {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GameEntityTest() {

    }

    /**
     * Moves the entity at map[y][x] to forward by its' direction.
     *
     * @param map
     *            map field of the entity exist.
     * @param x
     *            x coordinate of the entity.
     * @param y
     *            y coordinate of the entity.
     * @updates map
     */
    public static void moveForward(int[][] map, int x, int y) {
        if (map[y][x] == 1 && map[y].length - 1 > x + 1) {
            map[y][x + 1] = map[y][x];
            map[y][x] = 0;
        } else if (map[y][x] == 2 && map.length - 1 > y + 1) {
            map[y + 1][x] = map[y][x];
            map[y][x] = 0;
        } else if (map[y][x] == 3 && 0 < x) {
            map[y][x - 1] = map[y][x];
            map[y][x] = 0;
        } else if (map[y][x] == 4 && 0 < y) {
            map[y - 1][x] = map[y][x];
            map[y][x] = 0;
        }
    }

    /**
     * Turns the entity at map[y][x] to right from its' original direction.
     *
     * @param map
     *            map field of the entity exist.
     * @param x
     *            x coordinate of the entity.
     * @param y
     *            y coordinate of the entity.
     * @updates map
     */
    public static void turnRight(int[][] map, int x, int y) {
        map[y][x] += 1;
        if (map[y][x] > 4) {
            map[y][x] = 1;
        }
    }

    /**
     * Turns the entity at map[y][x] to left from its' original direction.
     *
     * @param map
     *            map field of the entity exist.
     * @param x
     *            x coordinate of the entity.
     * @param y
     *            y coordinate of the entity.
     * @updates map
     */
    public static void turnLeft(int[][] map, int x, int y) {
        map[y][x] -= 1;
        if (map[y][x] < 0) {
            map[y][x] = 4;
        }
    }

    /**
     * Turns back the entity at map[y][x] from its' original direction.
     *
     * @param map
     *            map field of the entity exist.
     * @param x
     *            x coordinate of the entity.
     * @param y
     *            y coordinate of the entity.
     * @updates map
     */
    public static void turnBack(int[][] map, int x, int y) {
        if (map[y][x] == 1) {
            map[y][x] = 3;
        } else if (map[y][x] == 2) {
            map[y][x] = 4;
        } else if (map[y][x] == 3) {
            map[y][x] = 1;
        } else {
            map[y][x] = 2;
        }
    }

    /**
     * Removes the entity at map[y][x].
     *
     * @param map
     *            map field of the entity exist.
     * @param x
     *            x coordinate of the entity.
     * @param y
     *            y coordinate of the entity.
     * @updates map
     */
    public static void death(int[][] map, int x, int y) {
        map[y][x] = 0;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        int[][] map = new int[10][10];
        int x = -1;
        int y = -1;

        while (y < 0 || y >= 10) {
            out.println("input y (-1 < y < 10): ");
            y = in.nextInteger();
        }

        while (x < 0 || x >= 10) {
            out.println("input x (-1 < x < 10): ");
            x = in.nextInteger();
        }

        map[y][x] = 1;

        out.println("***Valid Commands***");
        out.println("f: forward");
        out.println("r: turn right");
        out.println("l: turn left");
        out.println("b: turn back");
        out.println("d: death");
        out.println("input string of commands: ");

        String command = in.nextLine();

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'f') {
                moveForward(map, x, y);
            } else if (command.charAt(i) == 'r') {
                turnRight(map, x, y);
            } else if (command.charAt(i) == 'l') {
                turnLeft(map, x, y);
            } else if (command.charAt(i) == 'b') {
                turnBack(map, x, y);
            } else if (command.charAt(i) == 'd') {
                death(map, x, y);
                out.println("The entity is gone");
                break;
            } else {
                continue;
            }

            out.println("Map " + i + " (" + command.charAt(i) + "): ");
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map[j].length; k++) {
                    out.print(map[j][k] + " ");
                    if (map[j][k] != 0) {
                        y = j;
                        x = k;
                    }
                }
                out.println();
            }
            out.println();

        }

        in.close();
        out.close();
    }
}
