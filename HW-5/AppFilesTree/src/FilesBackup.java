import java.io.*;

public class FilesBackup {
    public static void main(String[] args) {

        File curDir = new File(".");
        File backupDir = new File("./backup");
        if (!backupDir.exists())
            backupDir.mkdir();

        backupDir(curDir, backupDir);
        System.out.println("Бэкап окончен!");
    }

    /**
     * Метод рекурсивно обходит заданную папку sourceDir (и все вложенные)
     * и создаёт копии всех файлов и папок в указанной backupDir папке.
     * @param sourceDir
     * @param backupDir
     */
    public static void backupDir(File sourceDir, File backupDir) {
        boolean Suc = false;
        if (!sourceDir.getName().equals(backupDir.getName())) {
            if (sourceDir.isDirectory()) {
                String infilename = sourceDir.getPath();
                String outfilename = backupDir.getPath() + infilename.substring(1);
                File nDir = new File(outfilename);
                nDir.mkdir();
                File[] files = sourceDir.listFiles();
                if (files == null)
                    return;
                for (int i = 0; i < files.length; i++)
                    backupDir(files[i], backupDir);
            } else {
                String infilename = sourceDir.getPath();
                String outfilename = backupDir.getPath() + "/" + sourceDir.getPath().substring(2);

                InputStream inFile = null;
                OutputStream outFile = null;
                try {
                    inFile = new FileInputStream(infilename);
                    outFile = new FileOutputStream(outfilename);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inFile.read(buffer)) > 0) {
                        outFile.write(buffer, 0, length);
                    }
                } catch (FileNotFoundException ef) {
                    System.out.println(ef.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if(inFile != null) {
                        try {
                            inFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}