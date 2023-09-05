import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int MAX_DIMEТSION = 7; // Максимальный размер игрового поля (любое разумное число)
    private static final int WIN_COUNT_NUM = 4; // Выигрышная комбинация (любое разумное число)
    private static final char DOT_USER = 'X'; // Фишка игрока - человек
    private static final char DOT_COMP = '0'; // Фишка игрока - компьютер
    private static final char DOT_EMPTY = '…'; // Признак пустого поля
    private static final char VERT_DIV = '⁝'; // Вертикальный разделитель
    private static final char HOR_DIV = ' '; // Горизонтальный разделитель

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля

    private static int fieldSizeX = MAX_DIMEТSION; // Размерность игрового поля
    private static int fieldSizeY = MAX_DIMEТSION; // Размерность игрового поля
    private static int winCountHelp = WIN_COUNT_NUM - 1;

    public static void main(String[] args) {
        field = new char[fieldSizeX][fieldSizeY];

        while (true) {
            initialize();
            printField();
            while (true) {
                userTurn();
                printField();
                if (checkGameState(DOT_USER, "Вы победили!"))
                    break;
                compTurn();
                printField();
                if (checkGameState(DOT_COMP, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация объектов игры
     */
    private static void initialize() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     * <p>
     * +-1-2-3-
     * 1|*|X|0|
     * 2|*|*|0|
     * 3|*|*|0|
     * --------
     */
    private static void printField() {
        System.out.print(" ");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++) {
            System.out.print((x % 2 == 0) ? HOR_DIV : Integer.toString(x / 2 + 1));
        }
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.printf("%d%s", y + 1, VERT_DIV);
            for (int x = 0; x < fieldSizeY; x++) {
                System.out.printf("%s%s", field[x][y], VERT_DIV);
            }
            System.out.println();
        }
        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print(HOR_DIV);
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void userTurn() {
        int x, y;

        do {
            while (true) {
                System.out.printf("Введите координату хода X (от 1 до %d): ", fieldSizeY);
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
            while (true) {
                System.out.printf("Введите координату хода Y (от 1 до %d): ", fieldSizeY);
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_USER;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода компьютера
     */
    private static void compTurn() {
        int x, y;
        int[] turn;

        winCountHelp = WIN_COUNT_NUM;           // Ищем предвыигрышные комбинации
        turn = checkWinHelp(DOT_COMP);
        if (turn[0] < 0 && winCountHelp > 3) {
            winCountHelp--;
            turn = checkWinHelp(DOT_COMP);
        }

        winCountHelp = WIN_COUNT_NUM;
        if (turn[0] < 0) {
            turn = checkWinHelp(DOT_USER);
            if (turn[0] >= 0)
                System.out.printf("1. Cx=%d Cy=%d\n", turn[0] + 1, turn[1] + 1);

            if (turn[0] < 0 && winCountHelp > 3) {  // Если не найдены, то
                winCountHelp--;                     // Ищем канун предвыигрышных
                turn = checkWinHelp(DOT_USER);
                System.out.printf("2. Cx=%d Cy=%d\n", turn[0] + 1, turn[1] + 1);
            }
        }


        if (turn[0] >= 0) {
            x = turn[0];
            y = turn[1];
            field[turn[0]][turn[1]] = DOT_COMP;
        } else {
            do {
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            }
            while (!isCellEmpty(x, y));
            field[x][y] = DOT_COMP;
        }
    }

    /**
     * Проверка состояния игры
     *
     * @param c фишка игрока
     * @param s победный слоган
     * @return
     */
    private static boolean checkGameState(char c, String s) {
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

    /**
     * Проверка победы
     *
     * @param c
     * @return
     */
    private static boolean checkWin(char c) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (fieldSizeX - x >= WIN_COUNT_NUM)
                    if (checkWCdir(x, y, "right", c))
                        return true;
                if (fieldSizeY - y >= WIN_COUNT_NUM)
                    if (checkWCdir(x, y, "down", c))
                        return true;
                if (fieldSizeX - x >= WIN_COUNT_NUM && fieldSizeY - y >= WIN_COUNT_NUM)
                    if (checkWCdir(x, y, "R-down", c))
                        return true;
                if (fieldSizeX - x >= WIN_COUNT_NUM && y + 1 >= WIN_COUNT_NUM)
                    if (checkWCdir(x, y, "R-up", c))
                        return true;
            }
        }
        return false;
    }

    /**
     * Метод проверки выигрышной последовательности по направлению
     *
     * @param x
     * @param y
     * @param dir - направление
     * @return
     */
    private static boolean checkWCdir(int x, int y, String dir, char c) {

        int wc = 0, cx = 0, cy = 0;
        switch (dir) {
            case "down":
                cy = 1;
                break;
            case "R-down":
                cx = 1;
                cy = 1;
                break;
            case "right":
                cx = 1;
                break;
            case "R-up":
                cx = 1;
                cy = -1;
                break;
        }
        for (int i = 0; i < WIN_COUNT_NUM; i++) {
            if (field[x][y] == c)
                wc++;
            x += cx;
            y += cy;
//            System.out.printf("x=%d y=%d wc=%s\n",x,y,wc);
        }

        return wc == WIN_COUNT_NUM;
    }

    /**
     * Метод проверяет координату на возможность направления для выигрышной последовательности
     * @param y
     * @return
     */
    private static boolean isPossibleDown(int y) {
        return fieldSizeY - y >= winCountHelp;
    }

    /**
     * Метод проверяет координату на возможность направления для выигрышной последовательности
     * @param y
     * @return
     */
    private static boolean isPossibleUp(int y) {
        return y + 1 >= winCountHelp;
    }

    /**
     * Метод проверяет координату на возможность направления для выигрышной последовательности
     * @param x
     * @return
     */
    private static boolean isPossibleRight(int x) {
        return fieldSizeX - x >= winCountHelp;
    }

    /**
     * Метод проверяет координату на возможность направления для выигрышной последовательности
     * @param x
     * @return
     */
    private static boolean isPossibleLeft(int x) {
        return x + 1 >= winCountHelp;
    }

    /**
     * Метод организует вызовы проверок направлений на наличие последовательностей
     * @param c
     * @return
     */
    private static int[] checkWinHelp(char c) {
        int[] turn = new int[]{-1, -1};
        for (int x = 0; x < fieldSizeX; x++) { // Проверяем все точки
            for (int y = 0; y < fieldSizeY; y++) {
                for (int dir = 0; dir < 8; dir++) {
                    int[] tmp = new int[]{-1, -1};
                    switch (dir) { // Проверяем все направления
                        case 0: // down
                            if (isPossibleDown(y))
                                turn = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 1: // right-down
                            if (isPossibleDown(y) && isPossibleRight(x))
                                turn = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 2: // right
                            if (isPossibleRight(x))
                                turn = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 3: // right-up
                            if (isPossibleRight(x) && isPossibleUp(y))
                                turn = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 4: // up
                            if (isPossibleUp(y))
                                tmp = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 5: // left-up
                            if (isPossibleLeft(x) && isPossibleUp(y))
                                tmp = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 6: // left
                            if (isPossibleLeft(x))
                                tmp = checkWinDirHelp(x, y, dir, c);
                            break;
                        case 7: // left-down
                            if (isPossibleLeft(x) && isPossibleDown(y))
                                tmp = checkWinDirHelp(x, y, dir, c);
                            break;
                        default:
                    }
                    if (turn[0] >= 0 && turn[1] >= 0)
                        return turn;
                }
            }
        }
        return turn;
    }

    /**
     * Метод проверки выигрышной последовательности по направлению
     *
     * @param x
     * @param y
     * @param dir - направление
     * @return -
     */
    private static int[] checkWinDirHelp(int x, int y, int dir, char c) {
        int[] point = new int[2];
        int cx = 0, cy = 0;
        switch (dir) {
            case 0:
                cx = 0;
                cy = 1;
                break;
            case 1:
                cx = 1;
                cy = 1;
                break;
            case 2:
                cx = 1;
                cy = 0;
                break;
            case 3:
                cx = 1;
                cy = -1;
                break;
            case 4:
                cx = 0;
                cy = -1;
                break;
            case 5:
                cx = -1;
                cy = -1;
                break;
            case 6:
                cx = -1;
                cy = 0;
                break;
            case 7:
                cx = -1;
                cy = 1;
                break;
            default:
                break;
        }
        for (int i = 0; i < winCountHelp; i++) {
            if (i == winCountHelp - 1)
                c = DOT_EMPTY;
            if (field[x][y] == c) {
                point[0] = x;
                point[1] = y;
            } else {
                point[0] = -1;
                point[1] = -1;
                return point;
            }
            x += cx;
            y += cy;
        }
        return point;
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }
}
