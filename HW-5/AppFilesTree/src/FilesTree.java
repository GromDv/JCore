import java.io.File;

public class FilesTree {
    public static void main(String[] args) {

        printTree(new File("."), "", true);
    }

    /**
     * Метод строит в косоли полное дерево файлов/каталогов, начиная с указанного file.
     * @param file
     * @param indent
     * @param isLast
     */
    public static void printTree(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        if (file.isDirectory()) {
            System.out.println(file.getName().toUpperCase());
            File[] files = file.listFiles();
            if (files == null)
                return;
            for (int i = 0; i < files.length; i++)
                printTree(files[i], indent, i == files.length - 1);
        } else
            System.out.println(file.getName());
    }
}